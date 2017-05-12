package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Comunidad;
import Util.SQLiteHelper;

public class ComunidadDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteComunidadById(Context context, String idComunidad) throws SQLException {

        Boolean flag = false;
        Comunidad comunidad = new Comunidad();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idComunidad};

            Cursor c = myDataBase.query(Comunidad.TABLE_NAME, null, "IdComunidad=?", args, null, null, null);

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

    public Comunidad getComunidadById(Context context, String idComunidad) throws SQLException {

        Comunidad comunidad = new Comunidad();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idComunidad};

            Cursor c = myDataBase.query(Comunidad.TABLE_NAME, null, "IdComunidad=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    comunidad.set_id(c.getInt(c.getColumnIndex("_id")));
                    comunidad.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    comunidad.setNomComunidad(String.valueOf(c.getString(c.getColumnIndex("NomComunidad"))));
                    comunidad.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
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
        return comunidad;
    }

    public Boolean getExisteComunidadByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        Comunidad comunidad = new Comunidad();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Comunidad.TABLE_NAME + " where " + Parametro + "",null);

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

    public Comunidad getComunidadByCustomer(Context context, String Parametro) throws SQLException {

        Comunidad comunidad = new Comunidad();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Comunidad.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    comunidad.set_id(c.getInt(c.getColumnIndex("_id")));
                    comunidad.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    comunidad.setNomComunidad(String.valueOf(c.getString(c.getColumnIndex("NomComunidad"))));
                    comunidad.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
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
        return comunidad;
    }

    public static List<Comunidad> getAllComunidadesList(Context context) throws SQLException {
        List<Comunidad> list = new ArrayList<Comunidad>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Comunidad.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Comunidad comunidad = new Comunidad();

                    comunidad.set_id(c.getInt(c.getColumnIndex("_id")));
                    comunidad.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    comunidad.setNomComunidad(String.valueOf(c.getString(c.getColumnIndex("NomComunidad"))));
                    comunidad.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));

                    list.add(comunidad);
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

    public static List<Comunidad> getAllComunidadesListCustom(Context context, String parametro) throws SQLException {
        List<Comunidad> list = new ArrayList<Comunidad>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Comunidad.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Comunidad comunidad = new Comunidad();

                    comunidad.set_id(c.getInt(c.getColumnIndex("_id")));
                    comunidad.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    comunidad.setNomComunidad(String.valueOf(c.getString(c.getColumnIndex("NomComunidad"))));
                    comunidad.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));

                    list.add(comunidad);
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

    public static ArrayList<Comunidad> getAllComunidadesArrayList(Context context) throws SQLException {

        ArrayList<Comunidad> list = new ArrayList<Comunidad>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Comunidad.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Comunidad comunidad = new Comunidad();

                    comunidad.set_id(c.getInt(c.getColumnIndex("_id")));
                    comunidad.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    comunidad.setNomComunidad(String.valueOf(c.getString(c.getColumnIndex("NomComunidad"))));
                    comunidad.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));

                    list.add(comunidad);
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

    public static ArrayList<Comunidad> getAllComunidadesArrayListCustom(Context context, String parametro) throws SQLException {
        ArrayList<Comunidad> list = new ArrayList<Comunidad>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Comunidad.TABLE_NAME + parametro,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Comunidad comunidad = new Comunidad();

                    comunidad.set_id(c.getInt(c.getColumnIndex("_id")));
                    comunidad.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    comunidad.setNomComunidad(String.valueOf(c.getString(c.getColumnIndex("NomComunidad"))));
                    comunidad.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));

                    list.add(comunidad);
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


    public static ArrayList<Comunidad> getAllComunidadesArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<Comunidad> list = new ArrayList<Comunidad>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Comunidad.TABLE_NAME + parametro + orderBy,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Comunidad comunidad = new Comunidad();

                    comunidad.set_id(c.getInt(c.getColumnIndex("_id")));
                    comunidad.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    comunidad.setNomComunidad(String.valueOf(c.getString(c.getColumnIndex("NomComunidad"))));
                    comunidad.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));

                    list.add(comunidad);
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

    public static Cursor getAllComunidadesCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_id", "IdComunidad", "NomComunidad", "IdMunicipio"};

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
            c = myDataBase.rawQuery("Select * from " + Comunidad.TABLE_NAME + parametro,null);

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

    public static Cursor getAllComunidadesCursor(Context context) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_id", "IdComunidad", "NomComunidad", "IdMunicipio", };

        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            c = myDataBase.rawQuery("Select * from " + Comunidad.TABLE_NAME,null);

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

    public int insertarComunidad(Context context, Comunidad comunidad) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(Comunidad.TABLE_NAME, null, ComunidadCV(comunidad));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarComunidad(Context context, Comunidad comunidad) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(Comunidad.TABLE_NAME, ComunidadCV(comunidad), "IdComunidad = " + comunidad.getIdComunidad() , null);

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int eliminarComunidad(Context context, int IdComunidad) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(Comunidad.TABLE_NAME, "_id = " + IdComunidad , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues ComunidadCV(Comunidad comunidad){
        ContentValues values = new ContentValues();

        values.put("_id", 	String.valueOf(comunidad.get_id()));
        values.put("IdComunidad", 	String.valueOf(comunidad.getIdComunidad()));
        values.put("NomComunidad", 	String.valueOf(comunidad.getNomComunidad()));
        values.put("IdMunicipio", 	String.valueOf(comunidad.getIdMunicipio()));

        return values;
    }
    //endregion

}

