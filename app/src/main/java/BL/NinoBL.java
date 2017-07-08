package BL;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.NinoDao;
import Entidades.Nino;

/**
 * Created by sponce on 27/04/2015.
 */
public class NinoBL {
    NinoDao ninoDao = new NinoDao();

    public ArrayList<Nino> getAllNinosArrayList(Context context) throws SQLException {

        return ninoDao.getAllNinosArrayList(context);
    }

    public ArrayList<Nino> getAllNinosArrayListCustom(Context context, String strWhere, String strOrderBy) throws SQLException {

        return ninoDao.getAllNinosArrayListCustom(context, strWhere, strOrderBy);
    }

    public ArrayList<Nino> getAllNinosByName(Context context, String parametro) throws SQLException {

        return ninoDao.getAllNinosArrayListCustom(context, "NomNino like '%" + parametro + "%'", "_id");
    }

    public int GuardarNino(Context context, Nino nino) throws SQLException {
        if (nino.get_id() != 0) {
            return ninoDao.actualizarNino(context, nino);
        } else {
            return ninoDao.insertarNino(context, nino);
        }

    }

    public Nino getNinoById(Context context, String IdNino) throws SQLException {
        return ninoDao.getNinoById(context, IdNino);
    }

    public boolean getExisteNinoByCustomer(Context context, String parametro)throws SQLException {
        return ninoDao.getExisteNinoByCustomer(context, parametro);
    }
}
