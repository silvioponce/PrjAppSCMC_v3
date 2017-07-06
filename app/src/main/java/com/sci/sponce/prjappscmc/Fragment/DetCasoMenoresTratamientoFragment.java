package com.sci.sponce.prjappscmc.Fragment;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.sci.sponce.prjappscmc.DetCasoMenoresActivity;
import com.sci.sponce.prjappscmc.ListCasoMenoresActivity;
import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import BL.CCMRecienNacidoBL;
import BL.TratamientoBL;
import BL.TratamientoRecienNacidoBL;
import BL.VisitasNinosMenorBL;
import Entidades.CCMRecienNacido;
import Entidades.Tratamiento;
import Entidades.TratamientoRecienNacido;


public class DetCasoMenoresTratamientoFragment extends Fragment implements View.OnClickListener {

    TratamientoBL tratamientoBL = new TratamientoBL();
    CCMRecienNacidoBL ccmRecienNacidoBL = new CCMRecienNacidoBL();
    BL.TratamientoRecienNacidoBL TratamientoRecienNacidoBL = new BL.TratamientoRecienNacidoBL();

    // TODO: Rename and change types of parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";

    private String mParam1;

    Calendar calendar = Calendar.getInstance();

    Cursor cursorTratamiento;

    Spinner spnTratamiento, spnPreguntaTratamiento;

    EditText txtRecomendaciones, txtObservaciones;

    Button btnGuardarCasoNinoMenor;

    ToggleButton tbn_entregoReferencia;

    String idNino;
    String LugarAtencion;
    String NomPregunta;
    String grupo;
    String tratamiento;
    String grupoTratamiento;
    int idTratamientoNinosMenores;
    String dosis;
    String observaciones;

    Boolean ModoEdit = false, entregoReferencia = false;

    int idTratamiento, idUsuarioPref, idCCMRecienNacido;



    public DetCasoMenoresTratamientoFragment() {
        // Required empty public constructor
    }

