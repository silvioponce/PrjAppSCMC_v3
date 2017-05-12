package BL;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.List;

import DAO.ComunidadDao;
import Entidades.Comunidad;

/**
 * Created by sponce on 22/04/2015.
 */
public class ComunidadBL {

    ComunidadDao comunidadDao = new ComunidadDao();

    public List<Comunidad> getAllComunidadesListCustom(Context context, String paramentro) throws SQLException {

        return comunidadDao.getAllComunidadesListCustom(context, paramentro);
    }

    public Comunidad getComunidadById(Context context, String IdComunidad) throws SQLException {
        return comunidadDao.getComunidadById(context,IdComunidad);
    }

    public Cursor getAllComunidadesCursor(Context context, String parametro) throws SQLException {

        return comunidadDao.getAllComunidadesCursor(context, parametro);
    }

}
