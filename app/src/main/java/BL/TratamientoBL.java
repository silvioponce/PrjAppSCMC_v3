package BL;


import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;

import DAO.TratamientoDao;
import Entidades.Tratamiento;

public class TratamientoBL {

    TratamientoDao tratamientoDao = new TratamientoDao();


    public Cursor getAllTratamientoCursor(Context context, String tratamiento, String grupoTratamiento) throws SQLException {

        return tratamientoDao.getAllTratamientoCursor(context, "Grupo = '" + grupoTratamiento + "' and Tratamiento = '"+ tratamiento +"'");
    }

    public Tratamiento getTratamientoById(Context context, String parametro) {
        Tratamiento tratamiento = new Tratamiento();
        try {
            tratamiento = tratamientoDao.getTratamientoById(context, parametro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tratamiento;
    }

    private int getPositionCursor(Cursor cursor, String column, Integer value) {
        int index = 0;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Integer id = Integer.valueOf(cursor.getString(cursor.getColumnIndex(column)));

            if (id == value) {
                index = cursor.getPosition();
            }

        }

        return index;
    }

}


