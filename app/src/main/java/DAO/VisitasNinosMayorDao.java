package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.VisitasNinosMayor;
import Util.SQLiteHelper;

public class VisitasNinosMayorDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteVisitasNinosMayorById(Context context, String idVisita) throws SQLException {

        Boolean flag = false;
        VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idVisita};

            Cursor c = myDataBase.query(VisitasNinosMayor.TABLE_NAME, null, "IdVisita=?", args, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }

    public VisitasNinosMayor getVisitasNinosMayorById(Context context, String idVisita) throws SQLException {

        VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idVisita};

            Cursor c = myDataBase.query(VisitasNinosMayor.TABLE_NAME, null, "_id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    visitasNinosMayor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMayor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMayor.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    visitasNinosMayor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMayor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMayor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMayor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMayor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMayor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return visitasNinosMayor;
    }

    public Boolean getExisteVisitasNinosMayorByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMayor.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }

    public VisitasNinosMayor getVisitasNinosMayorByCustomer(Context context, String Parametro) throws SQLException {

        VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMayor.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    visitasNinosMayor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMayor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMayor.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    visitasNinosMayor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMayor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMayor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMayor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMayor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMayor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return visitasNinosMayor;
    }

    public static List<VisitasNinosMayor> getAllVisitasNinosMayoresList(Context context) throws SQLException {
        List<VisitasNinosMayor> list = new ArrayList<VisitasNinosMayor>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(VisitasNinosMayor.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

                    visitasNinosMayor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMayor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMayor.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    visitasNinosMayor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMayor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMayor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMayor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMayor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMayor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMayor);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }

    public static List<VisitasNinosMayor> getAllVisitasNinosMayoresListCustom(Context context, String parametro) throws SQLException {
        List<VisitasNinosMayor> list = new ArrayList<VisitasNinosMayor>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        if (parametro!=null)
        {
            parametro = " where " + parametro;
        }
        else {
            parametro = "";
        }

        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMayor.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

                    visitasNinosMayor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMayor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMayor.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    visitasNinosMayor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMayor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMayor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMayor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMayor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMayor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMayor);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }

    public static ArrayList<VisitasNinosMayor> getAllVisitasNinosMayoresArrayList(Context context) throws SQLException {

        ArrayList<VisitasNinosMayor> list = new ArrayList<VisitasNinosMayor>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(VisitasNinosMayor.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

                    visitasNinosMayor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMayor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMayor.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    visitasNinosMayor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMayor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMayor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMayor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMayor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMayor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMayor);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }

    public static ArrayList<VisitasNinosMayor> getAllVisitasNinosMayoresArrayListCustom(Context context, String parametro) throws SQLException {
        ArrayList<VisitasNinosMayor> list = new ArrayList<VisitasNinosMayor>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        if (parametro!=null)
        {
            parametro = " where " + parametro;
        }
        else {
            parametro = "";
        }

        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMayor.TABLE_NAME + parametro,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMayor visitasNinosMayor = new VisitasNinosMayor();

                    visitasNinosMayor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMayor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMayor.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    visitasNinosMayor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMayor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMayor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMayor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMayor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMayor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMayor);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }

    public static Cursor getAllVisitasNinosMayoresCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdVisita", "IdVisita", "IdCCMNino", "FechaVisita", "CumpMedRect", "TomandoDosisIndicada", "ResultadoVisita", "Observacion", "IdUsuario"};

        if (parametro!=null)
        {
            parametro = " where " + parametro;
        }
        else {
            parametro = "";
        }

        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            c = myDataBase.rawQuery("Select * from " + VisitasNinosMayor.TABLE_NAME + parametro,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return c;
    }

    public int insertarVisitasNinosMayor(Context context, VisitasNinosMayor visitasNinosMayor) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(VisitasNinosMayor.TABLE_NAME, null, VisitasNinosMayorCV(visitasNinosMayor));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarVisitasNinosMayor(Context context, VisitasNinosMayor visitasNinosMayor) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(VisitasNinosMayor.TABLE_NAME, VisitasNinosMayorCV(visitasNinosMayor), "_id = " + visitasNinosMayor.get_id() , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int eliminarVisitasNinosMayor(Context context, int IdVisita) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(VisitasNinosMayor.TABLE_NAME, "_id = " + IdVisita , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues VisitasNinosMayorCV(VisitasNinosMayor visitasNinosMayor){
        ContentValues values = new ContentValues();

        //values.put("_id", 	String.valueOf(visitasNinosMayor.get_id()));
        values.put("IdVisita", 	String.valueOf(visitasNinosMayor.getIdVisita()));
        values.put("IdCCMNino", 	String.valueOf(visitasNinosMayor.getIdCCMNino()));
        values.put("FechaVisita", 	String.valueOf(visitasNinosMayor.getFechaVisita()));
        values.put("CumpMedRect", 	String.valueOf(visitasNinosMayor.getCumpMedRect()));
        values.put("TomandoDosisIndicada", 	String.valueOf(visitasNinosMayor.getTomandoDosisIndicada()));
        values.put("ResultadoVisita", 	String.valueOf(visitasNinosMayor.getResultadoVisita()));
        values.put("Observacion", 	String.valueOf(visitasNinosMayor.getObservacion()));
        values.put("IdUsuario", 	String.valueOf(visitasNinosMayor.getIdUsuario()));

        return values;
    }
    //endregion

}

