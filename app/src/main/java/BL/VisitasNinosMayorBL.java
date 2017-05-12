package BL;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.VisitasNinosMayorDao;
import Entidades.VisitasNinosMayor;


public class VisitasNinosMayorBL {

    VisitasNinosMayorDao visitasNinosMayorDao = new VisitasNinosMayorDao();

    public int GuardarVisitaNinosMayores(Context context, VisitasNinosMayor visitasNinosMayor) {
        int result = 0;

        try {
            if (visitasNinosMayor.get_id() != 0) {

                result = visitasNinosMayorDao.actualizarVisitasNinosMayor(context, visitasNinosMayor);
            } else {
                result = visitasNinosMayorDao.insertarVisitasNinosMayor(context, visitasNinosMayor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<VisitasNinosMayor> getAllVisitasNinosMayoresArrayList(Context context) throws SQLException {

        return visitasNinosMayorDao.getAllVisitasNinosMayoresArrayList(context);
    }

    public ArrayList<VisitasNinosMayor> getAllVisitasNinosMayoresArrayListCustom(Context context, String parametro) throws SQLException {

        return visitasNinosMayorDao.getAllVisitasNinosMayoresArrayListCustom(context, parametro);
    }

    public ArrayList<VisitasNinosMayor> getAllVisitasNinosMayoresArrayListCustomNomNino(Context context, String parametro) throws SQLException {

        return visitasNinosMayorDao.getAllVisitasNinosMayoresArrayListCustom(context, "IdCCMNino in (select _id from CCMNinos where IdNino in (" +
                "select _id from ninos where NomNino like '%" + parametro + "%'))");
    }

    public VisitasNinosMayor getVisitasNinosMayorById(Context context, String idVisitaNinoMayor) throws SQLException {
        return visitasNinosMayorDao.getVisitasNinosMayorById(context, idVisitaNinoMayor);
    }

    public boolean getExisteVisitasNinosMayorByCustomer(Context context, String parametro)throws SQLException {
        return visitasNinosMayorDao.getExisteVisitasNinosMayorByCustomer(context, parametro);
    }
}
