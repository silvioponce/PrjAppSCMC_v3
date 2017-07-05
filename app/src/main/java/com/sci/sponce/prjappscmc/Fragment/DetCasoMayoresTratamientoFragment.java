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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sci.sponce.prjappscmc.ListCasoMayoresActivity;
import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BL.CCMNinoBL;
import BL.TratamientoBL;
import BL.TratamientoNinoBL;
import BL.VisitasNinosMayorBL;
import Entidades.CCMNino;
import Entidades.Tratamiento;
import Entidades.TratamientoNino;

public class DetCasoMayoresTratamientoFragment extends Fragment implements View.OnClickListener {


    private static CCMNino ccmNino = new CCMNino();
    TratamientoBL tratamientoBL = new TratamientoBL();
    CCMNinoBL ccmNinoBL = new CCMNinoBL();
    TratamientoNinoBL tratamientoNinoBL = new TratamientoNinoBL();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String strClasificacionEnfermedad = "ClasificacionEnfermedad";

    private static final String strAmoxicilina = "Amoxicilina";
    private static final String strSuero = "Suero";
    private static final String strZinc = "Zinc";
    private static final String strFurazolidona = "Furazolidona";
    private static final String strRecomendaciones = "Recomendaciones";

    private String ClasificacionEnfermedad, Recomendaciones;
    Boolean Amoxicilina, Suero, Zinc, Furazolidona;

    EditText txtRecomendacionesNino, txtObservacionesNinos;

    TextView lblAmoxicilina, lblSuero, lblZinc, lblFurazolidona, lblClasificacionEnfermedad;
    Spinner spnPreguntaAmoxicilina, spnPreguntaSuero, spnPreguntaZinc, spnPreguntaFurazolidona;

    Cursor cursorAmoxicilina, cursorSuero, cursorZinc, cursorFurazolidona;

    int intAmoxicilina, intSuero, intZinc, intFurazolidona, idUsuarioPref;

    ToggleButton tbn_entregoReferenciaNino;

    Button btnGuardarCasoNino;


    public DetCasoMayoresTratamientoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetCasoMayoresTratamientoFragment newInstance(Object param1, String ClasificacionEnfermedad, String recomendaciones, Boolean amoxicilina, Boolean suero, Boolean zinc, Boolean furazolidona) {
        DetCasoMayoresTratamientoFragment fragment = new DetCasoMayoresTratamientoFragment();
        Bundle args = new Bundle();

        ccmNino = (CCMNino) param1;

        args.putString(strRecomendaciones, recomendaciones);

        args.putBoolean(strAmoxicilina, amoxicilina);
        args.putBoolean(strSuero, suero);
        args.putBoolean(strZinc, zinc);
        args.putBoolean(strFurazolidona, furazolidona);

        args.putString(strClasificacionEnfermedad, ClasificacionEnfermedad);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            ClasificacionEnfermedad = getArguments().getString(strClasificacionEnfermedad);
            Recomendaciones = getArguments().getString(strRecomendaciones);
            Amoxicilina = getArguments().getBoolean(strAmoxicilina, false);
            Suero = getArguments().getBoolean(strSuero, false);
            Zinc = getArguments().getBoolean(strZinc, false);
            Furazolidona = getArguments().getBoolean(strFurazolidona, false);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_det_caso_mayores_tratamiento, container, false);

        incializarComponentes(v);

        CargarCategoriasTratamientos();

        CargarPreguntasTratamiento();

        VisualizarTratamientos();

        CargarPreferencias();

        txtRecomendacionesNino.setText(Recomendaciones);

        CargarTratamientosEdit();

