package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Municipio;
import Util.SQLiteHelper;

public class MunicipioDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteMunicipioById(Context context, String idMunicipio) throws SQLException {

        Boolean flag = false;
        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idMunicipio};

            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, "IdMunicipio=?", args, null, null, null);

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

    public Municipio getMunicipioById(Context context, String idMunicipio) throws SQLException {

        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idMunicipio};

            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, "IdMunicipio=?", args, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    municipio.set_IdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setIdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
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
        return municipio;
    }

    public Boolean getExisteMunicipioByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select rowid _id, * from " + Municipio.TABLE_NAME + " where " + Parametro + "",null);

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

    public Municipio getMunicipioByCustomer(Context context, String Parametro) throws SQLException {

        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select rowid _id, * from " + Municipio.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    municipio.set_IdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setIdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
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
        return municipio;
    }

    public static List<Municipio> getAllMunicipiosList(Context context) throws SQLException {
        List<Municipio> list = new ArrayList<Municipio>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Municipio municipio = new Municipio();

                    municipio.set_IdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setIdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));

                    list.add(municipio);
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

    public static List<Municipio> getAllMunicipiosListCustom(Context context, String parametro) throws SQLException {
        List<Municipio> list = new ArrayList<Municipio>();
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
            Cursor c = myDataBase.rawQuery("Select rowid _id, * from " + Municipio.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Municipio municipio = new Municipio();

                    municipio.set_IdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setIdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));

                    list.add(municipio);
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

    public static ArrayList<Municipio> getAllMunicipiosArrayList(Context context) throws SQLException {
        String[] parametro = { String.valueOf(1) };
        ArrayList<Municipio> list = new ArrayList<Municipio>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, null , parametro,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Municipio municipio = new Municipio();

                    municipio.set_IdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setIdMunicipio(Integer.valueOf(c.getString(c.getColumnIndex("IdMunicipio"))));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));

                    list.add(municipio);
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

    public static Cursor getAllMunicipiosCursor(Context context, String parametro) throws SQLException {

        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdMunicipio", "IdMunicipio", "NomMunicipio", "IdDepartamento"};

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
            c = myDataBase.rawQuery("Select rowid _id, * from " + Municipio.TABLE_NAME + parametro,null);

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

    public Boolean insertarMunicipio(Context context, Municipio municipio) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.insert(Municipio.TABLE_NAME, null, MunicipioCV(municipio)) != -1)
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

    public Boolean actualizarMunicipio(Context context, Municipio municipio) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.update(Municipio.TABLE_NAME, MunicipioCV(municipio), "_IdMunicipio = " + municipio.getIdMunicipio() , null) != -1)
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

    public ContentValues MunicipioCV(Municipio municipio){
        ContentValues values = new ContentValues();

        values.put("_IdMunicipio", 	municipio.get_IdMunicipio());
        values.put("IdMunicipio", 	municipio.getIdMunicipio());
        values.put("NomMunicipio", 	municipio.getNomMunicipio());
        values.put("IdDepartamento", 	municipio.getIdDepartamento());

        return values;
    }
    //endregion

}

