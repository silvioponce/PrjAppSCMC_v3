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

import DAO.ComunidadDao;
import DAO.DepartamentoDao;
import DAO.MunicipioDao;
import Entidades.Comunidad;
import Entidades.Departamento;
import Entidades.Municipio;
import Entidades.Nino;


public class NinosAdapter extends ArrayAdapter<Nino> {

    ComunidadDao comunidadDao = new ComunidadDao();
    MunicipioDao municipioDao = new MunicipioDao();
    DepartamentoDao departamentoDao = new DepartamentoDao();

    public NinosAdapter(Context context, ArrayList<Nino> nino) {
        super(context, 0, nino);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Nino nino = getItem(position);
        Comunidad comunidad = null;
        Municipio municipio = null;
        Departamento departamento = null;

        try {
            comunidad = comunidadDao.getComunidadById(getContext(), String.valueOf(nino.getIdComunidad()));
            municipio = municipioDao.getMunicipioById(getContext(), String.valueOf(comunidad.getIdMunicipio()));
            departamento = departamentoDao.getDepartamentoById(getContext(), String.valueOf(municipio.getIdDepartamento()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_ninos, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.viewNomNino);
        TextView tvMadre = (TextView) convertView.findViewById(R.id.viewNomMadre);
        TextView tvFechaNac = (TextView) convertView.findViewById(R.id.viewFechaNac);
        TextView tvSexo = (TextView) convertView.findViewById(R.id.viewSexo);
        TextView tvComunidad = (TextView) convertView.findViewById(R.id.viewComunidad);
        TextView tvDepartamento = (TextView) convertView.findViewById(R.id.viewDepartamento);
        TextView tvMunicipio = (TextView) convertView.findViewById(R.id.viewMunicipio);

        CheckBox cbxSincronizado = (CheckBox) convertView.findViewById(R.id.cbxSincronizado);

        // Populate the data into the template view using the data object
        tvName.setText(nino.getNomNino());
        tvMadre.setText(nino.getNomMadre());
        //tvFechaNac.setText(nino.getFechaNac().getDate());
        tvSexo.setText(nino.getSexo());
        tvComunidad.setText(comunidad.getNomComunidad());
        tvMunicipio.setText(municipio.getNomMunicipio());
        tvDepartamento.setText(departamento.getNomDepartamento());

        if (nino.getIdNino() != 0) {
            cbxSincronizado.setChecked(true);
        } else {
            cbxSincronizado.setChecked(false);
        }

        Calendar calendar1 = Calendar.getInstance();
        DateFormat formate = DateFormat.getDateInstance(DateFormat.SHORT);

        Date date = new Date(String.valueOf(nino.getFechaNac()));
        calendar1.setTime(date);

        tvFechaNac.setText(formate.format(calendar1.getTime()));

        // Return the completed view to render on screen
        return convertView;
    }
}
