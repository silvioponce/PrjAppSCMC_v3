package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import Util.SQLiteHelper;

/**
 * Created by sponce on 07/04/2015.
 */
public class AdminDao {

    public Boolean getCreateDB(Context context) {
        Boolean resultado;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.createDataBase();
            resultado = true;
        } catch (Exception ex) {
            resultado = false;
            //throw new Exception("Error al Crear la BD");

        }

        return resultado;

    }


    public Boolean isOnline(Context context){
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        return myDbHelper.isOnline(context);

    }

}
