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
import java.util.List;

import BL.CCMNinoBL;
import BL.TratamientoBL;
import BL.TratamientoNinoBL;
import DAO.CCMNinoDao;
import DAO.NinoDao;
import DAO.TratamientoDao;
import DAO.TratamientoNinoDao;
import Entidades.CCMNino;
import Entidades.Nino;
import Entidades.Tratamiento;
import Entidades.TratamientoNino;
import Entidades.VisitasNinosMayor;

public class VisitaNinosMayoresAdapter extends ArrayAdapter<VisitasNinosMayor> {
    TratamientoNinoDao tratamientoNinoDao = new TratamientoNinoDao();
    TratamientoDao tratamientoDao = new TratamientoDao();
    NinoDao ninoDao = new NinoDao();
    CCMNinoDao ccmNinoDao = new CCMNinoDao();
    TratamientoNinoBL tratamientoNinoBL = new TratamientoNinoBL();
    TratamientoBL tratamientoBL = new TratamientoBL();
    CCMNinoBL ccmNinoBL = new CCMNinoBL();

    public VisitaNinosMayoresAdapter(Context context, ArrayList<VisitasNinosMayor> visitasNinosMayores) {
        super(context, 0, visitasNinosMayores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VisitasNinosMayor visitasNinosMayor = getItem(position);
        CCMNino ccmNino = new CCMNino();
        TratamientoNino tratamientoNino = null;
        Tratamiento tratamiento = null;
        Nino nino = null;
        int intIdTratamiento;
        String strTratamiento = "";

        List<TratamientoNino> listTratatamientoNino = new ArrayList<TratamientoNino>();

        try {
            ccmNino = ccmNinoDao.getCCMNinoById(getContext(), String.valueOf(visitasNinosMayor.getIdCCMNino()));
            nino = ninoDao.getNinoById(getContext(), String.valueOf(ccmNino.getIdNino()));

            listTratatamientoNino = tratamientoNinoBL.getAllTratamientoNinosListCustom(getContext(), "IdCCMNino = " + String.valueOf(ccmNino.get_id()));

            for (int x = 0; x < listTratatamientoNino.size(); x++) {
                intIdTratamiento = listTratatamientoNino.get(x).getIdTratamiento();
                tratamiento = tratamientoBL.getTratamientoById(getContext(), String.valueOf(intIdTratamiento));

                strTratamiento += tratamiento.getTratamiento().toString() + " - " + tratamiento.getPregunta().toString() + ".\n";
            }

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

        if (tratamiento == null) {
            viewTratmientoVisitaMenor.setText("No se dio Tratamiento");
        } else {
            viewTratmientoVisitaMenor.setText(strTratamiento);
        }

        viewEnfermedadVisitaMenor.setText(ccmNinoBL.clasificarEnfermedad(ccmNino));

        if (visitasNinosMayor.getIdVisita() != 0) {
            cbxSincronizadoVisitaNinosMenores.setChecked(true);
        } else {
            cbxSincronizadoVisitaNinosMenores.setChecked(false);
        }

        if (visitasNinosMayor.getCumpMedRect()) {
            cbxCumplioRefVisitaMenor.setChecked(true);
        } else {
            cbxCumplioRefVisitaMenor.setChecked(false);
        }

        if (visitasNinosMayor.getTomandoDosisIndicada()) {
            cbxTomoDosisVisitaMenor.setChecked(true);
        } else {
            cbxTomoDosisVisitaMenor.setChecked(false);
        }

        Calendar calendar1 = Calendar.getInstance();
        DateFormat formate = DateFormat.getDateInstance(DateFormat.SHORT);

        Date date = new Date(String.valueOf(visitasNinosMayor.getFechaVisita()));
        calendar1.setTime(date);

        viewFechaVisitaMenor.setText(formate.format(calendar1.getTime()).toString());

        viewEstadoVisitaMenor.setText(String.valueOf(visitasNinosMayor.getResultadoVisita()));

        viewNomNinoVisitaMenor.setText(nino.getNomNino());

        // Return the completed view to render on screen
        return convertView;
    }
}
