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

import DAO.CCMRecienNacidoDao;
import DAO.NinoDao;
import DAO.TratamientoDao;
import DAO.TratamientoRecienNacidoDao;
import Entidades.CCMRecienNacido;
import Entidades.Nino;
import Entidades.Tratamiento;
import Entidades.TratamientoRecienNacido;
import Entidades.VisitasNinosMenor;


public class VisitaNinosMenoresAdapter extends ArrayAdapter<VisitasNinosMenor> {

    TratamientoRecienNacidoDao tratamientoRecienNacidoDao = new TratamientoRecienNacidoDao();
    TratamientoDao tratamientoDao = new TratamientoDao();
    NinoDao ninoDao = new NinoDao();
    CCMRecienNacidoDao ccmRecienNacidoDao = new CCMRecienNacidoDao();

    public VisitaNinosMenoresAdapter(Context context, ArrayList<VisitasNinosMenor> visitasNinosMenor) {
        super(context, 0, visitasNinosMenor);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VisitasNinosMenor visitasNinosMenor = getItem(position);
        CCMRecienNacido ccmRecienNacidos = new CCMRecienNacido();
        TratamientoRecienNacido tratamientoRecienNacido = null;
        Tratamiento tratamiento = null;
        Nino nino = null;

        try {
            ccmRecienNacidos = ccmRecienNacidoDao.getCCMRecienNacidoById(getContext(), String.valueOf(visitasNinosMenor.getIdCCMRecienNacido()));
            nino = ninoDao.getNinoById(getContext(), String.valueOf(ccmRecienNacidos.getIdNino()));
            tratamientoRecienNacido = tratamientoRecienNacidoDao.getTratamientoRecienNacidoByCustomer(getContext(), "IdCCMRecienNacido = " + String.valueOf(ccmRecienNacidos.get_id()));
            tratamiento = tratamientoDao.getTratamientoById(getContext(), String.valueOf(tratamientoRecienNacido.getIdTratamiento()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_visita_ninos_menores, parent, false);
        }
        // Lookup view for data population
        TextView viewNomNinoVisitaMenor = (TextView) convertView.findViewById(R.id.viewNomNinoVisitaMenor);
        TextView viewEnfermedadVisitaMenor = (TextView) convertView.findViewById(R.id.viewEnfermedadVisitaMenor);
        TextView viewFechaVisitaMenor = (TextView) convertView.findViewById(R.id.viewFechaVisitaMenor);
        TextView viewTratmientoVisitaMenor = (TextView) convertView.findViewById(R.id.viewTratmientoVisitaMenor);
        TextView viewEstadoVisitaMenor = (TextView) convertView.findViewById(R.id.viewEstadoVisitaMenor);

        CheckBox cbxTomoDosisVisitaMenor = (CheckBox) convertView.findViewById(R.id.cbxTomoDosisVisitaMenor);
        CheckBox cbxCumplioRefVisitaMenor = (CheckBox) convertView.findViewById(R.id.cbxCumplioRefVisitaMenor);
        CheckBox cbxSincronizadoVisitaNinosMenores = (CheckBox) convertView.findViewById(R.id.cbxSincronizadoVisitaNinosMenores);

        // Populate the data into the template view using the data object

        if (ccmRecienNacidos.getNoPuedeTomarPecho())
            viewEnfermedadVisitaMenor.setText("No puede tomar del pecho o beber");

        if (ccmRecienNacidos.getConvulsiones())
            viewEnfermedadVisitaMenor.setText("Ha tenido convulsiones o ataques");

        if (ccmRecienNacidos.getHundePiel())
            viewEnfermedadVisitaMenor.setText("Se le hunde la piel debajo de la costilla al respirar");

        if (ccmRecienNacidos.getRuidosRespirar())
            viewEnfermedadVisitaMenor.setText("Ruidos raros al respirar o hervor en el pecho");

        if (ccmRecienNacidos.getRespRapida())
            viewEnfermedadVisitaMenor.setText("Hay respiración rápida");

        if (ccmRecienNacidos.getFibre())
            viewEnfermedadVisitaMenor.setText("Tiene temperatura alta");

        if (ccmRecienNacidos.getTemperatura())
            viewEnfermedadVisitaMenor.setText("Tiene temperatura baja");

        if (ccmRecienNacidos.getPielOjosAmarillos())
            viewEnfermedadVisitaMenor.setText("Tiene Piel y ojos amarillos");

        if (ccmRecienNacidos.getMovEstimulos())
            viewEnfermedadVisitaMenor.setText("Tiene Movimiento sólo cuando está estimulado, o ningún movimiento, incluso en la estimulación");

        if (ccmRecienNacidos.getOmbligoPus())
            viewEnfermedadVisitaMenor.setText("Hay pus que sale del cordón umbilical");

        if (ccmRecienNacidos.getPielUmbilicalRoja())
            viewEnfermedadVisitaMenor.setText("La piel alrededor del cordón umbilical esta roja");

        if (ccmRecienNacidos.getPielGranos())
            viewEnfermedadVisitaMenor.setText("Hay granos o abscesos en la piel llenos de pus");

        if (ccmRecienNacidos.getOjosPus())
            viewEnfermedadVisitaMenor.setText("Hay pus saliendo de los ojos");

        if (ccmRecienNacidos.getOtra())
            viewEnfermedadVisitaMenor.setText("Otras");

        if (tratamiento.getTratamiento()==null){
            viewTratmientoVisitaMenor.setText("No se dio Tratamiento");
        }else {
            viewTratmientoVisitaMenor.setText(tratamiento.getTratamiento() + " - " + tratamiento.getPregunta());
        }


        if (visitasNinosMenor.getIdVisita() != 0) {
            cbxSincronizadoVisitaNinosMenores.setChecked(true);
        } else {
            cbxSincronizadoVisitaNinosMenores.setChecked(false);
        }

        if (visitasNinosMenor.getCumpMedRect()) {
            cbxCumplioRefVisitaMenor.setChecked(true);
        } else {
            cbxCumplioRefVisitaMenor.setChecked(false);
        }

        if (visitasNinosMenor.getTomandoDosisIndicada()) {
            cbxTomoDosisVisitaMenor.setChecked(true);
        } else {
            cbxTomoDosisVisitaMenor.setChecked(false);
        }

        Calendar calendar1 = Calendar.getInstance();
        DateFormat formate = DateFormat.getDateInstance(DateFormat.SHORT);

        Date date = new Date(String.valueOf(visitasNinosMenor.getFechaVisita()));
        calendar1.setTime(date);

        viewFechaVisitaMenor.setText(formate.format(calendar1.getTime()).toString());

        viewEstadoVisitaMenor.setText(String.valueOf(visitasNinosMenor.getResultadoVisita()));

        viewNomNinoVisitaMenor.setText(nino.getNomNino());

        // Return the completed view to render on screen
        return convertView;
    }

}
