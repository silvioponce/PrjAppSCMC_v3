package BL;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.VisitasNinosMenorDao;
import Entidades.VisitasNinosMenor;

/**
 * Created by sponce on 04/05/2015.
 */
public class VisitasNinosMenorBL {

    VisitasNinosMenorDao visitasNinosMenorDao = new VisitasNinosMenorDao();

    public int GuardarVisitaNinosMenores(Context context, VisitasNinosMenor visitasNinosMenor) throws SQLException {
        if (visitasNinosMenor.get_id() != 0) {
            return visitasNinosMenorDao.actualizarVisitasNinosMenor(context, visitasNinosMenor);
        } else {
            return visitasNinosMenorDao.insertarVisitasNinosMenor(context, visitasNinosMenor);
        }

    }

    public ArrayList<VisitasNinosMenor> getAllVisitasNinosMenoresArrayList(Context context) throws SQLException {

        return visitasNinosMenorDao.getAllVisitasNinosMenoresArrayList(context);
    }

    public ArrayList<VisitasNinosMenor> getAllVisitasNinosMenoresArrayListCustom(Context context, String strWhere, String strOrderBy) throws SQLException {

        return visitasNinosMenorDao.getAllVisitasNinosMenoresArrayListCustom(context, strWhere,strOrderBy);
    }

    public ArrayList<VisitasNinosMenor> getAllVisitasNinosMenoresArrayListByNomNino(Context context, String strWhere, String strOrderBy) throws SQLException {

        return visitasNinosMenorDao.getAllVisitasNinosMenoresArrayListCustom(context, "IdCCMRecienNacido in (select _id from CCMRecienNacidos where IdNino in (" +
                "select _id from ninos where NomNino like '%"+ strWhere +"%'))", strOrderBy);
    }

    public VisitasNinosMenor getVisitasNinosMenorById(Context context, String idVisitaNinoMenor) throws SQLException {
        return visitasNinosMenorDao.getVisitasNinosMenorById(context, idVisitaNinoMenor);
    }

    public boolean getExisteVisitasNinosMenorByCustomer(Context context, String parametro)throws SQLException {
        return visitasNinosMenorDao.getExisteVisitasNinosMenorByCustomer(context, parametro);
    }



}
