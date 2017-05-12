package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.TratamientoNino;
import Util.SQLiteHelper;

public class TratamientoNinoDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteTratamientoNinoById(Context context, String idTratamientoNinos) throws SQLException {

        Boolean flag = false;
        TratamientoNino tratamientoNino = new TratamientoNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idTratamientoNinos};

            Cursor c = myDataBase.query(TratamientoNino.TABLE_NAME, null, "IdTratamientoNinos=?", args, null, null, null);

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

    public TratamientoNino getTratamientoNinoById(Context context, String idTratamientoNinos) throws SQLException {

        TratamientoNino tratamientoNino = new TratamientoNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idTratamientoNinos};

            Cursor c = myDataBase.query(TratamientoNino.TABLE_NAME, null, "_id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    tratamientoNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoNino.setIdTratamientoNinos(c.getInt(c.getColumnIndex("IdTratamientoNinos")));
                    tratamientoNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    tratamientoNino.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
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
        return tratamientoNino;
    }

    public Boolean getExisteTratamientoNinoByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        TratamientoNino tratamientoNino = new TratamientoNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoNino.TABLE_NAME + " where " + Parametro + "",null);

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

    public TratamientoNino getTratamientoNinoByCustomer(Context context, String Parametro) throws SQLException {

        TratamientoNino tratamientoNino = new TratamientoNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoNino.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    tratamientoNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoNino.setIdTratamientoNinos(c.getInt(c.getColumnIndex("IdTratamientoNinos")));
                    tratamientoNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    tratamientoNino.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
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
        return tratamientoNino;
    }

    public static List<TratamientoNino> getAllTratamientoNinosList(Context context) throws SQLException {
        List<TratamientoNino> list = new ArrayList<TratamientoNino>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(TratamientoNino.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoNino tratamientoNino = new TratamientoNino();

                    tratamientoNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoNino.setIdTratamientoNinos(c.getInt(c.getColumnIndex("IdTratamientoNinos")));
                    tratamientoNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    tratamientoNino.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoNino);
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

    public static List<TratamientoNino> getAllTratamientoNinosListCustom(Context context, String parametro) throws SQLException {
        List<TratamientoNino> list = new ArrayList<TratamientoNino>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoNino.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoNino tratamientoNino = new TratamientoNino();

                    tratamientoNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoNino.setIdTratamientoNinos(c.getInt(c.getColumnIndex("IdTratamientoNinos")));
                    tratamientoNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    tratamientoNino.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoNino);
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

    public static ArrayList<TratamientoNino> getAllTratamientoNinosArrayList(Context context) throws SQLException {

        ArrayList<TratamientoNino> list = new ArrayList<TratamientoNino>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(TratamientoNino.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoNino tratamientoNino = new TratamientoNino();

                    tratamientoNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoNino.setIdTratamientoNinos(c.getInt(c.getColumnIndex("IdTratamientoNinos")));
                    tratamientoNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    tratamientoNino.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoNino);
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

    public static ArrayList<TratamientoNino> getAllTratamientoNinosArrayListCustom(Context context, String parametro) throws SQLException {
        ArrayList<TratamientoNino> list = new ArrayList<TratamientoNino>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoNino.TABLE_NAME + parametro,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoNino tratamientoNino = new TratamientoNino();

                    tratamientoNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoNino.setIdTratamientoNinos(c.getInt(c.getColumnIndex("IdTratamientoNinos")));
                    tratamientoNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    tratamientoNino.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoNino);
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

    public static Cursor getAllTratamientoNinosCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdTratamientoNinos", "IdTratamientoNinos", "IdCCMNino", "IdTratamiento"};

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
            c = myDataBase.rawQuery("Select * from " + TratamientoNino.TABLE_NAME + parametro,null);

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

    public int insertarTratamientoNino(Context context, TratamientoNino tratamientoNino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(TratamientoNino.TABLE_NAME, null, TratamientoNinoCV(tratamientoNino));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarTratamientoNino(Context context, TratamientoNino tratamientoNino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(TratamientoNino.TABLE_NAME, TratamientoNinoCV(tratamientoNino), "_id = " + tratamientoNino.get_id() , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int eliminarTratamientoNino(Context context, int IdTratamientoNinos) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(TratamientoNino.TABLE_NAME, "_id = " + IdTratamientoNinos , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues TratamientoNinoCV(TratamientoNino tratamientoNino){
        ContentValues values = new ContentValues();

        //values.put("_id", 	String.valueOf(tratamientoNino.get_id()));
        values.put("IdTratamientoNinos", 	String.valueOf(tratamientoNino.getIdTratamientoNinos()));
        values.put("IdCCMNino", 	String.valueOf(tratamientoNino.getIdCCMNino()));
        values.put("IdTratamiento", 	String.valueOf(tratamientoNino.getIdTratamiento()));

        return values;
    }
    //endregion

}

