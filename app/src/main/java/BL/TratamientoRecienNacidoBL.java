package BL;

import android.content.Context;

import java.sql.SQLException;

import DAO.TratamientoRecienNacidoDao;
import Entidades.TratamientoRecienNacido;

/**
 * Created by sponce on 03/05/2015.
 */
public class TratamientoRecienNacidoBL {

    TratamientoRecienNacidoDao tratamientoRecienNacidoDao = new TratamientoRecienNacidoDao();

    public int GuardarTratamientoRecienNacido(Context context, TratamientoRecienNacido tratamientoRecienNacido) throws SQLException {
        int resultado = 0;
        int idTratamientoRecienNacido, idTratamiento;

        Boolean existe = false;

        existe = tratamientoRecienNacidoDao.getExisteTratamientoRecienNacidoByCustomer(context, "_id = " + String.valueOf(tratamientoRecienNacido.get_id()));

        if (existe) {
            idTratamientoRecienNacido = tratamientoRecienNacidoDao.getTratamientoRecienNacidoByCustomer(context, "IdCCMRecienNacido = " + String.valueOf(tratamientoRecienNacido.getIdCCMRecienNacido())).get_id();
            tratamientoRecienNacido.setIdTratamientoReciendNacido(idTratamientoRecienNacido);
            resultado = tratamientoRecienNacidoDao.actualizarTratamientoRecienNacido(context, tratamientoRecienNacido);

        } else {

            resultado = tratamientoRecienNacidoDao.insertarTratamientoRecienNacido(context, tratamientoRecienNacido);
        }

        return resultado;

    }

    public TratamientoRecienNacido getTratamientoRecienNacidoByCustomer(Context context, String parametro) {
        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();
        try {
            tratamientoRecienNacido =  tratamientoRecienNacidoDao.getTratamientoRecienNacidoByCustomer(context, parametro);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tratamientoRecienNacido;
    }

    public int eliminarTratamientoRecienNacido(Context context, int IdCCMRecienNacido) throws SQLException {

        int id = tratamientoRecienNacidoDao.getTratamientoRecienNacidoByCustomer(context, "IdCCMRecienNacido = " + IdCCMRecienNacido).get_id();

            return tratamientoRecienNacidoDao.eliminarTratamientoRecienNacido(context, id);

    }

}