    public static DetCasoMenoresTratamientoFragment newInstance(String param1, String param2, String param3, String param4) {
        DetCasoMenoresTratamientoFragment fragment = new DetCasoMenoresTratamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    public static DetCasoMenoresTratamientoFragment newInstance(String param1, String param2, String param3, String param4, int idCCMRecienNacido, boolean entregoReferencia) {
        DetCasoMenoresTratamientoFragment fragment = new DetCasoMenoresTratamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putInt(ARG_PARAM5, idCCMRecienNacido);
        args.putBoolean(ARG_PARAM6, entregoReferencia);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetCasoMenoresTratamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetCasoMenoresTratamientoFragment newInstance(String param1, String param2) {
        DetCasoMenoresTratamientoFragment fragment = new DetCasoMenoresTratamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            idNino = getArguments().getString(ARG_PARAM1);
            LugarAtencion = getArguments().getString(ARG_PARAM2);
            NomPregunta = getArguments().getString(ARG_PARAM3);
            grupo = getArguments().getString(ARG_PARAM4);

            idCCMRecienNacido = getArguments().getInt(ARG_PARAM5);

            if (idCCMRecienNacido > 0) {
                entregoReferencia = getArguments().getBoolean(ARG_PARAM6);

                ModoEdit = true;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_det_caso_menores_tratamiento, container, false);

        InicializarComponentes(view);

        CargarTratamientos(grupo);

        CargarRecomendaciones(NomPregunta);

        CargarPreferencias();

        if (ModoEdit) {
            if (verificarPregunta(String.valueOf(idCCMRecienNacido)))
                CargarTratamientosEdit();
        }

        return view;
    }

    private void CargarTratamientosEdit() {
        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();
        CCMRecienNacido ccmRecienNacido = new CCMRecienNacido();
        Tratamiento tratamiento = new Tratamiento();

        if (spnPreguntaTratamiento.isClickable()) {
            try {
                tratamientoRecienNacido = TratamientoRecienNacidoBL.getTratamientoRecienNacidoByCustomer(getActivity(), "IdCCMRecienNacido = " + String.valueOf(idCCMRecienNacido));
                ccmRecienNacido = ccmRecienNacidoBL.getCCMRecienNacidoById(getActivity(), String.valueOf(idCCMRecienNacido));
                tratamiento = tratamientoBL.getTratamientoById(getActivity(), String.valueOf(tratamientoRecienNacido.getIdTratamiento()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            ModoEdit = false;

        txtObservaciones.setText(ccmRecienNacido.getObsevaciones());

        idTratamientoNinosMenores =tratamientoRecienNacido.get_id();
        CargarPreguntasTratamiento(tratamiento.getTratamiento());

        idTratamiento = getPositionCursor(cursorTratamiento, "_id", tratamientoRecienNacido.getIdTratamiento());

    }

    private void InicializarComponentes(View view) {

        btnGuardarCasoNinoMenor = (Button) view.findViewById(R.id.btnGuardarCasoNinoMenor);
        btnGuardarCasoNinoMenor.setOnClickListener(this);

        spnTratamiento = (Spinner) view.findViewById(R.id.spnTratamiento);
        spnPreguntaTratamiento = (Spinner) view.findViewById(R.id.spnPreguntaTratamiento);

        txtRecomendaciones = (EditText) view.findViewById(R.id.txtRecomendaciones);
        txtObservaciones = (EditText) view.findViewById(R.id.txtObservaciones);

        tbn_entregoReferencia = (ToggleButton) view.findViewById(R.id.tbn_entregoReferencia);


        spnTratamiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tratamiento = parent.getItemAtPosition(position).toString();
                CargarPreguntasTratamiento(tratamiento);
                if (ModoEdit) {
                    spnPreguntaTratamiento.setSelection(idTratamiento);
                    ModoEdit = false;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnPreguntaTratamiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idTratamiento = Integer.valueOf(cursorTratamiento.getString(cursorTratamiento.getColumnIndex("IdTratamiento")));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void CargarPreguntasTratamiento(String tratamiento) {

        try {
            cursorTratamiento = tratamientoBL.getAllTratamientoCursor(getActivity(), tratamiento, grupoTratamiento);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] adapterCols = new String[]{"Pregunta"};
        int[] adapterRowViews = new int[]{android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, cursorTratamiento, adapterCols, adapterRowViews, 0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnPreguntaTratamiento.setAdapter(sca);

    }

    public void CargarTratamientos(String grupo) {

        String tratamiento[] = null;

        switch (grupo) {
            case "Acetaminofen":
                grupoTratamiento = "AcetaminofenMenor";
                tratamiento = new String[]{"Aceta frasco de 100 gr/ cc", "Aceta frasco de 120 mg/5 cc"};
                break;
            case "Amoxicilina":
                grupoTratamiento = "AmoxicilinaMenor";
                tratamiento = new String[]{"Amoxi frasco de 125 mg/5cc", "Amoxi frasco de 250 mg/5cc"};
                break;
            case "Tetraciclina":
                grupoTratamiento = "TetraciclinaMenor";
                tratamiento = new String[]{"Tetraciclina oftálmica"};
                break;
            case "ninguno":
                grupoTratamiento = "ninguno";
                tratamiento = new String[]{""};
                spnPreguntaTratamiento.setClickable(false);
                spnTratamiento.setClickable(false);
                break;
        }

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, tratamiento);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnTratamiento.setAdapter(spinnerArrayAdapter);

    }

    public void CargarRecomendaciones(String pregunta) {

        if (pregunta == "NoPuedeTomarPecho" || pregunta == "PielOjosAmarillos" || pregunta == "MovEstimulos" || pregunta == "Convulsiones")
            txtRecomendaciones.setText("Refiéralo inmediatamente al establecimiento de salud");

        if (pregunta == "HundePiel" || pregunta == "RuidosRespirar" || pregunta == "RespRapida")
            txtRecomendaciones.setText("•\tDar primera dosis de Amoxicilina.\n•\tReferirlo.\n•\tRecomendar a la madre que continúe dándole lactancia materna  si es posible, durante el traslado.");

        if (pregunta == "Fibre" || pregunta == "Temperatura")
            txtRecomendaciones.setText("•\tRefiéralo inmediatamente. \n•\tTemperatura  alta dar Acetaminofén. \n•\tTemperatura baja oriente a la madre: Que mantenga al recién nacido abrigado.");

        if (pregunta == "OmbligoPus" || pregunta == "PielUmbilicalRoja" || pregunta == "PielGranos")
            txtRecomendaciones.setText("•\tRefiéralo inmediatamente. \n•\tDar la primera dosis de Amoxicilina.");

        if (pregunta == "OjosPus")
            txtRecomendaciones.setText("•\tRefiéralo inmediatamente. \n•\tAplique en los ojos la primera dosis de tetraciclina  en ungüento.");

        if (entregoReferencia)
            tbn_entregoReferencia.setChecked(true);

    }

    public void GuardarCasoNinoMenor() {

        CCMRecienNacido ccmRecienNacido = new CCMRecienNacido();
        Date fecha = calendar.getTime();

        ccmRecienNacido.set_id(idCCMRecienNacido);

        ccmRecienNacido.setIdNino(Integer.parseInt(idNino));
        ccmRecienNacido.setFechaCCM(fecha);
        ccmRecienNacido.setLugarAtencion(LugarAtencion);

        ccmRecienNacido.setNoPuedeTomarPecho(false);
        ccmRecienNacido.setPielOjosAmarillos(false);
        ccmRecienNacido.setMovEstimulos(false);
        ccmRecienNacido.setHundePiel(false);
        ccmRecienNacido.setRuidosRespirar(false);
        ccmRecienNacido.setFibre(false);
        ccmRecienNacido.setTemperatura(false);
        ccmRecienNacido.setOmbligoPus(false);
        ccmRecienNacido.setPielUmbilicalRoja(false);
        ccmRecienNacido.setPielGranos(false);
        ccmRecienNacido.setOjosPus(false);
        ccmRecienNacido.setOtra(false);

        ccmRecienNacido.setObsevaciones(txtObservaciones.getText().toString());
        ccmRecienNacido.setIdUsuario(idUsuarioPref);

        if (NomPregunta == "NoPuedeTomarPecho")
            ccmRecienNacido.setNoPuedeTomarPecho(true);

        if (NomPregunta == "Convulsiones")
            ccmRecienNacido.setConvulsiones(true);

        if (NomPregunta == "PielOjosAmarillos")
            ccmRecienNacido.setPielOjosAmarillos(true);

        if (NomPregunta == "MovEstimulos")
            ccmRecienNacido.setMovEstimulos(true);

        if (NomPregunta == "HundePiel")
            ccmRecienNacido.setHundePiel(true);

        if (NomPregunta == "RuidosRespirar")
            ccmRecienNacido.setRuidosRespirar(true);

        if (NomPregunta == "RespRapida")
            ccmRecienNacido.setRespRapida(true);

        if (NomPregunta == "Fibre")
            ccmRecienNacido.setFibre(true);

        if (NomPregunta == "Temperatura")
            ccmRecienNacido.setTemperatura(true);

        if (NomPregunta == "OmbligoPus")
            ccmRecienNacido.setOmbligoPus(true);

        if (NomPregunta == "PielUmbilicalRoja")
            ccmRecienNacido.setPielUmbilicalRoja(true);

        if (NomPregunta == "PielGranos")
            ccmRecienNacido.setPielGranos(true);

        if (NomPregunta == "OjosPus")
            ccmRecienNacido.setOjosPus(true);

        if (NomPregunta == "Otra")
            ccmRecienNacido.setOtra(true);

        if (tbn_entregoReferencia.isChecked()) {
            ccmRecienNacido.setEntregoReferencia(true);
        } else {
            ccmRecienNacido.setEntregoReferencia(false);
        }

        try {
            int id;

            if (idCCMRecienNacido!=0){
                ccmRecienNacidoBL.GuardarNino(getActivity(), ccmRecienNacido);
                id = idCCMRecienNacido;
            }else {
                id = ccmRecienNacidoBL.GuardarNino(getActivity(), ccmRecienNacido);
            }

            if (grupo != "ninguno") {
                GuardarTratamiento(id);
            } else {

                TratamientoRecienNacidoBL.eliminarTratamientoRecienNacido(getActivity(), id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void GuardarTratamiento(int id) {
        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

        tratamientoRecienNacido.setIdCCMRecienNacido(id);
        tratamientoRecienNacido.setIdTratamiento(idTratamiento);
        tratamientoRecienNacido.set_id(idTratamientoNinosMenores);

        try {
            TratamientoRecienNacidoBL.GuardarTratamientoRecienNacido(getActivity(), tratamientoRecienNacido);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void CargarPreferencias() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PreferenciasUsuario", getActivity().getApplication().MODE_PRIVATE);
        idUsuarioPref = sharedPreferences.getInt("IdUsuario", 0);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        //inflater.inflate(R.menu.menu_cat_ninos, menu);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardarCasoNinoMenor:

                if (verficaCaso())
                    break;

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Información...");
                alertDialog.setMessage("El Caso se Guardo Correctamente!!!");
                alertDialog.setIcon(R.mipmap.ic_save);

                alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        GuardarCasoNinoMenor();

                        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(getActivity());

                        alertDialog1.setTitle("Informacion...");
                        alertDialog1.setMessage("El Registro se Guardo exitosamente!!!");
                        alertDialog1.setIcon(R.mipmap.ic_save);
                        alertDialog1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                /*android.app.Fragment f = new android.app.Fragment();
                                f = new ListCasoMenoresFragment();

                                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                getFragmentManager().beginTransaction().replace(R.id.Contenedor, f, "BuscarCasoNinosMenores").addToBackStack(null).commit();*/

                                Intent intent = new Intent(getActivity(), ListCasoMenoresActivity.class);
                                //intent.putExtra(DetCasoMenoresFragment.ID, 0);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);


                            }
                        });
                        alertDialog1.show();

                    }
                });

                alertDialog.show();
                break;

        }
    }

    private int getPositionCursor(Cursor cursor, String column, Integer value) {
        int index = 0;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Integer id = Integer.valueOf(cursor.getString(cursor.getColumnIndex(column)));

            if (id == value) {
                index = cursor.getPosition();
            }

        }

        return index;
    }

    private boolean verificarPregunta(String id) {

        boolean flag = false;
        CCMRecienNacido ccmRecienNacido = new CCMRecienNacido();

        try {
            ccmRecienNacido = ccmRecienNacidoBL.getCCMRecienNacidoById(getActivity(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (ccmRecienNacido.getNoPuedeTomarPecho()) {
            if (NomPregunta.equals("NoPuedeTomarPecho"))
                flag = true;
        }

        if (ccmRecienNacido.getConvulsiones()) {
            if (NomPregunta.equals("Convulsiones"))
                flag = true;
        }

        if (ccmRecienNacido.getPielOjosAmarillos()) {
            if (NomPregunta.equals("PielOjosAmarillos"))
                flag = true;
        }

        if (ccmRecienNacido.getMovEstimulos()) {
            if (NomPregunta.equals("MovEstimulos"))
                flag = true;
        }

        if (ccmRecienNacido.getHundePiel()) {
            if (NomPregunta.equals("HundePiel"))
                flag = true;
        }

        if (ccmRecienNacido.getRuidosRespirar()) {
            if (NomPregunta.equals("RuidosRespirar"))
                flag = true;
        }

        if (ccmRecienNacido.getRespRapida()) {
            if (NomPregunta.equals("RespRapida"))
                flag = true;
        }

        if (ccmRecienNacido.getFibre()) {
            if (NomPregunta.equals("Fibre"))
                flag = true;
        }
        if (ccmRecienNacido.getTemperatura()) {
            if (NomPregunta.equals("Temperatura"))
                flag = true;
        }

        if (ccmRecienNacido.getOmbligoPus()) {
            if (NomPregunta.equals("OmbligoPus"))
                flag = true;
        }

        if (ccmRecienNacido.getPielUmbilicalRoja()) {
            if (NomPregunta.equals("PielUmbilicalRoja"))
                flag = true;
        }
        if (ccmRecienNacido.getPielGranos()) {
            if (NomPregunta.equals("PielGranos"))
                flag = true;
        }

        if (ccmRecienNacido.getOjosPus()) {
            if (NomPregunta.equals("OjosPus"))
                flag = true;
        }

        if (ccmRecienNacido.getOtra()) {
            if (NomPregunta.equals("Otra"))
                flag = true;
        }

        return flag;
    }

    private boolean verficaCaso() {
        boolean flag = false;
        String strMensaje = "";

        VisitasNinosMenorBL visitasNinosMenorBL = new VisitasNinosMenorBL();

        try {
            flag = visitasNinosMenorBL.getExisteVisitasNinosMenorByCustomer(getActivity(), "IdCCMRecienNacido = " + idCCMRecienNacido);

            if (flag)
                strMensaje = "El Caso del Niño no se puede actualizar, por que tiene una Visita Registrado";

            if (ccmRecienNacidoBL.getCCMRecienNacidoById(getActivity(), String.valueOf(idCCMRecienNacido)).getIdCCMRecienNacido()>0)
            {
                strMensaje = "El Caso del Niño no se puede actualizar, por que ya esta registrado en el Servidor";
                flag = true;
            }

            if (flag) {
                AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(getActivity());

                alertDialog1.setTitle("Informacion...");
                alertDialog1.setMessage(strMensaje);
                alertDialog1.setIcon(R.mipmap.ic_save);
                alertDialog1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), ListCasoMenoresActivity.class);
                        //intent.putExtra(DetCasoMenoresFragment.ID, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);

                    }
                });
                alertDialog1.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
