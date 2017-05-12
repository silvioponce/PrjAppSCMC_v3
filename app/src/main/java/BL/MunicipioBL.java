package BL;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.List;

import DAO.MunicipioDao;
import Entidades.Municipio;

public class MunicipioBL {

    MunicipioDao municipioDao = new MunicipioDao();


    public List<Municipio> getAllMunicipiosListCustom(Context context, String paramentro) throws SQLException {

        return municipioDao.getAllMunicipiosListCustom(context, paramentro);
    }

    public Municipio getMunicipioById(Context context, String IdMunicipio) throws SQLException {
        return municipioDao.getMunicipioById(context,IdMunicipio);
    }

    public Cursor getAllMunicipiosCursor(Context context, String parametro) throws SQLException {

        return municipioDao.getAllMunicipiosCursor(context,parametro);
    }

}
