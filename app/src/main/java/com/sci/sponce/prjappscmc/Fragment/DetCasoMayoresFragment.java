package com.sci.sponce.prjappscmc.Fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import BL.CCMNinoBL;
import BL.NinoBL;
import Entidades.CCMNino;
import Entidades.Nino;
import Entidades.TratamientoNino;


public class DetCasoMayoresFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public static String ID = "extra.id";

    NinoBL ninoBL = new NinoBL();
    CCMNinoBL ccmNinoBL = new CCMNinoBL();
    CCMNino ccmNino = new CCMNino();

    DateFormat formate = DateFormat.getDateInstance(DateFormat.SHORT);
    Calendar calendar = Calendar.getInstance();
    Calendar calendarFechaActual = Calendar.getInstance();
    Calendar calendarFechaNacNino = Calendar.getInstance();

    Button btnContinuarCasoNinos;

    EditText edt_fecatencion_Meses_Nino, edt_fecatencion_nino;
    ImageButton bt_fecatencion_Nino;

    TextView lblNomNino;

    DatePickerDialog.OnDateSetListener d;

    boolean ModoEdit = false;

    RadioButton radio_sesion_Nino, radio_madrebuscabps_Nino, radio_visitadomiciliar_Nino, radio_primera_vez_si_Ninos, radio_primera_vez_no_Ninos;

    ToggleButton tbn_DificilDespertar,
            tbn_NoPuedeTomarPecho,
            tbn_VomitaTodo,
            tbn_Ataques,
            tbn_HundePiel,
            tbn_RuidosRespirar,
            tbn_RespiracionRapida,
            tbn_FrecCardiaca,
            tbn_SignoNeumonia,
            tbn_TosCatarro,
            tbn_Moco,
            tbn_MuyDormido,
            tbn_DejoComer,
            tbn_OjosHundido,
            tbn_SignoPliegue,
            tbn_DiarreMayorDias,
            tbn_PopuSangre,
            tbn_InquietoIrritable,
            tbn_BebeMuchaSed,
            tbn_PresentaDeshidratacion,
            tbn_CostadoCaliente;

    int idNino, idCCMNino;

    Boolean Amoxicilina = false, Suero = false, Zinc = false, Furazolidona = false;

    String strRecomendaciones = "";

    public DetCasoMayoresFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetCasoMayoresFragment newInstance(int idNino, int idCCMNino, boolean modoEdit) {
        DetCasoMayoresFragment fragment = new DetCasoMayoresFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idNino);
        args.putInt(ARG_PARAM2, idCCMNino);
        args.putBoolean(ARG_PARAM3, modoEdit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            int Id = getArguments().getInt(DetNinoFragment.ID, 0);
            idNino = Id;

            if (getArguments().getBoolean("modoEdit")) {
                idCCMNino = Integer.parseInt(getArguments().getString("idCCMRecienNacido"));
                ModoEdit = getArguments().getBoolean("modoEdit");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_action_bar, menu);
        menu.findItem(R.id.btnActionNext).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnActionNext : {
                //Toast.makeText(getActivity(), clasificarEnfermedad(), Toast.LENGTH_SHORT).show();
                String strMensaje;

                strMensaje = clasificarEnfermedad();

                if (strMensaje.equals("NoAplicaNeumonia")) {
                    Mensajes("La Clasificación no puede tener 2 Tipos de Neumonia por favor revisar los sintomas!!!");
                    break;
                }

                if (strMensaje.equals("NoDeshidratacion")) {
                    Mensajes("La Clasificación no puede tener 2 Tipos de Deshidratación por favor revisar los sintomas!!!");
                    break;
                }

                if (strMensaje.equals("NoClasificación")) {
                    Mensajes("No se ha podido Clasificar la Enfermedad, por favor revise los sintomas!!!");
                    break;
                }

                //if (!ModoEdit)
                ccmNino = guardarDatosCasoNino();

                if (radio_madrebuscabps_Nino.isChecked())
                    ccmNino.setLugarAtencion("Madre busca a BPS");

                if (radio_sesion_Nino.isChecked())
                    ccmNino.setLugarAtencion("Sesion de Pesaje");

                if (radio_visitadomiciliar_Nino.isChecked())
                    ccmNino.setLugarAtencion("Visita Domiciliar");

                ccmNino.setIdNino(idNino);

                getFragmentManager().beginTransaction().replace(R.id.contenedor_detalle, new DetCasoMayoresTratamientoFragment().newInstance(ccmNino, strMensaje, strRecomendaciones, Amoxicilina, Suero, Zinc, Furazolidona), "CatCasoNinosTratamiento").addToBackStack("").commit();

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_det_caso_mayores, container, false);

        inicializarComponentes(v);

        if (ModoEdit) {
            CargaCasoNino();
        } else {
            CargarNino(idNino);
        }

        return v;
    }

    private void inicializarComponentes(View v) {

        lblNomNino = (TextView) v.findViewById(R.id.lblNomNino);

        bt_fecatencion_Nino = (ImageButton) v.findViewById(R.id.bt_fecatencion_Nino);
        edt_fecatencion_nino = (EditText) v.findViewById(R.id.edt_fecatencion_nino);
        edt_fecatencion_Meses_Nino = (EditText) v.findViewById(R.id.edt_fecatencion_Meses_Nino);

        radio_sesion_Nino = (RadioButton) v.findViewById(R.id.radio_sesion_Nino);
        radio_madrebuscabps_Nino = (RadioButton) v.findViewById(R.id.radio_madrebuscabps_Nino);
        radio_visitadomiciliar_Nino = (RadioButton) v.findViewById(R.id.radio_visitadomiciliar_Nino);
        radio_primera_vez_si_Ninos = (RadioButton) v.findViewById(R.id.radio_primera_vez_si_Ninos);
        radio_primera_vez_no_Ninos = (RadioButton) v.findViewById(R.id.radio_primera_vez_no_Ninos);

        tbn_DificilDespertar = (ToggleButton) v.findViewById(R.id.tbn_DificilDespertar);
        tbn_NoPuedeTomarPecho = (ToggleButton) v.findViewById(R.id.tbn_NoPuedeTomarPecho);
        tbn_VomitaTodo = (ToggleButton) v.findViewById(R.id.tbn_VomitaTodo);
        tbn_Ataques = (ToggleButton) v.findViewById(R.id.tbn_Ataques);
        tbn_HundePiel = (ToggleButton) v.findViewById(R.id.tbn_HundePiel);
        tbn_RuidosRespirar = (ToggleButton) v.findViewById(R.id.tbn_RuidosRespirar);
        tbn_RespiracionRapida = (ToggleButton) v.findViewById(R.id.tbn_RespiracionRapida);
        tbn_SignoNeumonia = (ToggleButton) v.findViewById(R.id.tbn_SignoNeumonia);
        tbn_TosCatarro = (ToggleButton) v.findViewById(R.id.tbn_TosCatarro);
        tbn_Moco = (ToggleButton) v.findViewById(R.id.tbn_Moco);
        tbn_MuyDormido = (ToggleButton) v.findViewById(R.id.tbn_MuyDormido);
        tbn_DejoComer = (ToggleButton) v.findViewById(R.id.tbn_DejoComer);
        tbn_OjosHundido = (ToggleButton) v.findViewById(R.id.tbn_OjosHundido);
        tbn_SignoPliegue = (ToggleButton) v.findViewById(R.id.tbn_SignoPliegue);
        tbn_DiarreMayorDias = (ToggleButton) v.findViewById(R.id.tbn_DiarreMayorDias);
        tbn_PopuSangre = (ToggleButton) v.findViewById(R.id.tbn_PopuSangre);
        tbn_InquietoIrritable = (ToggleButton) v.findViewById(R.id.tbn_InquietoIrritable);
        tbn_BebeMuchaSed = (ToggleButton) v.findViewById(R.id.tbn_BebeMuchaSed);
        tbn_PresentaDeshidratacion = (ToggleButton) v.findViewById(R.id.tbn_PresentaDeshidratacion);
        tbn_CostadoCaliente = (ToggleButton) v.findViewById(R.id.tbn_CostadoCaliente);

        btnContinuarCasoNinos = (Button) v.findViewById(R.id.btnContinuarCasoNinos);
        btnContinuarCasoNinos.setOnClickListener(this);
        btnContinuarCasoNinos.setVisibility(View.GONE);

        d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
                obtenerMeses(calendarFechaNacNino.getTime(), calendar.getTime());
            }
        };

        getActivity().setTitle("Caso Niños 2 Meses");

    }

    private void CargaCasoNino() {
        Nino nino = new Nino();

        TratamientoNino tratamientoNino = new TratamientoNino();
        try {
            ccmNino = ccmNinoBL.getCCMNinoById(getActivity(), String.valueOf(idCCMNino));
            nino = ninoBL.getNinoById(getActivity(), String.valueOf(idNino));
        } catch (SQLException e) {
            e.printStackTrace();

        }


        if (ccmNino.getDificilDespertar()) {
            tbn_DificilDespertar.setChecked(true);

        }

        if (ccmNino.getNoPuedeTomarPecho()) {
            tbn_NoPuedeTomarPecho.setChecked(true);
        }

        if (ccmNino.getVomitaTodo()) {
            tbn_VomitaTodo.setChecked(true);
        }

        if (ccmNino.getAtaques()) {
            tbn_Ataques.setChecked(true);
        }

        // Neumonia Grave

        if (ccmNino.getHundePiel()) {
            tbn_HundePiel.setChecked(true);
        }

        if (ccmNino.getRuidosRespirar()) {
            tbn_RuidosRespirar.setChecked(true);
        }

        // Neumonia no Complicada

        if (ccmNino.getRespiracionRapida()) {
            tbn_RespiracionRapida.setChecked(true);
        }

        // Tos o Catarro

        if (ccmNino.getSignoNeumonia()) {
            tbn_SignoNeumonia.setChecked(true);
        }

        if (ccmNino.getTosCatarro()) {
            tbn_TosCatarro.setChecked(true);

        }

        if (ccmNino.getMoco()) {
            tbn_Moco.setChecked(true);
        }

        //Deshidratacion Grave o Sever

        if (ccmNino.getMuyDormido()) {
            tbn_MuyDormido.setChecked(true);
        }

        if (ccmNino.getDejoComer()) {
            tbn_DejoComer.setChecked(true);
        }

        if (ccmNino.getOjosHundido()) {
            tbn_OjosHundido.setChecked(true);
        }

        if (ccmNino.getSignoPliegue()) {
            tbn_SignoPliegue.setChecked(true);
        }


        // Diarrea Persistente

        if (ccmNino.getDiarreMayorDias()) {
            tbn_DiarreMayorDias.setChecked(true);
        }

        // diarrea o pupú con moco y sangre

        if (ccmNino.getPopuSangre()) {
            tbn_PopuSangre.setChecked(true);
        }

        //Diarrea con Deshidratacion Moderada

        if (ccmNino.getInquietoIrritable()) {
            tbn_InquietoIrritable.setChecked(true);
        }

        if (ccmNino.getBebeMuchaSed()) {
            tbn_BebeMuchaSed.setChecked(true);
        }

        //Diarrea sin Deshidratacion

        if (ccmNino.getPresentaDeshidratacion()) {
            tbn_PresentaDeshidratacion.setChecked(true);
        }

        //Fiebre

        if (ccmNino.getCostadoCaliente()) {
            tbn_CostadoCaliente.setChecked(true);
        }


        if (ccmNino.getLugarAtencion().equals("Madre busca a BPS"))
            radio_madrebuscabps_Nino.setChecked(true);
        if (ccmNino.getLugarAtencion().equals("Sesion de Pesaje"))
            radio_sesion_Nino.setChecked(true);
        if (ccmNino.getLugarAtencion().equals("Visita Domiciliar"))
            radio_visitadomiciliar_Nino.setChecked(true);

        lblNomNino.setText(nino.getNomNino());

        Date date = new Date(String.valueOf(ccmNino.getFechaCCM()));
        calendar.setTime(date);

        Date dateFechaNac = new Date(String.valueOf(nino.getFechaNac()));
        calendarFechaNacNino.setTime(dateFechaNac);

        updateDate();
        int meses = obtenerMeses(dateFechaNac, date);

        edt_fecatencion_Meses_Nino.setText(String.valueOf(meses));

    }

    private int obtenerMeses(Date dateInicial, Date dateFinal) {


        java.util.GregorianCalendar jCal = new java.util.GregorianCalendar();
        java.util.GregorianCalendar jCal2 = new java.util.GregorianCalendar();

        jCal.setTime(dateInicial);
        jCal2.setTime(dateFinal);

        long diferencia = jCal2.getTime().getTime() - jCal.getTime().getTime();
        double minutos = diferencia / (1000 * 60);
        long horas = (long) (minutos / 60);
        long minuto = (long) (minutos % 60);
        long segundos = (long) diferencia % 1000;
        long dias = horas / 24;

        //Calcular meses...
        //Crear vector para almacenar los diferentes dias maximos segun correponda
        String[] mesesAnio = new String[12];
        mesesAnio[0] = "31";
        //validacion de los años bisiestos
        if (jCal.isLeapYear(jCal.YEAR)) {
            mesesAnio[1] = "29";
        } else {
            mesesAnio[1] = "28";
        }
        mesesAnio[2] = "31";
        mesesAnio[3] = "30";
        mesesAnio[4] = "31";
        mesesAnio[5] = "30";
        mesesAnio[6] = "31";
        mesesAnio[7] = "31";
        mesesAnio[8] = "30";
        mesesAnio[9] = "31";
        mesesAnio[10] = "30";
        mesesAnio[11] = "31";
        int diasRestantes = (int) dias;
        //variable almacenará el total de meses que hay en esos dias
        int totalMeses = 0;
        int mesActual = jCal.MONTH - 1;
        //Restar los dias de cada mes desde la fecha de ingreso hasta que ya no queden sufcientes dias para
        // completar un mes.
        for (int i = 0; i <= 11; i++) {
            //Validar año, si sumando 1 al mes actual supera el fin de año,
            // setea la variable a principio de año
            if ((mesActual + 1) >= 12) {
                mesActual = i;
            }
            //Validar que el numero de dias resultantes de la resta de las 2 fechas, menos los dias
            //del mes correspondiente sea mayor a cero, de ser asi totalMeses aumenta,continuar hasta
            //que ya nos se cumpla.
            if ((diasRestantes - Integer.parseInt(mesesAnio[mesActual])) >= 0) {
                totalMeses++;
                diasRestantes = diasRestantes - Integer.parseInt(mesesAnio[mesActual]);
                mesActual++;
            } else {
                break;
            }
        }
        //Resto de horas despues de sacar los dias
        horas = horas % 24;
        String salida = "";
        if (totalMeses > 0) {
            if (totalMeses > 1)
                salida = salida + String.valueOf(totalMeses) + " Meses,  ";
            else
                salida = salida + String.valueOf(totalMeses) + " Mes, ";
        }
        if (diasRestantes > 0) {
            if (diasRestantes > 1)
                salida = salida + String.valueOf(diasRestantes) + " Dias, ";
            else
                salida = salida + String.valueOf(diasRestantes) + " Dia, ";
        }

        int dato = (dateFinal.getMonth() - dateInicial.getMonth()) + ((dateFinal.getYear() - dateInicial.getYear()) * 12);

        if ((dateInicial.getDate() - dateFinal.getDate()) < 0)
        {
            //dato = dato + 1;
        }else
        {
            dato = dato - 1;
        }

        return dato;

    }

    public void updateDate() {
        edt_fecatencion_nino.setText(formate.format(calendar.getTime()));
    }

    private CCMNino guardarDatosCasoNino() {

        Date fecha = calendar.getTime();
        ccmNino.setFechaCCM(fecha);

        ccmNino.setDificilDespertar(false);
        ccmNino.setNoPuedeTomarPecho(false);
        ccmNino.setVomitaTodo(false);
        ccmNino.setAtaques(false);
        ccmNino.setHundePiel(false);
        ccmNino.setRuidosRespirar(false);
        ccmNino.setRespiracionRapida(false);
        ccmNino.setSignoNeumonia(false);
        ccmNino.setTosCatarro(false);
        ccmNino.setMoco(false);
        ccmNino.setMuyDormido(false);
        ccmNino.setDejoComer(false);
        ccmNino.setOjosHundido(false);
        ccmNino.setSignoPliegue(false);
        ccmNino.setRuidosRespirar(false);
        ccmNino.setPopuSangre(false);
        ccmNino.setInquietoIrritable(false);
        ccmNino.setBebeMuchaSed(false);
        ccmNino.setPresentaDeshidratacion(false);
        ccmNino.setCostadoCaliente(false);


        if (tbn_DificilDespertar.isChecked()) {
            ccmNino.setDificilDespertar(true);
        }

        if (tbn_NoPuedeTomarPecho.isChecked()) {
            ccmNino.setNoPuedeTomarPecho(true);
        }

        if (tbn_VomitaTodo.isChecked()) {
            ccmNino.setVomitaTodo(true);
        }

        if (tbn_Ataques.isChecked()) {
            ccmNino.setAtaques(true);
        }

        // Neumonia Grave

        if (tbn_HundePiel.isChecked()) {
            ccmNino.setHundePiel(true);
        }

        if (tbn_RuidosRespirar.isChecked()) {
            ccmNino.setRuidosRespirar(true);
        }

        // Neumonia no Complicada

        if (tbn_RespiracionRapida.isChecked()) {
            ccmNino.setRespiracionRapida(true);
        }

        // Tos o Catarro

        if (tbn_SignoNeumonia.isChecked()) {
            ccmNino.setSignoNeumonia(true);
        }

        if (tbn_TosCatarro.isChecked()) {
            ccmNino.setTosCatarro(true);
        }

        if (tbn_Moco.isChecked()) {
            ccmNino.setMoco(true);
        }

        //Deshidratacion Grave o Sever

        if (tbn_MuyDormido.isChecked()) {
            ccmNino.setMuyDormido(true);
        }

        if (tbn_DejoComer.isChecked()) {
            ccmNino.setDejoComer(true);
        }

        if (tbn_OjosHundido.isChecked()) {
            ccmNino.setOjosHundido(true);
        }

        if (tbn_SignoPliegue.isChecked()) {
            ccmNino.setSignoPliegue(true);
        }


        // Diarrea Persistente

        if (tbn_DiarreMayorDias.isChecked()) {
            ccmNino.setDiarreMayorDias(true);
        }

        // diarrea o pupú con moco y sangre

        if (tbn_PopuSangre.isChecked()) {
            ccmNino.setPopuSangre(true);
        }

        //Diarrea con Deshidratacion Moderada

        if (tbn_InquietoIrritable.isChecked()) {
            ccmNino.setInquietoIrritable(true);
        }

        if (tbn_BebeMuchaSed.isChecked()) {
            ccmNino.setBebeMuchaSed(true);
        }

        //Diarrea sin Deshidratacion

        if (tbn_PresentaDeshidratacion.isChecked()) {
            ccmNino.setPresentaDeshidratacion(true);
        }

        //Fiebre

        if (tbn_CostadoCaliente.isChecked()) {
            ccmNino.setCostadoCaliente(true);
        }

        return ccmNino;

    }

    private String clasificarEnfermedad() {

        boolean enfermedad_muy_gr, Neumonia_grave, Neumonia_no_complicada, Tos_catarro, deshidratacion_grave_Severa, diarrea_persistente, diarrea_pupu_moco_sangre, diarrea_deshidratacion_moderada,
                diarrea_sin_deshidratación, fiebre;

        int intDeshidratacionGrave = 0, intDeshidratacionModerada = 0, intNeumoniaGrave = 0, intNeumoniaNoComplicada = 0, intTosOCatarro = 0, intDiarreaSinDeshidratacion = 0;

        Amoxicilina = false;
        Suero = false;
        Zinc = false;
        Furazolidona = false;

        String strResultado = "";
        strRecomendaciones = "";

        // Efermendad muy Grave

        if (tbn_DificilDespertar.isChecked() || tbn_NoPuedeTomarPecho.isChecked() || tbn_VomitaTodo.isChecked() || tbn_Ataques.isChecked()) {
            enfermedad_muy_gr = true;
            strResultado += "Enfermedad muy Grave" + ", ";
            strRecomendaciones += "Refiéralo inmediatamente al establecimiento de salud. \n";
        }

        // Neumonia Grave

        if (tbn_HundePiel.isChecked() || tbn_RuidosRespirar.isChecked()) {
            Neumonia_grave = true;
            intNeumoniaGrave += 1;
            strResultado += "Neumonía Grave" + ", ";
            Amoxicilina = true;
            strRecomendaciones += "Dele la primera dosis de Amoxicilina. \n";
            strRecomendaciones += "Refiéralo inmediatamente al establecimiento de salud. \n";
        }

        // Neumonia no Complicada

        if (tbn_RespiracionRapida.isChecked()) {
            Neumonia_no_complicada = true;
            intNeumoniaNoComplicada += 1;
            strResultado += "Neumonía no Complicada" + ", ";
            Amoxicilina = true;
            strRecomendaciones += "De consejería. \n * Pregunte si padece de alergia a algún medicamento. \n * Dele primera dosis de Amoxicilina. \n *Recomiende  continuar  con Amoxicilina tres veces al día o sea cada ocho horas por 5 días. \n Realice visita domiciliar (2 días). \n";
        }

        // Tos o Catarro

        if (tbn_SignoNeumonia.isChecked() || tbn_TosCatarro.isChecked() || tbn_Moco.isChecked()) {
            Tos_catarro = true;
            intTosOCatarro += 1;
            strResultado += "Tos o Catarro" + ", ";
            strRecomendaciones += "De consejería con lámina de tos y catarro. \n Realice visita domiciliar. \n";

        }

        //Deshidratacion Grave o Sever

        if (tbn_MuyDormido.isChecked()) {
            intDeshidratacionGrave += 1;
        }

        if (tbn_DejoComer.isChecked()) {
            intDeshidratacionGrave += 1;
            ;
        }

        if (tbn_OjosHundido.isChecked()) {
            intDeshidratacionGrave += 1;
        }

        if (tbn_SignoPliegue.isChecked()) {
            intDeshidratacionGrave += 1;
        }

        if (intDeshidratacionGrave >= 3) {
            intDeshidratacionGrave = 1;
            deshidratacion_grave_Severa = true;
            strResultado += "Deshidratación Grave o Severa" + ", ";
            Suero = true;
            strRecomendaciones += "De Suero oral . \n";
            strRecomendaciones += "Refiéralo inmediatamente al establecimiento de salud. \n";

        } else {
            intDeshidratacionGrave = 0;
        }

        // Diarrea Persistente

        if (tbn_DiarreMayorDias.isChecked()) {
            diarrea_persistente = true;
            strResultado += "Diarrea Persistente" + ", ";
            Suero = true;
            strRecomendaciones += "De Suero oral . \n";
            strRecomendaciones += "Refiéralo inmediatamente al establecimiento de salud. \n";

        }

        // diarrea o pupú con moco y sangre

        if (tbn_PopuSangre.isChecked()) {
            diarrea_pupu_moco_sangre = true;
            strResultado += "Diarrea o Popú con Moco y Sangre" + ", ";
            Zinc = true;
            Furazolidona = true;
            strRecomendaciones += "Dar consejería (Laminas beneficios del suero oral, y que está haciendo con el niño con diarrea en la casa). \n Oriente a la familia sobre la higiene (Lámina prevención de la diarrea. \n De Tableta de Zinc y Furazolidona según edad cuatro veces al día o sea cada seis horas por 5 días. \n Realizar visita domiciliar. \n";

        }

        //Diarrea con Deshidratacion Moderada

        if (tbn_InquietoIrritable.isChecked()) {
            intDeshidratacionModerada += 1;
        }

        if (tbn_BebeMuchaSed.isChecked()) {
            intDeshidratacionModerada += 1;
        }

        if (tbn_OjosHundido.isChecked()) {
            intDeshidratacionModerada += 1;
        }

        if (tbn_SignoPliegue.isChecked()) {
            intDeshidratacionModerada += 1;

        }

        if (intDeshidratacionModerada >= 2) {
            intDeshidratacionModerada = 1;
            diarrea_deshidratacion_moderada = true;
            strResultado += "Diarrea con Deshidratación Moderada" + ", ";
            Zinc = true;
            strRecomendaciones += "Dar consejería (Lámina de beneficios del suero oral, y que está haciendo con el niño con diarrea en la casa. \n" +
                    "Oriente a la familia sobre la higiene (lámina de prevención de la diarrea)\n. De tabletas de Zinc. \n Realice visita domiciliar. \n Si frecuentemente tiene diarrea... referir \n Garantizar la adecuada administración del SRO antes de que regrese a la casa. \n";
        } else {
            intDeshidratacionModerada = 0;
        }


        //Diarrea sin Deshidratacion

        if (tbn_PresentaDeshidratacion.isChecked()) {
            diarrea_sin_deshidratación = true;
            intDiarreaSinDeshidratacion += 1;
            strResultado += "Diarrea sin Deshidratación" + ", ";
            Zinc = true;

            strRecomendaciones += "Dar consejería (Lámina de beneficios del suero oral, y que está haciendo con el niño con diarrea en la casa. \n" +
                    "Oriente a la familia sobre la higiene (lámina de prevención de la diarrea)\n. De tabletas de Zinc. \n Realice visita domiciliar. \n Si frecuentemente tiene diarrea... referir \n Garantizar la adecuada administración del SRO antes de que regrese a la casa. \n";
        }

        //Fiebre

        if (tbn_CostadoCaliente.isChecked()) {
            fiebre = true;
            strResultado += "Fiebre" + ", ";
            strRecomendaciones += "Dar acetaminofén. \n Usar lámina de manejo de fiebre en la casa y cómo le está dando el acetaminofén. \n";
        }

        //Verificaciones

        if ((intNeumoniaGrave + intNeumoniaNoComplicada + intTosOCatarro) > 1) {
            strResultado = "NoAplicaNeumonia  ";
        }

        if ((intDeshidratacionGrave + intDeshidratacionModerada + intDiarreaSinDeshidratacion) > 1) {
            strResultado = "NoDeshidratacion  ";
        }

        if (strResultado.length() == 0) {
            strResultado = "NoClasificación  ";
        }

        return strResultado.substring(0, strResultado.length() - 2);

    }

    private void CargarNino(int id) {

        Nino nino = new Nino();

        try {
            nino = ninoBL.getNinoById(getActivity(), String.valueOf(id));

            lblNomNino.setText(nino.getNomNino());

            Calendar calendar1 = Calendar.getInstance();
            Date date = new Date(String.valueOf(nino.getFechaNac()));
            calendar1.setTime(date);

            calendarFechaNacNino.setTime(date);

            updateDate();
            int meses = obtenerMeses(date, calendarFechaActual.getTime());
            edt_fecatencion_Meses_Nino.setText(String.valueOf(meses));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void Mensajes(String strMensaje) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Información...");
        alertDialog.setMessage(strMensaje);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setIcon(R.mipmap.ic_save);
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContinuarCasoNinos:

                //Toast.makeText(getActivity(), clasificarEnfermedad(), Toast.LENGTH_SHORT).show();
                String strMensaje;

                strMensaje = clasificarEnfermedad();

                if (strMensaje.equals("NoAplicaNeumonia")) {
                    Mensajes("La Clasificación no puede tener 2 Tipos de Neumonia por favor revisar los sintomas!!!");
                    break;
                }

                if (strMensaje.equals("NoDeshidratacion")) {
                    Mensajes("La Clasificación no puede tener 2 Tipos de Deshidratación por favor revisar los sintomas!!!");
                    break;
                }

                if (strMensaje.equals("NoClasificación")) {
                    Mensajes("No se ha podido Clasificar la Enfermedad, por favor revise los sintomas!!!");
                    break;
                }


                //if (!ModoEdit)
                ccmNino = guardarDatosCasoNino();

                if (radio_madrebuscabps_Nino.isChecked())
                    ccmNino.setLugarAtencion("Madre busca a BPS");

                if (radio_sesion_Nino.isChecked())
                    ccmNino.setLugarAtencion("Sesion de Pesaje");

                if (radio_visitadomiciliar_Nino.isChecked())
                    ccmNino.setLugarAtencion("Visita Domiciliar");

                ccmNino.setIdNino(idNino);

                getFragmentManager().beginTransaction().replace(R.id.contenedor_detalle, new DetCasoMayoresTratamientoFragment().newInstance(ccmNino, strMensaje, strRecomendaciones, Amoxicilina, Suero, Zinc, Furazolidona), "CatCasoNinosTratamiento").addToBackStack("").commit();




                break;
        }

    }
}
