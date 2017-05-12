package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Rol;
import Util.SQLiteHelper;

public class RolDao {

    public Boolean getExisteRolById(Context context, String idRol) throws SQLException {

        Boolean flag = false;
        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idRol};

            Cursor c = myDataBase.query(Rol.TABLE_NAME, null, "IdRol=?", args, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception ex) {

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }

    public Rol getRolById(Context context, String idRol) throws SQLException {

        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idRol};

            Cursor c = myDataBase.query(Rol.TABLE_NAME, null, "IdRol=?", args, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    rol.setIdRol(Integer.valueOf(c.getString(c.getColumnIndex("IdRol"))));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return rol;
    }

    public Boolean getExisteRolByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Rol.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception ex) {

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }

    public Rol getRolByCustomer(Context context, String Parametro) throws SQLException {

        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Rol.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    rol.setIdRol(Integer.valueOf(c.getString(c.getColumnIndex("IdRol"))));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return rol;
    }

    public static List<Rol> getAllRoles(Context context) throws SQLException {
        String[] parametro = { String.valueOf(1) };
        List<Rol> list = new ArrayList<Rol>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Rol.TABLE_NAME, null, null , parametro,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Rol rol = new Rol();

                    rol.setIdRol(Integer.valueOf(c.getString(c.getColumnIndex("IdRol"))));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));

                    list.add(rol);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }

    public Boolean insertarRol(Context context, Rol rol) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.insert(Rol.TABLE_NAME, null, RolCV(rol)) != -1)
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

    public Boolean actualizarRol(Context context, Rol rol) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.update(Rol.TABLE_NAME, RolCV(rol), "_IdRol = " + rol.getIdRol() , null) != -1)
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

    public ContentValues RolCV(Rol rol){
        ContentValues values = new ContentValues();

        values.put("_IdRol", 	rol.get_IdRol());
        values.put("IdRol", 	rol.getIdRol());
        values.put("NomRol", 	rol.getNomRol());
        values.put("DesRol", 	rol.getDesRol());

        return values;
    }

}

