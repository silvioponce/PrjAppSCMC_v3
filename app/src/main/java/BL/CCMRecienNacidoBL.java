package BL;


import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CCMRecienNacidoDao;
import Entidades.CCMRecienNacido;


public class CCMRecienNacidoBL {

    CCMRecienNacidoDao ccmRecienNacidoDao = new CCMRecienNacidoDao();

    public int GuardarNino(Context context, CCMRecienNacido ccmRecienNacido1) throws SQLException {
        if (ccmRecienNacido1.get_id() != 0) {
            return ccmRecienNacidoDao.actualizarCCMRecienNacido(context, ccmRecienNacido1);

        } else {
            return ccmRecienNacidoDao.insertarCCMRecienNacido(context, ccmRecienNacido1);
        }
    }

    public ArrayList<CCMRecienNacido> getAllCCMRecienNacidosArrayList(Context context) throws SQLException {

        return ccmRecienNacidoDao.getAllCCMRecienNacidosArrayList(context);
    }

    public ArrayList<CCMRecienNacido> getAllCCMRecienNacidosArrayListCustom(Context context, String parametro) throws SQLException {

        return ccmRecienNacidoDao.getAllCCMRecienNacidosArrayListCustom(context, parametro);
    }

    public CCMRecienNacido getCCMRecienNacidoById(Context context, String idCCMRecienNacido) throws SQLException {
        return ccmRecienNacidoDao.getCCMRecienNacidoById(context, idCCMRecienNacido);
    }

    public ArrayList<CCMRecienNacido> getAllCCMRecienNacidosArrayListByNomNino(Context context, String parametro) throws SQLException {

        return ccmRecienNacidoDao.getAllCCMRecienNacidosArrayListCustom(context, "IdNino in (\n" +
                "select _id from ninos where NomNino like '%"+ parametro +"%')");
    }

    public boolean getExisteCCMRecienNacidoByCustomer(Context context, String parametro)throws SQLException {
        return ccmRecienNacidoDao.getExisteCCMRecienNacidoByCustomer(context, parametro);
    }
}
