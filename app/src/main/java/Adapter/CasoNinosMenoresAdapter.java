package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import DAO.NinoDao;
import DAO.TratamientoDao;
import DAO.TratamientoRecienNacidoDao;
import Entidades.CCMRecienNacido;
import Entidades.Nino;
import Entidades.Tratamiento;
import Entidades.TratamientoRecienNacido;


public class CasoNinosMenoresAdapter extends ArrayAdapter<CCMRecienNacido> {

    TratamientoRecienNacidoDao tratamientoRecienNacidoDao = new TratamientoRecienNacidoDao();
    TratamientoDao tratamientoDao = new TratamientoDao();
    NinoDao ninoDao = new NinoDao();

    public CasoNinosMenoresAdapter(Context context, ArrayList<CCMRecienNacido> ccmRecienNacidos) {
        super(context, 0, ccmRecienNacidos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CCMRecienNacido ccmRecienNacidos = getItem(position);
        TratamientoRecienNacido tratamientoRecienNacido = null;
        Tratamiento tratamiento = null;
        Nino nino = null;

        try {
            nino = ninoDao.getNinoById(getContext(), String.valueOf(ccmRecienNacidos.getIdNino()));
            tratamientoRecienNacido = tratamientoRecienNacidoDao.getTratamientoRecienNacidoByCustomer(getContext(), "IdCCMRecienNacido = " + String.valueOf(ccmRecienNacidos.get_id()));
            tratamiento = tratamientoDao.getTratamientoById(getContext(), String.valueOf(tratamientoRecienNacido.getIdTratamiento()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_caso_ninos_menores, parent, false);
        }
        // Lookup view for data population
        TextView viewEnfermedad = (TextView) convertView.findViewById(R.id.viewEnfermedad);
        TextView viewLugarAtencion = (TextView) convertView.findViewById(R.id.viewLugarAtencion);
        TextView viewFechaAtencion = (TextView) convertView.findViewById(R.id.viewFechaAtencion);
        TextView viewEdadMeses = (TextView) convertView.findViewById(R.id.viewEdadMeses);
        TextView viewTratamiento = (TextView) convertView.findViewById(R.id.viewTratamiento);
       TextView viewNomNinoCasoMenor = (TextView) convertView.findViewById(R.id.viewNomNinoCasoMenor);

        CheckBox cbxSincronizadoCasoNinosMenores = (CheckBox) convertView.findViewById(R.id.cbxSincronizadoCasoNinosMenores);

        // Populate the data into the template view using the data object

        if (ccmRecienNacidos.getNoPuedeTomarPecho())
            viewEnfermedad.setText("No puede tomar del pecho o beber");

        if (ccmRecienNacidos.getConvulsiones())
            viewEnfermedad.setText("Ha tenido convulsiones o ataques");

        if (ccmRecienNacidos.getHundePiel())
            viewEnfermedad.setText("Se le hunde la piel debajo de la costilla al respirar");

        if (ccmRecienNacidos.getRuidosRespirar())
            viewEnfermedad.setText("Ruidos raros al respirar o hervor en el pecho");

        if (ccmRecienNacidos.getRespRapida())
            viewEnfermedad.setText("Hay respiración rápida");

        if (ccmRecienNacidos.getFibre())
            viewEnfermedad.setText("Tiene temperatura alta");

        if (ccmRecienNacidos.getTemperatura())
            viewEnfermedad.setText("Tiene temperatura baja");

        if (ccmRecienNacidos.getPielOjosAmarillos())
            viewEnfermedad.setText("Tiene Piel y ojos amarillos");

        if (ccmRecienNacidos.getMovEstimulos())
            viewEnfermedad.setText("Tiene Movimiento sólo cuando está estimulado, o ningún movimiento, incluso en la estimulación");

        if (ccmRecienNacidos.getOmbligoPus())
            viewEnfermedad.setText("Hay pus que sale del cordón umbilical");

        if (ccmRecienNacidos.getPielUmbilicalRoja())
            viewEnfermedad.setText("La piel alrededor del cordón umbilical esta roja");

        if (ccmRecienNacidos.getPielGranos())
            viewEnfermedad.setText("Hay granos o abscesos en la piel llenos de pus");

        if (ccmRecienNacidos.getOjosPus())
            viewEnfermedad.setText("Hay pus saliendo de los ojos");

        if (ccmRecienNacidos.getOtra())
            viewEnfermedad.setText("Otras");


        viewLugarAtencion.setText(ccmRecienNacidos.getLugarAtencion());

        if (tratamiento.getTratamiento()==null){
            viewTratamiento.setText("No se dio Tratamiento");
        }else {
            viewTratamiento.setText(tratamiento.getTratamiento() + " - " + tratamiento.getPregunta());
        }



        if (ccmRecienNacidos.getIdCCMRecienNacido() != 0) {
            cbxSincronizadoCasoNinosMenores.setChecked(true);
        } else {
            cbxSincronizadoCasoNinosMenores.setChecked(false);
        }

        Calendar calendar1 = Calendar.getInstance();
        DateFormat formate = DateFormat.getDateInstance(DateFormat.SHORT);

        Date date = new Date(String.valueOf(ccmRecienNacidos.getFechaCCM()));
        calendar1.setTime(date);

        GregorianCalendar fechaActual = new GregorianCalendar();


       // long meses = ((fechaActual.getTimeInMillis() - calendar1.getTimeInMillis()) / 262975000) / 10;


        Date dateFechaNac = new Date(String.valueOf(nino.getFechaNac()));

        int meses = obtenerMeses(dateFechaNac, date);

        viewFechaAtencion.setText(formate.format(calendar1.getTime()).toString());

        viewEdadMeses.setText(String.valueOf(meses));

        viewNomNinoCasoMenor.setText(nino.getNomNino());


        // Return the completed view to render on screen
        return convertView;
    }

    private int obtenerMeses1(Calendar calendar) {


        java.util.GregorianCalendar jCal = new java.util.GregorianCalendar();
        java.util.GregorianCalendar jCal2 = new java.util.GregorianCalendar();

        calendar = new GregorianCalendar();

        long diferencia = jCal2.getTime().getTime()-jCal.getTime().getTime();
        double minutos = diferencia / (1000 * 60);
        long horas = (long) (minutos / 60);
        long minuto = (long) (minutos%60);
        long segundos = (long) diferencia % 1000;
        long dias = horas/24;

        //Calcular meses...
        //Crear vector para almacenar los diferentes dias maximos segun correponda
        String[] mesesAnio = new String[12];
        mesesAnio[0] = "31";
        //validacion de los años bisiestos
        if (jCal.isLeapYear(jCal.YEAR)){mesesAnio[1] = "29";}else{mesesAnio[1] = "28";}
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
        int mesActual = jCal.MONTH;
        //Restar los dias de cada mes desde la fecha de ingreso hasta que ya no queden sufcientes dias para
        // completar un mes.
        for (int i=0; i<=11; i++ ){
            //Validar año, si sumando 1 al mes actual supera el fin de año,
            // setea la variable a principio de año
            if ((mesActual+1)>=12){
                mesActual = i;
            }
            //Validar que el numero de dias resultantes de la resta de las 2 fechas, menos los dias
            //del mes correspondiente sea mayor a cero, de ser asi totalMeses aumenta,continuar hasta
            //que ya nos se cumpla.
            if ((diasRestantes - Integer.parseInt(mesesAnio[mesActual]))>=0){
                totalMeses ++;
                diasRestantes = diasRestantes- Integer.parseInt(mesesAnio[mesActual]);
                mesActual ++;
            }else{
                break;
            }
        }
        //Resto de horas despues de sacar los dias
        horas = horas % 24;
        String salida ="";
        if (totalMeses > 0){
            if (totalMeses > 1)
                salida = salida+  String.valueOf(totalMeses)+" Meses,  ";
            else
                salida = salida+  String.valueOf(totalMeses)+" Mes, ";
        }
        if (diasRestantes > 0){
            if (diasRestantes > 1)
                salida = salida+  String.valueOf(diasRestantes)+" Dias, ";
            else
                salida = salida+  String.valueOf(diasRestantes)+" Dia, ";
        }

        return  1;

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
}
