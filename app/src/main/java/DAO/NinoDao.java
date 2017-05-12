package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Nino;
import Util.SQLiteHelper;

public class NinoDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteNinoById(Context context, String idNino) throws SQLException {

        Boolean flag = false;
        Nino nino = new Nino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idNino};

            Cursor c = myDataBase.query(Nino.TABLE_NAME, null, "IdNino=?", args, null, null, null);

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

    public Nino getNinoById(Context context, String idNino) throws SQLException {

        Nino nino = new Nino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idNino};

            Cursor c = myDataBase.query(Nino.TABLE_NAME, null, "_id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    nino.set_id(c.getInt(c.getColumnIndex("_id")));
                    nino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    nino.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    nino.setFechaRegistro(new java.util.Date(c.getString(c.getColumnIndex("FechaRegistro"))));
                    nino.setNomMadre(String.valueOf(c.getString(c.getColumnIndex("NomMadre"))));
                    nino.setNomNino(String.valueOf(c.getString(c.getColumnIndex("NomNino"))));
                    nino.setFechaNac(new java.util.Date(c.getString(c.getColumnIndex("FechaNac"))));
                    nino.setSexo(String.valueOf(c.getString(c.getColumnIndex("Sexo"))));
                    nino.setPesoMas2500(Boolean.valueOf(c.getString(c.getColumnIndex("PesoMas2500"))));
                    nino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return nino;
    }

    public Boolean getExisteNinoByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        Nino nino = new Nino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Nino.TABLE_NAME + " where " + Parametro + "",null);

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

    public Nino getNinoByCustomer(Context context, String Parametro) throws SQLException {

        Nino nino = new Nino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Nino.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    nino.set_id(c.getInt(c.getColumnIndex("_id")));
                    nino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    nino.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    nino.setFechaRegistro(new java.util.Date(c.getString(c.getColumnIndex("FechaRegistro"))));
                    nino.setNomMadre(String.valueOf(c.getString(c.getColumnIndex("NomMadre"))));
                    nino.setNomNino(String.valueOf(c.getString(c.getColumnIndex("NomNino"))));
                    nino.setFechaNac(new java.util.Date(c.getString(c.getColumnIndex("FechaNac"))));
                    nino.setSexo(String.valueOf(c.getString(c.getColumnIndex("Sexo"))));
                    nino.setPesoMas2500(Boolean.valueOf(c.getString(c.getColumnIndex("PesoMas2500"))));
                    nino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return nino;
    }

    public static List<Nino> getAllNinosList(Context context) throws SQLException {
        List<Nino> list = new ArrayList<Nino>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Nino.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Nino nino = new Nino();

                    nino.set_id(c.getInt(c.getColumnIndex("_id")));
                    nino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    nino.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    nino.setFechaRegistro(new java.util.Date(c.getString(c.getColumnIndex("FechaRegistro"))));
                    nino.setNomMadre(String.valueOf(c.getString(c.getColumnIndex("NomMadre"))));
                    nino.setNomNino(String.valueOf(c.getString(c.getColumnIndex("NomNino"))));
                    nino.setFechaNac(new java.util.Date(c.getString(c.getColumnIndex("FechaNac"))));
                    nino.setSexo(String.valueOf(c.getString(c.getColumnIndex("Sexo"))));
                    nino.setPesoMas2500(Boolean.valueOf(c.getString(c.getColumnIndex("PesoMas2500"))));
                    nino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(nino);
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

    public static List<Nino> getAllNinosListCustom(Context context, String parametro) throws SQLException {
        List<Nino> list = new ArrayList<Nino>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Nino.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Nino nino = new Nino();

                    nino.set_id(c.getInt(c.getColumnIndex("_id")));
                    nino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    nino.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    nino.setFechaRegistro(new java.util.Date(c.getString(c.getColumnIndex("FechaRegistro"))));
                    nino.setNomMadre(String.valueOf(c.getString(c.getColumnIndex("NomMadre"))));
                    nino.setNomNino(String.valueOf(c.getString(c.getColumnIndex("NomNino"))));
                    nino.setFechaNac(new java.util.Date(c.getString(c.getColumnIndex("FechaNac"))));
                    nino.setSexo(String.valueOf(c.getString(c.getColumnIndex("Sexo"))));
                    nino.setPesoMas2500(Boolean.valueOf(c.getString(c.getColumnIndex("PesoMas2500"))));
                    nino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(nino);
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

    public static ArrayList<Nino> getAllNinosArrayList(Context context) throws SQLException {

        ArrayList<Nino> list = new ArrayList<Nino>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Nino.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Nino nino = new Nino();

                    nino.set_id(c.getInt(c.getColumnIndex("_id")));
                    nino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    nino.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    nino.setFechaRegistro(new java.util.Date(c.getString(c.getColumnIndex("FechaRegistro"))));
                    nino.setNomMadre(String.valueOf(c.getString(c.getColumnIndex("NomMadre"))));
                    nino.setNomNino(String.valueOf(c.getString(c.getColumnIndex("NomNino"))));
                    nino.setFechaNac(new java.util.Date(c.getString(c.getColumnIndex("FechaNac"))));
                    nino.setSexo(String.valueOf(c.getString(c.getColumnIndex("Sexo"))));
                    nino.setPesoMas2500(Boolean.valueOf(c.getString(c.getColumnIndex("PesoMas2500"))));
                    nino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(nino);
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

    public static ArrayList<Nino> getAllNinosArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<Nino> list = new ArrayList<Nino>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Nino.TABLE_NAME + parametro + orderBy,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Nino nino = new Nino();

                    nino.set_id(c.getInt(c.getColumnIndex("_id")));
                    nino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    nino.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    nino.setNomMadre(String.valueOf(c.getString(c.getColumnIndex("NomMadre"))));
                    nino.setNomNino(String.valueOf(c.getString(c.getColumnIndex("NomNino"))));
                    nino.setFechaNac(new java.util.Date(c.getString(c.getColumnIndex("FechaNac"))));
                    nino.setSexo(String.valueOf(c.getString(c.getColumnIndex("Sexo"))));
                    nino.setPesoMas2500(Boolean.valueOf(c.getString(c.getColumnIndex("PesoMas2500"))));
                    nino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(nino);
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

    public static Cursor getAllNinosCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdNino", "IdNino", "IdComunidad", "FechaRegistro", "NomMadre", "NomNino", "FechaNac", "Sexo", "PesoMas2500", "IdUsuario"};

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
            c = myDataBase.rawQuery("Select * from " + Nino.TABLE_NAME + parametro,null);

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

    public int insertarNino(Context context, Nino nino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(Nino.TABLE_NAME, null, NinoCV(nino));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarNino(Context context, Nino nino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(Nino.TABLE_NAME, NinoCV(nino), "_id = " + nino.get_id() , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int eliminarNino(Context context, int IdNino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(Nino.TABLE_NAME, "_id = " + IdNino , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues NinoCV(Nino nino){
        ContentValues values = new ContentValues();

        //values.put("_id", 	String.valueOf(nino.get_id()));
        values.put("IdNino", 	String.valueOf(nino.getIdNino()));
        values.put("IdComunidad", 	String.valueOf(nino.getIdComunidad()));
        values.put("FechaRegistro", 	String.valueOf(nino.getFechaRegistro()));
        values.put("NomMadre", 	String.valueOf(nino.getNomMadre()));
        values.put("NomNino", 	String.valueOf(nino.getNomNino()));
        values.put("FechaNac", 	String.valueOf(nino.getFechaNac()));
        values.put("Sexo", 	String.valueOf(nino.getSexo()));
        values.put("PesoMas2500", 	String.valueOf(nino.getPesoMas2500()));
        values.put("IdUsuario", 	String.valueOf(nino.getIdUsuario()));

        return values;
    }
    //endregion

}