        return v;
    }

    private void VisualizarTratamientos() {
        if (Amoxicilina) {
            lblAmoxicilina.setVisibility(View.VISIBLE);
            spnPreguntaAmoxicilina.setVisibility(View.VISIBLE);
        } else {
            lblAmoxicilina.setVisibility(View.GONE);
            spnPreguntaAmoxicilina.setVisibility(View.GONE);
        }

        if (Suero) {

            lblSuero.setVisibility(View.VISIBLE);
            spnPreguntaSuero.setVisibility(View.VISIBLE);

        } else {
            lblSuero.setVisibility(View.GONE);
            spnPreguntaSuero.setVisibility(View.GONE);

        }

        if (Zinc) {
            lblZinc.setVisibility(View.VISIBLE);
            spnPreguntaZinc.setVisibility(View.VISIBLE);
        } else {
            lblZinc.setVisibility(View.GONE);
            spnPreguntaZinc.setVisibility(View.GONE);

        }

        if (Furazolidona) {
            lblFurazolidona.setVisibility(View.VISIBLE);
            spnPreguntaFurazolidona.setVisibility(View.VISIBLE);
        } else {
            lblFurazolidona.setVisibility(View.GONE);
            spnPreguntaFurazolidona.setVisibility(View.GONE);

        }
    }

    private void CargarTratamientosEdit() {

        List<TratamientoNino> listTratatamientoNino = new ArrayList<TratamientoNino>();
        Tratamiento tratamiento = new Tratamiento();

        int intIdTratamiento;
        boolean boolAmoxicilina = Amoxicilina, boolSuero = Suero, boolZinc = Zinc, boolFurazolidona = Furazolidona;

        listTratatamientoNino = tratamientoNinoBL.getAllTratamientoNinosListCustom(getActivity(), "IdCCMNino = " + String.valueOf(ccmNino.get_id()));

        for (int x = 0; x < listTratatamientoNino.size(); x++) {
            intIdTratamiento = listTratatamientoNino.get(x).getIdTratamiento();
            tratamiento = tratamientoBL.getTratamientoById(getActivity(), String.valueOf(intIdTratamiento));

            if (tratamiento.getGrupo().equals("AmoxicilinaMayor")) {
                intAmoxicilina = intIdTratamiento;
                spnPreguntaAmoxicilina.setSelection(getPositionCursor(cursorAmoxicilina, "_id", tratamiento.get_Id()));
                boolAmoxicilina = false;
            }

            if (tratamiento.getGrupo().equals("SueroMayor")) {
                intSuero = intIdTratamiento;
                spnPreguntaSuero.setSelection(getPositionCursor(cursorSuero, "_id", tratamiento.get_Id()));
                boolSuero = false;
            }

            if (tratamiento.getGrupo().equals("ZincMayor")) {
                intZinc = intIdTratamiento;
                spnPreguntaZinc.setSelection(getPositionCursor(cursorZinc, "_id", tratamiento.get_Id()));
                boolZinc = false;
            }

            if (tratamiento.getGrupo().equals("FurazolidonaMayor")) {
                intFurazolidona = intIdTratamiento;
                spnPreguntaFurazolidona.setSelection(getPositionCursor(cursorFurazolidona, "_id", tratamiento.get_Id()));
                boolFurazolidona = false;
            }

        }

        if (ccmNino.getObservacion() != null)
            txtObservacionesNinos.setText(ccmNino.getObservacion().toString());
        if (ccmNino.getEntregoReferencia() != null)
            tbn_entregoReferenciaNino.setChecked(ccmNino.getEntregoReferencia());

    }

    private void incializarComponentes(View v) {

        txtRecomendacionesNino = (EditText) v.findViewById(R.id.txtRecomendacionesNino);
        txtObservacionesNinos = (EditText) v.findViewById(R.id.txtObservacionesNinos);
        lblClasificacionEnfermedad = (TextView) v.findViewById(R.id.lblClasificacionEnfermedad);

        btnGuardarCasoNino = (Button) v.findViewById(R.id.btnGuardarCasoNino);

        tbn_entregoReferenciaNino = (ToggleButton) v.findViewById(R.id.tbn_entregoReferenciaNino);

        lblAmoxicilina = (TextView) v.findViewById(R.id.lblAmoxicilina);
        lblSuero = (TextView) v.findViewById(R.id.lblSuero);
        lblZinc = (TextView) v.findViewById(R.id.lblZinc);
        lblFurazolidona = (TextView) v.findViewById(R.id.lblFurazolidona);

        spnPreguntaAmoxicilina = (Spinner) v.findViewById(R.id.spnPreguntaAmoxicilina);

        spnPreguntaSuero = (Spinner) v.findViewById(R.id.spnPreguntaSuero);
        spnPreguntaZinc = (Spinner) v.findViewById(R.id.spnPreguntaZinc);
        spnPreguntaFurazolidona = (Spinner) v.findViewById(R.id.spnPreguntaFurazolidona);

        lblClasificacionEnfermedad.setText(ClasificacionEnfermedad);

        getActivity().setTitle("Caso Niños 2 Meses");

        spnPreguntaAmoxicilina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intAmoxicilina = Integer.valueOf(cursorAmoxicilina.getString(cursorAmoxicilina.getColumnIndex("IdTratamiento")));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spnPreguntaSuero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intSuero = Integer.valueOf(cursorSuero.getString(cursorSuero.getColumnIndex("IdTratamiento")));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spnPreguntaZinc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intZinc = Integer.valueOf(cursorZinc.getString(cursorZinc.getColumnIndex("IdTratamiento")));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spnPreguntaFurazolidona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intFurazolidona = Integer.valueOf(cursorFurazolidona.getString(cursorFurazolidona.getColumnIndex("IdTratamiento")));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnGuardarCasoNino.setOnClickListener(this);

    }

    public void CargarCategoriasTratamientos() {

        lblAmoxicilina.setText("Amoxicilina Jarabe de250 mg/5 ml");

    }

    public void CargarPreguntasTratamiento() {

        try {
            cursorAmoxicilina = tratamientoBL.getAllTratamientoCursor(getActivity(), "Amoxi Jarabe de250 mg/5 ml", "AmoxicilinaMayor");
            cursorSuero = tratamientoBL.getAllTratamientoCursor(getActivity(), "SUERO ORAL", "SueroMayor");
            cursorZinc = tratamientoBL.getAllTratamientoCursor(getActivity(), "ZINC: Tabletas de 20", "ZincMayor");
            cursorFurazolidona = tratamientoBL.getAllTratamientoCursor(getActivity(), "Furazolidona 50mg/5ml", "FurazolidonaMayor");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] adapterCols = new String[]{"Pregunta"};
        int[] adapterRowViews = new int[]{android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, cursorAmoxicilina, adapterCols, adapterRowViews, 0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SimpleCursorAdapter scaSuero = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, cursorSuero, adapterCols, adapterRowViews, 0);
        scaSuero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SimpleCursorAdapter scaZinc = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, cursorZinc, adapterCols, adapterRowViews, 0);
        scaZinc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SimpleCursorAdapter scaFurazolidona = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, cursorFurazolidona, adapterCols, adapterRowViews, 0);
        scaFurazolidona.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnPreguntaAmoxicilina.setAdapter(sca);
        spnPreguntaSuero.setAdapter(scaSuero);
        spnPreguntaZinc.setAdapter(scaZinc);
        spnPreguntaFurazolidona.setAdapter(scaFurazolidona);

    }

    public void GuardarCasoNino() {

        if (tbn_entregoReferenciaNino.isChecked()) {
            ccmNino.setEntregoReferencia(true);
        } else {
            ccmNino.setEntregoReferencia(false);
        }

        ccmNino.setObservacion(txtObservacionesNinos.getText().toString());
        ccmNino.setIdUsuario(idUsuarioPref);

        try {
            int id;
            id = ccmNinoBL.GuardarCCMNino(getActivity(), ccmNino);

            List<TratamientoNino> listTratatamientoNino = new ArrayList<TratamientoNino>();

            listTratatamientoNino = tratamientoNinoBL.getAllTratamientoNinosListCustom(getActivity(), "IdCCMNino = " + String.valueOf(ccmNino.get_id()));

            for (int x = 0; x < listTratatamientoNino.size(); x++) {
                int idCCN, idtratamiento;

                idtratamiento = listTratatamientoNino.get(x).getIdTratamiento();
                idCCN = listTratatamientoNino.get(x).getIdCCMNino();

                EliminarTratamiento(idCCN, idtratamiento);

            }


            if (Amoxicilina) {

                GuardarTratamiento(id, intAmoxicilina);
            } else {

                EliminarTratamiento(id, intAmoxicilina);
            }

            if (Suero) {

                GuardarTratamiento(id, intSuero);
            } else {

                EliminarTratamiento(id, intSuero);
            }

            if (Zinc) {

                GuardarTratamiento(id, intZinc);
            } else {

                EliminarTratamiento(id, intZinc);
            }

            if (Furazolidona) {

                GuardarTratamiento(id, intFurazolidona);
            } else {

                EliminarTratamiento(id, intFurazolidona);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void GuardarTratamiento(int id, int idTratamiento) {
        TratamientoNino tratamientoNino = new TratamientoNino();

        tratamientoNino.setIdCCMNino(id);
        tratamientoNino.setIdTratamiento(idTratamiento);

        try {
            tratamientoNinoBL.GuardarTratamientoNino(getActivity(), tratamientoNino);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void EliminarTratamiento(int id, int idTratamiento) {
        TratamientoNino tratamientoNino = new TratamientoNino();

        tratamientoNino.setIdCCMNino(id);
        tratamientoNino.setIdTratamiento(idTratamiento);

        try {
            tratamientoNinoBL.eliminarTratamientoNino(getActivity(), tratamientoNino);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void CargarPreferencias() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PreferenciasUsuario", getActivity().getApplication().MODE_PRIVATE);
        idUsuarioPref = sharedPreferences.getInt("IdUsuario", 0);

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

    private boolean verficaCaso() {
        boolean flag = false;
        String strMensaje = "";

        VisitasNinosMayorBL visitasNinosMayorBL = new VisitasNinosMayorBL();

        try {
            flag = visitasNinosMayorBL.getExisteVisitasNinosMayorByCustomer(getActivity(), "IdCCMNino = " + ccmNino.getIdCCMNino());

            if (flag)
                strMensaje = "El Caso del Niño no se puede actualizar, por que tiene una Visita Registrado";

            if (ccmNino.getIdCCMNino()>0)
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
                        /*android.app.Fragment f = new android.app.Fragment();
                        f = new BuscarCasoNinos();

                        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getFragmentManager().beginTransaction().replace(R.id.Contenedor, f, "BuscarCasoNinos").addToBackStack(null).commit();*/

                    }
                });
                alertDialog1.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardarCasoNino:
                if (verficaCaso())
                    break;

                GuardarCasoNino();

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Información...");
                alertDialog.setMessage("El Caso se Guardo Correctamente!!!");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        /*android.app.Fragment f = new android.app.Fragment();
                        f = new BuscarCasoNinos();

                        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getFragmentManager().beginTransaction().replace(R.id.Contenedor, f, "BuscarCasoNinos").addToBackStack(null).commit();*/

                        Intent intent = new Intent(getActivity(), ListCasoMayoresActivity.class);
                        //intent.putExtra(DetCasoMenoresFragment.ID, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);

                    }
                });
                alertDialog.setIcon(R.mipmap.ic_save);
                alertDialog.show();

                break;
        }
    }

}
