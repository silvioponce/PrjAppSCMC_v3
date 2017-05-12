package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Tratamiento;
import Util.SQLiteHelper;

public class TratamientoDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteTratamientoById(Context context, String idTratamiento) throws SQLException {

        Boolean flag = false;
        Tratamiento tratamiento = new Tratamiento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idTratamiento};

            Cursor c = myDataBase.query(Tratamiento.TABLE_NAME, null, "IdTratamiento=?", args, null, null, null);

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

    public Tratamiento getTratamientoById(Context context, String idTratamiento) throws SQLException {

        Tratamiento tratamiento = new Tratamiento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idTratamiento};

            Cursor c = myDataBase.query(Tratamiento.TABLE_NAME, null, "_id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    tratamiento.set_Id(c.getInt(c.getColumnIndex("_id")));
                    tratamiento.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
                    tratamiento.setPregunta(String.valueOf(c.getString(c.getColumnIndex("Pregunta"))));
                    tratamiento.setTratamiento(String.valueOf(c.getString(c.getColumnIndex("Tratamiento"))));
                    tratamiento.setGrupo(String.valueOf(c.getString(c.getColumnIndex("Grupo"))));
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
        return tratamiento;
    }

    public Boolean getExisteTratamientoByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        Tratamiento tratamiento = new Tratamiento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Tratamiento.TABLE_NAME + " where " + Parametro + "",null);

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

    public Tratamiento getTratamientoByCustomer(Context context, String Parametro) throws SQLException {

        Tratamiento tratamiento = new Tratamiento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Tratamiento.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    tratamiento.set_Id(c.getInt(c.getColumnIndex("_Id")));
                    tratamiento.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
                    tratamiento.setPregunta(String.valueOf(c.getString(c.getColumnIndex("Pregunta"))));
                    tratamiento.setTratamiento(String.valueOf(c.getString(c.getColumnIndex("Tratamiento"))));
                    tratamiento.setGrupo(String.valueOf(c.getString(c.getColumnIndex("Grupo"))));
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
        return tratamiento;
    }

    public static List<Tratamiento> getAllTratamientoList(Context context) throws SQLException {
        List<Tratamiento> list = new ArrayList<Tratamiento>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Tratamiento.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Tratamiento tratamiento = new Tratamiento();

                    tratamiento.set_Id(c.getInt(c.getColumnIndex("_Id")));
                    tratamiento.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
                    tratamiento.setPregunta(String.valueOf(c.getString(c.getColumnIndex("Pregunta"))));
                    tratamiento.setTratamiento(String.valueOf(c.getString(c.getColumnIndex("Tratamiento"))));
                    tratamiento.setGrupo(String.valueOf(c.getString(c.getColumnIndex("Grupo"))));

                    list.add(tratamiento);
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

    public static List<Tratamiento> getAllTratamientoListCustom(Context context, String parametro) throws SQLException {
        List<Tratamiento> list = new ArrayList<Tratamiento>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Tratamiento.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Tratamiento tratamiento = new Tratamiento();

                    tratamiento.set_Id(c.getInt(c.getColumnIndex("_Id")));
                    tratamiento.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
                    tratamiento.setPregunta(String.valueOf(c.getString(c.getColumnIndex("Pregunta"))));
                    tratamiento.setTratamiento(String.valueOf(c.getString(c.getColumnIndex("Tratamiento"))));
                    tratamiento.setGrupo(String.valueOf(c.getString(c.getColumnIndex("Grupo"))));

                    list.add(tratamiento);
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

    public static ArrayList<Tratamiento> getAllTratamientoArrayList(Context context) throws SQLException {

        ArrayList<Tratamiento> list = new ArrayList<Tratamiento>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Tratamiento.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Tratamiento tratamiento = new Tratamiento();

                    tratamiento.set_Id(c.getInt(c.getColumnIndex("_Id")));
                    tratamiento.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
                    tratamiento.setPregunta(String.valueOf(c.getString(c.getColumnIndex("Pregunta"))));
                    tratamiento.setTratamiento(String.valueOf(c.getString(c.getColumnIndex("Tratamiento"))));
                    tratamiento.setGrupo(String.valueOf(c.getString(c.getColumnIndex("Grupo"))));

                    list.add(tratamiento);
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

    public static ArrayList<Tratamiento> getAllTratamientoArrayListCustom(Context context, String parametro) throws SQLException {
        ArrayList<Tratamiento> list = new ArrayList<Tratamiento>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Tratamiento.TABLE_NAME + parametro,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Tratamiento tratamiento = new Tratamiento();

                    tratamiento.set_Id(c.getInt(c.getColumnIndex("_Id")));
                    tratamiento.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
                    tratamiento.setPregunta(String.valueOf(c.getString(c.getColumnIndex("Pregunta"))));
                    tratamiento.setTratamiento(String.valueOf(c.getString(c.getColumnIndex("Tratamiento"))));
                    tratamiento.setGrupo(String.valueOf(c.getString(c.getColumnIndex("Grupo"))));

                    list.add(tratamiento);
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

    public static Cursor getAllTratamientoCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_Id", "IdTratamiento", "Pregunta", "Tratamiento", "Grupo"};
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
            c = myDataBase.rawQuery("Select * from " + Tratamiento.TABLE_NAME + parametro,null);

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

    public Boolean insertarTratamiento(Context context, Tratamiento tratamiento) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.insert(Tratamiento.TABLE_NAME, null, TratamientoCV(tratamiento)) != -1)
                flag = true;

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }

    public Boolean actualizarTratamiento(Context context, Tratamiento tratamiento) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.update(Tratamiento.TABLE_NAME, TratamientoCV(tratamiento), "_IdTratamiento = " + tratamiento.getIdTratamiento() , null) != -1)
                flag = true;

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }

    public ContentValues TratamientoCV(Tratamiento tratamiento){
        ContentValues values = new ContentValues();

        //values.put("_Id", 	String.valueOf(tratamiento.get_Id()));
        values.put("IdTratamiento", 	String.valueOf(tratamiento.getIdTratamiento()));
        values.put("Pregunta", 	String.valueOf(tratamiento.getPregunta()));
        values.put("Tratamiento", 	String.valueOf(tratamiento.getTratamiento()));
        values.put("Grupo", 	String.valueOf(tratamiento.getGrupo()));

        return values;
    }
    //endregion

}

