package com.sci.sponce.prjappscmc.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sci.sponce.prjappscmc.ListVistaMenoresActivity;
import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import BL.CCMRecienNacidoBL;
import BL.NinoBL;
import BL.TratamientoBL;
import BL.TratamientoRecienNacidoBL;
import BL.VisitasNinosMenorBL;
import Entidades.CCMRecienNacido;
import Entidades.Nino;
import Entidades.Tratamiento;
import Entidades.TratamientoRecienNacido;
import Entidades.VisitasNinosMenor;


public class DetVisitaMenoresFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static String ID = "extra.id";

    NinoBL ninoBL = new NinoBL();
    CCMRecienNacidoBL ccmRecienNacidoBL = new CCMRecienNacidoBL();
    TratamientoBL tratamientoBL = new TratamientoBL();
    TratamientoRecienNacidoBL tratamientoRecienNacidoBL = new TratamientoRecienNacidoBL();

    VisitasNinosMenorBL visitasNinosMenorBL = new VisitasNinosMenorBL();

    DateFormat formate = DateFormat.getDateInstance(DateFormat.SHORT);
    Calendar calendar = Calendar.getInstance();
    Calendar calendarFechaActual = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d;

    int idNino, idCCMRecienNacido, idUsuarioPref, idVisitaNinosMenores;

    String ResultadoVisita;

    Boolean modoEidt;

    TextView txtnomNinoVisitaMenor, txtEnfermedadVisitaMenor, txtTratamientoVisitaMenor;

    EditText txtFechaVisitaMenor, txtObservaciones;

    ImageButton btnFechaVisitaMenor;

    ToggleButton tbn_CumpMedRect, tbn_TomaDosisIndicada;

    Button btnGuardarVisitaMenor;

    Spinner spnResultadoVisita;

    public DetVisitaMenoresFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetVisitaMenoresFragment newInstance(String param1, String param2) {
        DetVisitaMenoresFragment fragment = new DetVisitaMenoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (getArguments() != null) {
            idNino = getArguments().getInt("idNino");
            idCCMRecienNacido = getArguments().getInt("idCCMRecienNacido");

            modoEidt = getArguments().getBoolean("modoEdit", false);

            if (modoEidt) {
                idVisitaNinosMenores = getArguments().getInt(ID);

            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_det_visita_menores, container, false);

        txtnomNinoVisitaMenor = (TextView) view.findViewById(R.id.txtnomNinoVisitaMenor);
        txtEnfermedadVisitaMenor = (TextView) view.findViewById(R.id.txtEnfermedadVisitaMenor);
        txtTratamientoVisitaMenor = (TextView) view.findViewById(R.id.txtTratamientoVisitaMenor);

        txtFechaVisitaMenor = (EditText) view.findViewById(R.id.txtFechaVisitaMenor);
        txtObservaciones = (EditText) view.findViewById(R.id.txtObservaciones);

        btnFechaVisitaMenor = (ImageButton) view.findViewById(R.id.btnFechaVisitaMenor);

        tbn_CumpMedRect = (ToggleButton) view.findViewById(R.id.tbn_CumpMedRect);
        tbn_TomaDosisIndicada = (ToggleButton) view.findViewById(R.id.tbn_TomaDosisIndicada);

        btnGuardarVisitaMenor = (Button) view.findViewById(R.id.btnGuardarVisitaMenor);

        btnGuardarVisitaMenor.setOnClickListener(this);

        spnResultadoVisita = (Spinner) view.findViewById(R.id.spnResultadoVisita);

        CargarResultadoVisita();

        d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();

            }
        };

        spnResultadoVisita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ResultadoVisita = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        updateDate();

        CargarPreferencias();

        CargarDatosGenerales();

        getActivity().setTitle("Visita Nino(a) Menores");

        if (modoEidt)
            CargarVisitaNinosMenores(idVisitaNinosMenores);

        return view;
    }

    public void updateDate() {
        txtFechaVisitaMenor.setText(formate.format(calendarFechaActual.getTime()));
    }

    public void CargarDatosGenerales() {

        Nino nino = new Nino();
        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();
        CCMRecienNacido ccmRecienNacido = new CCMRecienNacido();
        Tratamiento tratamiento = new Tratamiento();

        try {
            nino = ninoBL.getNinoById(getActivity(), String.valueOf(idNino));
            ccmRecienNacido = ccmRecienNacidoBL.getCCMRecienNacidoById(getActivity(), String.valueOf(idCCMRecienNacido));
            tratamientoRecienNacido = tratamientoRecienNacidoBL.getTratamientoRecienNacidoByCustomer(getActivity(), "IdCCMRecienNacido = " + String.valueOf(ccmRecienNacido.get_id()));
            tratamiento = tratamientoBL.getTratamientoById(getActivity(), String.valueOf(tratamientoRecienNacido.getIdTratamiento()));

            txtnomNinoVisitaMenor.setText(nino.getNomNino());

            if (tratamiento.getTratamiento() == null) {
                txtTratamientoVisitaMenor.setText("No se dio Tratamiento");
            } else {
                txtTratamientoVisitaMenor.setText(tratamiento.getTratamiento() + " - " + tratamiento.getPregunta());
            }

            if (ccmRecienNacido.getNoPuedeTomarPecho())
                txtEnfermedadVisitaMenor.setText("No puede tomar del pecho o beber");

            if (ccmRecienNacido.getConvulsiones())
                txtEnfermedadVisitaMenor.setText("Ha tenido convulsiones o ataques");

            if (ccmRecienNacido.getHundePiel())
                txtEnfermedadVisitaMenor.setText("Se le hunde la piel debajo de la costilla al respirar");

            if (ccmRecienNacido.getRuidosRespirar())
                txtEnfermedadVisitaMenor.setText("Ruidos raros al respirar o hervor en el pecho");

            if (ccmRecienNacido.getRespRapida())
                txtEnfermedadVisitaMenor.setText("Hay respiración rápida");

            if (ccmRecienNacido.getFibre())
                txtEnfermedadVisitaMenor.setText("Tiene temperatura alta");

            if (ccmRecienNacido.getTemperatura())
                txtEnfermedadVisitaMenor.setText("Tiene temperatura baja");

            if (ccmRecienNacido.getPielOjosAmarillos())
                txtEnfermedadVisitaMenor.setText("Tiene Piel y ojos amarillos");

            if (ccmRecienNacido.getMovEstimulos())
                txtEnfermedadVisitaMenor.setText("Tiene Movimiento sólo cuando está estimulado, o ningún movimiento, incluso en la estimulación");

            if (ccmRecienNacido.getOmbligoPus())
                txtEnfermedadVisitaMenor.setText("Hay pus que sale del cordón umbilical");

            if (ccmRecienNacido.getPielUmbilicalRoja())
                txtEnfermedadVisitaMenor.setText("La piel alrededor del cordón umbilical esta roja");

            if (ccmRecienNacido.getPielGranos())
                txtEnfermedadVisitaMenor.setText("Hay granos o abscesos en la piel llenos de pus");

            if (ccmRecienNacido.getOjosPus())
                txtEnfermedadVisitaMenor.setText("Hay pus saliendo de los ojos");

            if (ccmRecienNacido.getOtra())
                txtEnfermedadVisitaMenor.setText("Otras");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void setDate() {
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(calendarFechaActual.getTimeInMillis());
        dialog.show();

    }

    public void CargarResultadoVisita() {
        String ResultadoVisita[] = new String[]{"Mejoro", "No Mejoro", "Fallecio"};
        ;
        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ResultadoVisita);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnResultadoVisita.setAdapter(spinnerArrayAdapter);
    }

    private void GuardarVisitaNinoMenor() {
        Date fecha = calendarFechaActual.getTime();

        VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

        if (modoEidt)
            visitasNinosMenor.set_id(idVisitaNinosMenores);

        visitasNinosMenor.setIdCCMRecienNacido(idCCMRecienNacido);

        if (tbn_CumpMedRect.isChecked()) {
            visitasNinosMenor.setCumpMedRect(true);
        } else {
            visitasNinosMenor.setCumpMedRect(false);
        }

        if (tbn_TomaDosisIndicada.isChecked()) {
            visitasNinosMenor.setTomandoDosisIndicada(true);
        } else {
            visitasNinosMenor.setTomandoDosisIndicada(false);
        }

        visitasNinosMenor.setObservacion(txtObservaciones.getText().toString());

        visitasNinosMenor.setFechaVisita(fecha);

        visitasNinosMenor.setResultadoVisita(ResultadoVisita);

        visitasNinosMenor.setIdUsuario(idUsuarioPref);

        try {
            visitasNinosMenorBL.GuardarVisitaNinosMenores(getActivity(), visitasNinosMenor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void CargarVisitaNinosMenores(int idVisitaNinosMenores) {

        VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

        try {
            visitasNinosMenor = visitasNinosMenorBL.getVisitasNinosMenorById(getActivity(), String.valueOf(idVisitaNinosMenores));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Calendar calendar2 = Calendar.getInstance();
        Date dateCasoNinoMenor = new Date(String.valueOf(visitasNinosMenor.getFechaVisita()));
        calendar2.setTime(dateCasoNinoMenor);

        calendarFechaActual = calendar2;

        txtFechaVisitaMenor.setText(formate.format(calendar2.getTime()));

        if (visitasNinosMenor.getCumpMedRect()) {
            tbn_CumpMedRect.setChecked(true);
        }

        if (visitasNinosMenor.getTomandoDosisIndicada()) {
            tbn_TomaDosisIndicada.setChecked(true);
        }

        txtObservaciones.setText(visitasNinosMenor.getObservacion());

        for (int i = 0; i < spnResultadoVisita.getCount(); i++) {
            String value = String.valueOf(spnResultadoVisita.getItemAtPosition(i));

            if (value.equals(visitasNinosMenor.getResultadoVisita()))
                spnResultadoVisita.setSelection(i);
        }

    }

    public void CargarPreferencias() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PreferenciasUsuario", getActivity().getApplication().MODE_PRIVATE);
        idUsuarioPref = sharedPreferences.getInt("IdUsuario", 0);

    }

    private boolean verficaVisita() {
        boolean flag = false;
        String strMensaje = "";

        try {

            if (visitasNinosMenorBL.getVisitasNinosMenorById(getActivity(), String.valueOf(idVisitaNinosMenores)).getIdVisita()>0)
            {
                strMensaje = "La Visita del Niño no se puede actualizar, por que ya esta registrada en el Servidor";
                flag = true;
            }

            if (flag) {
                AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(getActivity());

                alertDialog1.setTitle("Informacion...");
                alertDialog1.setMessage(strMensaje);
                alertDialog1.setIcon(R.mipmap.ic_save);
                alertDialog1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.app.Fragment f = new android.app.Fragment();
                       /* f = new BuscarVisitaNinosMenores().newInstance(idCCMRecienNacido, false);

                        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getFragmentManager().beginTransaction().replace(R.id.Contenedor, f, "BuscarVistaNinosMenores").addToBackStack(null).commit();*/
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
            case R.id.btnGuardarVisitaMenor:

                if (verficaVisita())
                    break;

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Guardar Registro...");
                alertDialog.setMessage("¿Desea guardar este registro?");
                alertDialog.setIcon(R.mipmap.ic_save);
                alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        GuardarVisitaNinoMenor();


                        startActivity(new Intent(getActivity(), ListVistaMenoresActivity.class)
                               .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP ));
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

                break;
            case R.id.btnFechaVisitaMenor:
                setDate();
                break;
        }

    }
}
