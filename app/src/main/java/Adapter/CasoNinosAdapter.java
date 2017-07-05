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
import java.util.List;

import BL.CCMNinoBL;
import DAO.NinoDao;
import DAO.TratamientoDao;
import DAO.TratamientoNinoDao;
import Entidades.CCMNino;
import Entidades.Nino;
import Entidades.Tratamiento;
import Entidades.TratamientoNino;


public class CasoNinosAdapter extends ArrayAdapter<CCMNino> {

    TratamientoNinoDao tratamientoNinoDao = new TratamientoNinoDao();
    TratamientoDao tratamientoDao = new TratamientoDao();
    NinoDao ninoDao = new NinoDao();

    public CasoNinosAdapter(Context context, ArrayList<CCMNino> ccmNinos) {
        super(context, 0, ccmNinos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CCMNino ccmNino = getItem(position);
        CCMNinoBL ccmNinoBL = new CCMNinoBL();
        TratamientoNino tratamientoNino = null;
        Tratamiento tratamiento = null;
        String strTratamiento = "";
        int intIdTratamiento;
        Nino nino = null;

        List<TratamientoNino> listTratatamientoNino = new ArrayList<TratamientoNino>();

        try {
            nino = ninoDao.getNinoById(getContext(), String.valueOf(ccmNino.getIdNino()));
            listTratatamientoNino = tratamientoNinoDao.getAllTratamientoNinosListCustom(getContext(), "IdCCMNino = " + String.valueOf(ccmNino.get_id()));


            for(int x=0;x<listTratatamientoNino.size();x++) {
               intIdTratamiento = listTratatamientoNino.get(x).getIdTratamiento();
                tratamiento = tratamientoDao.getTratamientoById(getContext(), String.valueOf(intIdTratamiento));

                strTratamiento += tratamiento.getTratamiento().toString() + " - " + tratamiento.getPregunta().toString();
                if (x!=listTratatamientoNino.size())
                   strTratamiento += ".\n";
            }

            //tratamiento = tratamientoDao.getTratamientoById(getContext(), String.valueOf(tratamientoNino.getIdTratamiento()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_caso_ninos, parent, false);
        }
        // Lookup view for data population
        TextView viewClasificacionEnfermedad = (TextView) convertView.findViewById(R.id.viewClasificacionEnfermedad);
        TextView viewLugarAtencionNino = (TextView) convertView.findViewById(R.id.viewLugarAtencionNino);
        TextView viewFechaAtencionNino = (TextView) convertView.findViewById(R.id.viewFechaAtencionNino);
        TextView viewEdadMesesNino = (TextView) convertView.findViewById(R.id.viewEdadMesesNino);
        TextView viewTratamientoNino = (TextView) convertView.findViewById(R.id.viewTratamientoNino);
        TextView viewNomNinoCasoNino = (TextView) convertView.findViewById(R.id.viewNomNinoCasoNino);

        CheckBox cbxSincronizadoCasoNinos = (CheckBox) convertView.findViewById(R.id.cbxSincronizadoCasoNinos);

        // Populate the data into the template view using the data object

        viewClasificacionEnfermedad.setText(ccmNinoBL.clasificarEnfermedad(ccmNino));


        viewLugarAtencionNino.setText(ccmNino.getLugarAtencion());

        if (strTratamiento == "") {
            viewTratamientoNino.setText("No se dio Tratamiento");
        } else {
            viewTratamientoNino.setText(strTratamiento);
        }


        if (ccmNino.getIdCCMNino() != 0) {
            cbxSincronizadoCasoNinos.setChecked(true);
        } else {
            cbxSincronizadoCasoNinos.setChecked(false);
        }

        Calendar calendar1 = Calendar.getInstance();
        DateFormat formate = DateFormat.getDateInstance(DateFormat.SHORT);

        Date date = new Date(String.valueOf(ccmNino.getFechaCCM()));
        calendar1.setTime(date);

        GregorianCalendar fechaActual = new GregorianCalendar();
        Calendar calfechaNac = Calendar.getInstance();
        Date fechaNac = new Date(String.valueOf(nino.getFechaNac()));
        calfechaNac.setTime(fechaNac);


        //long meses = ((calendar1.getTimeInMillis() - calfechaNac.getTimeInMillis()) / 262975000) / 10;

        int meses = obtenerMeses(fechaNac, date);

        viewFechaAtencionNino.setText(formate.format(calendar1.getTime()).toString());

        viewEdadMesesNino.setText(String.valueOf(meses));

        viewNomNinoCasoNino.setText(nino.getNomNino());

        // Return the completed view to render on screen
        return convertView;
    }


    private int obtenerMeses(Date dateInicial, Date dateFinal) {


        java.util.GregorianCalendar jCal = new java.util.GregorianCalendar();
        java.util.GregorianCalendar jCal2 = new java.util.GregorianCalendar();

        jCal.setTime(dateInicial);
        jCal2.setTime(dateFinal);

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
        int mesActual = jCal.MONTH - 1;
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

        int dato = (dateFinal.getMonth() - dateInicial.getMonth()) + ((dateFinal.getYear() - dateInicial.getYear()) * 12);

        if ((dateInicial.getDate() - dateFinal.getDate()) < 0)
        {
            //dato = dato + 1;
        }else
        {
            dato = dato - 1;
        }

        return  dato;

    }


}
