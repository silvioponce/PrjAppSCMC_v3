package BL;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.TratamientoNinoDao;
import Entidades.TratamientoNino;

public class TratamientoNinoBL {

    TratamientoNinoDao tratamientoNinoDao = new TratamientoNinoDao();

    public int GuardarTratamientoNino(Context context, TratamientoNino tratamientoNino) throws SQLException {
        int resultado = 0;
        int idTratamientoNino, idTratamiento;

        Boolean existe = false;
        existe = tratamientoNinoDao.getExisteTratamientoNinoByCustomer(context, "IdCCMNino = " + tratamientoNino.getIdCCMNino() + " and IdTratamiento = " + tratamientoNino.getIdTratamiento());

        if (existe) {
            idTratamientoNino = tratamientoNinoDao.getTratamientoNinoByCustomer(context, "IdCCMNino = " + tratamientoNino.getIdCCMNino() + " and IdTratamiento = " + tratamientoNino.getIdTratamiento()).get_id();
            tratamientoNino.set_id(idTratamientoNino);
            resultado = tratamientoNinoDao.actualizarTratamientoNino(context, tratamientoNino);

        } else {
            resultado = tratamientoNinoDao.insertarTratamientoNino(context, tratamientoNino);
        }
        return resultado;
    }

    public int eliminarTratamientoNino(Context context, TratamientoNino tratamientoNino) throws SQLException {
        int id = tratamientoNinoDao.getTratamientoNinoByCustomer(context, "IdCCMNino = " + tratamientoNino.getIdCCMNino() + " and IdTratamiento = " + tratamientoNino.getIdTratamiento()).get_id();
        return tratamientoNinoDao.eliminarTratamientoNino(context, id);
    }

    public TratamientoNino getTratamientoNinoByCustomer(Context context, String parametro) throws SQLException {
        return tratamientoNinoDao.getTratamientoNinoByCustomer(context, parametro);
    }

    public List<TratamientoNino> getAllTratamientoNinosListCustom (Context context, String strParametro){
        List<TratamientoNino> listTratatamientoNino = new ArrayList<TratamientoNino>();
        try {
            listTratatamientoNino =  tratamientoNinoDao.getAllTratamientoNinosListCustom(context,strParametro);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTratatamientoNino;
    }

}
