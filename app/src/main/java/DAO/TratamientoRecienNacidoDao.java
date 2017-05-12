package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.TratamientoRecienNacido;
import Util.SQLiteHelper;

public class TratamientoRecienNacidoDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteTratamientoRecienNacidoById(Context context, String idTratamientoReciendNacido) throws SQLException {

        Boolean flag = false;
        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idTratamientoReciendNacido};

            Cursor c = myDataBase.query(TratamientoRecienNacido.TABLE_NAME, null, "IdTratamientoReciendNacido=?", args, null, null, null);

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

    public TratamientoRecienNacido getTratamientoRecienNacidoById(Context context, String idTratamientoReciendNacido) throws SQLException {

        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idTratamientoReciendNacido};

            Cursor c = myDataBase.query(TratamientoRecienNacido.TABLE_NAME, null, "_id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    tratamientoRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoRecienNacido.setIdTratamientoReciendNacido(c.getInt(c.getColumnIndex("IdTratamientoReciendNacido")));
                    tratamientoRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    tratamientoRecienNacido.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
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
        return tratamientoRecienNacido;
    }

    public Boolean getExisteTratamientoRecienNacidoByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoRecienNacido.TABLE_NAME + " where " + Parametro + "",null);

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

    public TratamientoRecienNacido getTratamientoRecienNacidoByCustomer(Context context, String Parametro) throws SQLException {

        TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoRecienNacido.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    tratamientoRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoRecienNacido.setIdTratamientoReciendNacido(c.getInt(c.getColumnIndex("IdTratamientoReciendNacido")));
                    tratamientoRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    tratamientoRecienNacido.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));
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
        return tratamientoRecienNacido;
    }

    public static List<TratamientoRecienNacido> getAllTratamientoRecienNacidoList(Context context) throws SQLException {
        List<TratamientoRecienNacido> list = new ArrayList<TratamientoRecienNacido>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(TratamientoRecienNacido.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

                    tratamientoRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoRecienNacido.setIdTratamientoReciendNacido(c.getInt(c.getColumnIndex("IdTratamientoReciendNacido")));
                    tratamientoRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    tratamientoRecienNacido.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoRecienNacido);
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

    public static List<TratamientoRecienNacido> getAllTratamientoRecienNacidoListCustom(Context context, String parametro) throws SQLException {
        List<TratamientoRecienNacido> list = new ArrayList<TratamientoRecienNacido>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoRecienNacido.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

                    tratamientoRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoRecienNacido.setIdTratamientoReciendNacido(c.getInt(c.getColumnIndex("IdTratamientoReciendNacido")));
                    tratamientoRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    tratamientoRecienNacido.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoRecienNacido);
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

    public static ArrayList<TratamientoRecienNacido> getAllTratamientoRecienNacidoArrayList(Context context) throws SQLException {

        ArrayList<TratamientoRecienNacido> list = new ArrayList<TratamientoRecienNacido>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(TratamientoRecienNacido.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

                    tratamientoRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoRecienNacido.setIdTratamientoReciendNacido(c.getInt(c.getColumnIndex("IdTratamientoReciendNacido")));
                    tratamientoRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    tratamientoRecienNacido.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoRecienNacido);
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

    public static ArrayList<TratamientoRecienNacido> getAllTratamientoRecienNacidoArrayListCustom(Context context, String parametro) throws SQLException {
        ArrayList<TratamientoRecienNacido> list = new ArrayList<TratamientoRecienNacido>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + TratamientoRecienNacido.TABLE_NAME + parametro,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();

                    tratamientoRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    tratamientoRecienNacido.setIdTratamientoReciendNacido(c.getInt(c.getColumnIndex("IdTratamientoReciendNacido")));
                    tratamientoRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    tratamientoRecienNacido.setIdTratamiento(c.getInt(c.getColumnIndex("IdTratamiento")));

                    list.add(tratamientoRecienNacido);
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

    public static Cursor getAllTratamientoRecienNacidoCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdTratamientoReciendNacido", "IdTratamientoReciendNacido", "IdCCMRecienNacido", "IdTratamiento"};

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
            c = myDataBase.rawQuery("Select * from " + TratamientoRecienNacido.TABLE_NAME + parametro,null);

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

    public int insertarTratamientoRecienNacido(Context context, TratamientoRecienNacido tratamientoRecienNacido) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(TratamientoRecienNacido.TABLE_NAME, null, TratamientoRecienNacidoCV(tratamientoRecienNacido));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarTratamientoRecienNacido(Context context, TratamientoRecienNacido tratamientoRecienNacido) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(TratamientoRecienNacido.TABLE_NAME, TratamientoRecienNacidoCV(tratamientoRecienNacido), "_id = " + tratamientoRecienNacido.get_id() , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }


    public int eliminarTratamientoRecienNacido(Context context, int idTratamientoRecienNacido) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(TratamientoRecienNacido.TABLE_NAME, "_id = " + idTratamientoRecienNacido , null);

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues TratamientoRecienNacidoCV(TratamientoRecienNacido tratamientoRecienNacido){
        ContentValues values = new ContentValues();

        //values.put("_id", 	String.valueOf(tratamientoRecienNacido.get_id()));
        values.put("IdTratamientoReciendNacido", 	String.valueOf(tratamientoRecienNacido.getIdTratamientoReciendNacido()));
        values.put("IdCCMRecienNacido", 	String.valueOf(tratamientoRecienNacido.getIdCCMRecienNacido()));
        values.put("IdTratamiento", 	String.valueOf(tratamientoRecienNacido.getIdTratamiento()));

        return values;
    }
    //endregion

}

