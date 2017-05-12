package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Departamento;
import Util.SQLiteHelper;

public class DepartamentoDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteDepartamentoById(Context context, String idDepartamento) throws SQLException {

        Boolean flag = false;
        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idDepartamento};

            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, "IdDepartamento=?", args, null, null, null);

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

    public Departamento getDepartamentoById(Context context, String idDepartamento) throws SQLException {

        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idDepartamento};

            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, "IdDepartamento=?", args, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    departamento.set_IdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return departamento;
    }

    public Boolean getExisteDepartamentoByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME + " where " + Parametro + "",null);

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

    public Departamento getDepartamentoByCustomer(Context context, String Parametro) throws SQLException {

        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    departamento.set_IdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return departamento;
    }

    public static List<Departamento> getAllDepartamentosList(Context context) throws SQLException {
        List<Departamento> list = new ArrayList<Departamento>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Departamento departamento = new Departamento();

                    departamento.set_IdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));

                    list.add(departamento);
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

    public static ArrayList<Departamento> getAllDepartamentosArrayList(Context context) throws SQLException {

        ArrayList<Departamento> list = new ArrayList<Departamento>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Departamento departamento = new Departamento();

                    departamento.set_IdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setIdDepartamento(Integer.valueOf(c.getString(c.getColumnIndex("IdDepartamento"))));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));

                    list.add(departamento);
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

    public static Cursor getAllDepartamentosCursor(Context context){
        String[] parametro = { String.valueOf(1) };
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdDepartamento", "IdDepartamento", "NomDepartamento"};
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            //c = myDataBase.query(Departamento.TABLE_NAME, null, null , null, null, null, null);
            c = myDataBase.rawQuery("Select rowid _id, * from " + Departamento.TABLE_NAME,null);
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
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return c;
    }

    public List<String> getAllDepartamentosListString(Context context){
        List<String> labels = new ArrayList<String>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        // Select All Query
        String selectQuery = "Select * from " + Departamento.TABLE_NAME;

        myDbHelper.openDataBase();
        myDataBase = myDbHelper.getReadableDatabase();
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        myDataBase.close();

        // returning lables
        return labels;
    }

    public Boolean insertarDepartamento(Context context, Departamento departamento) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.insert(Departamento.TABLE_NAME, null, DepartamentoCV(departamento)) != -1)
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

    public Boolean actualizarDepartamento(Context context, Departamento departamento) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.update(Departamento.TABLE_NAME, DepartamentoCV(departamento), "_IdDepartamento = " + departamento.getIdDepartamento() , null) != -1)
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

    public ContentValues DepartamentoCV(Departamento departamento){
        ContentValues values = new ContentValues();

        values.put("_IdDepartamento", 	departamento.get_IdDepartamento());
        values.put("IdDepartamento", 	departamento.getIdDepartamento());
        values.put("NomDepartamento", 	departamento.getNomDepartamento());

        return values;
    }
    //endregion

}

