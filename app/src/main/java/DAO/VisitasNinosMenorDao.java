package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.VisitasNinosMenor;
import Util.SQLiteHelper;

public class VisitasNinosMenorDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteVisitasNinosMenorById(Context context, String idVisita) throws SQLException {

        Boolean flag = false;
        VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idVisita};

            Cursor c = myDataBase.query(VisitasNinosMenor.TABLE_NAME, null, "IdVisita=?", args, null, null, null);

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

    public VisitasNinosMenor getVisitasNinosMenorById(Context context, String idVisita) throws SQLException {

        VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idVisita};

            Cursor c = myDataBase.query(VisitasNinosMenor.TABLE_NAME, null, "_id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    visitasNinosMenor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMenor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMenor.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    visitasNinosMenor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMenor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMenor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMenor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMenor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMenor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return visitasNinosMenor;
    }

    public Boolean getExisteVisitasNinosMenorByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMenor.TABLE_NAME + " where " + Parametro + "",null);

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

    public VisitasNinosMenor getVisitasNinosMenorByCustomer(Context context, String Parametro) throws SQLException {

        VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMenor.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    visitasNinosMenor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMenor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMenor.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    visitasNinosMenor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMenor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMenor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMenor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMenor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMenor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return visitasNinosMenor;
    }

    public static List<VisitasNinosMenor> getAllVisitasNinosMenoresList(Context context) throws SQLException {
        List<VisitasNinosMenor> list = new ArrayList<VisitasNinosMenor>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(VisitasNinosMenor.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

                    visitasNinosMenor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMenor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMenor.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    visitasNinosMenor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMenor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMenor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMenor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMenor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMenor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMenor);
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

    public static List<VisitasNinosMenor> getAllVisitasNinosMenoresListCustom(Context context, String parametro) throws SQLException {
        List<VisitasNinosMenor> list = new ArrayList<VisitasNinosMenor>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMenor.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

                    visitasNinosMenor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMenor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMenor.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    visitasNinosMenor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMenor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMenor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMenor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMenor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMenor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMenor);
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

    public static ArrayList<VisitasNinosMenor> getAllVisitasNinosMenoresArrayList(Context context) throws SQLException {

        ArrayList<VisitasNinosMenor> list = new ArrayList<VisitasNinosMenor>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(VisitasNinosMenor.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

                    visitasNinosMenor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMenor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMenor.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    visitasNinosMenor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMenor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMenor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMenor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMenor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMenor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMenor);
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

    public static ArrayList<VisitasNinosMenor> getAllVisitasNinosMenoresArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<VisitasNinosMenor> list = new ArrayList<VisitasNinosMenor>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        if (parametro!=null)
        {
            parametro = " where " + parametro;
        }
        else {
            parametro = "";
        }

        if (orderBy!=null)
        {
            orderBy = " Order By " + orderBy + " DESC" ;
        }
        else {
            orderBy = "";
        }

        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.rawQuery("Select * from " + VisitasNinosMenor.TABLE_NAME + parametro + orderBy,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    VisitasNinosMenor visitasNinosMenor = new VisitasNinosMenor();

                    visitasNinosMenor.set_id(c.getInt(c.getColumnIndex("_id")));
                    visitasNinosMenor.setIdVisita(c.getInt(c.getColumnIndex("IdVisita")));
                    visitasNinosMenor.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    visitasNinosMenor.setFechaVisita(new java.util.Date(c.getString(c.getColumnIndex("FechaVisita"))));
                    visitasNinosMenor.setCumpMedRect(Boolean.valueOf(c.getString(c.getColumnIndex("CumpMedRect"))));
                    visitasNinosMenor.setTomandoDosisIndicada(Boolean.valueOf(c.getString(c.getColumnIndex("TomandoDosisIndicada"))));
                    visitasNinosMenor.setResultadoVisita(String.valueOf(c.getString(c.getColumnIndex("ResultadoVisita"))));
                    visitasNinosMenor.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    visitasNinosMenor.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(visitasNinosMenor);
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

    public static Cursor getAllVisitasNinosMenoresCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdVisita", "IdVisita", "IdCCMRecienNacido", "FechaVisita", "CumpMedRect", "TomandoDosisIndicada", "ResultadoVisita", "Observacion", "IdUsuario"};

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
            c = myDataBase.rawQuery("Select * from " + VisitasNinosMenor.TABLE_NAME + parametro,null);

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

    public int insertarVisitasNinosMenor(Context context, VisitasNinosMenor visitasNinosMenor) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(VisitasNinosMenor.TABLE_NAME, null, VisitasNinosMenorCV(visitasNinosMenor));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarVisitasNinosMenor(Context context, VisitasNinosMenor visitasNinosMenor) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(VisitasNinosMenor.TABLE_NAME, VisitasNinosMenorCV(visitasNinosMenor), "_id = " + visitasNinosMenor.get_id() , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues VisitasNinosMenorCV(VisitasNinosMenor visitasNinosMenor){
        ContentValues values = new ContentValues();

        //values.put("_id", 	String.valueOf(visitasNinosMenor.get_id()));
        values.put("IdVisita", 	String.valueOf(visitasNinosMenor.getIdVisita()));
        values.put("IdCCMRecienNacido", 	String.valueOf(visitasNinosMenor.getIdCCMRecienNacido()));
        values.put("FechaVisita", 	String.valueOf(visitasNinosMenor.getFechaVisita()));
        values.put("CumpMedRect", 	String.valueOf(visitasNinosMenor.getCumpMedRect()));
        values.put("TomandoDosisIndicada", 	String.valueOf(visitasNinosMenor.getTomandoDosisIndicada()));
        values.put("ResultadoVisita", 	String.valueOf(visitasNinosMenor.getResultadoVisita()));
        values.put("Observacion", 	String.valueOf(visitasNinosMenor.getObservacion()));
        values.put("IdUsuario", 	String.valueOf(visitasNinosMenor.getIdUsuario()));

        return values;
    }
    //endregion

}

