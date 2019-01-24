package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.CCMRecienNacido;
import Util.SQLiteHelper;

public class CCMRecienNacidoDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteCCMRecienNacidoById(Context context, String idCCMRecienNacido) throws SQLException {

        Boolean flag = false;
        CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idCCMRecienNacido};

            Cursor c = myDataBase.query(CCMRecienNacido.TABLE_NAME, null, "IdCCMRecienNacido=?", args, null, null, null);

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

    public CCMRecienNacido getCCMRecienNacidoById(Context context, String idCCMRecienNacido) throws SQLException {

        CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idCCMRecienNacido};

            Cursor c = myDataBase.query(CCMRecienNacido.TABLE_NAME, null, "_id=?", args, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    cCMRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    cCMRecienNacido.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMRecienNacido.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMRecienNacido.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMRecienNacido.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMRecienNacido.setConvulsiones(Boolean.valueOf(c.getString(c.getColumnIndex("Convulsiones"))));
                    cCMRecienNacido.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMRecienNacido.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMRecienNacido.setRespRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespRapida"))));
                    cCMRecienNacido.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMRecienNacido.setFibre(Boolean.valueOf(c.getString(c.getColumnIndex("Fibre"))));
                    cCMRecienNacido.setTemperatura(Boolean.valueOf(c.getString(c.getColumnIndex("Temperatura"))));
                    cCMRecienNacido.setPielOjosAmarillos(Boolean.valueOf(c.getString(c.getColumnIndex("PielOjosAmarillos"))));
                    cCMRecienNacido.setMovEstimulos(Boolean.valueOf(c.getString(c.getColumnIndex("MovEstimulos"))));
                    cCMRecienNacido.setOmbligoPus(Boolean.valueOf(c.getString(c.getColumnIndex("OmbligoPus"))));
                    cCMRecienNacido.setPielUmbilicalRoja(Boolean.valueOf(c.getString(c.getColumnIndex("PielUmbilicalRoja"))));
                    cCMRecienNacido.setPielGranos(Boolean.valueOf(c.getString(c.getColumnIndex("PielGranos"))));
                    cCMRecienNacido.setOjosPus(Boolean.valueOf(c.getString(c.getColumnIndex("OjosPus"))));
                    cCMRecienNacido.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMRecienNacido.setNrespiraciones(c.getInt(c.getColumnIndex("NRespiraciones")));
                    cCMRecienNacido.setOtra(Boolean.valueOf(c.getString(c.getColumnIndex("Otra"))));
                    cCMRecienNacido.setObsevaciones(String.valueOf(c.getString(c.getColumnIndex("Obsevaciones"))));
                    cCMRecienNacido.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return cCMRecienNacido;
    }

    public Boolean getExisteCCMRecienNacidoByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + CCMRecienNacido.TABLE_NAME + " where " + Parametro + "",null);

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

    public CCMRecienNacido getCCMRecienNacidoByCustomer(Context context, String Parametro) throws SQLException {

        CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + CCMRecienNacido.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    cCMRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    cCMRecienNacido.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMRecienNacido.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMRecienNacido.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMRecienNacido.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMRecienNacido.setConvulsiones(Boolean.valueOf(c.getString(c.getColumnIndex("Convulsiones"))));
                    cCMRecienNacido.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMRecienNacido.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMRecienNacido.setRespRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespRapida"))));
                    cCMRecienNacido.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMRecienNacido.setFibre(Boolean.valueOf(c.getString(c.getColumnIndex("Fibre"))));
                    cCMRecienNacido.setTemperatura(Boolean.valueOf(c.getString(c.getColumnIndex("Temperatura"))));
                    cCMRecienNacido.setPielOjosAmarillos(Boolean.valueOf(c.getString(c.getColumnIndex("PielOjosAmarillos"))));
                    cCMRecienNacido.setMovEstimulos(Boolean.valueOf(c.getString(c.getColumnIndex("MovEstimulos"))));
                    cCMRecienNacido.setOmbligoPus(Boolean.valueOf(c.getString(c.getColumnIndex("OmbligoPus"))));
                    cCMRecienNacido.setPielUmbilicalRoja(Boolean.valueOf(c.getString(c.getColumnIndex("PielUmbilicalRoja"))));
                    cCMRecienNacido.setPielGranos(Boolean.valueOf(c.getString(c.getColumnIndex("PielGranos"))));
                    cCMRecienNacido.setOjosPus(Boolean.valueOf(c.getString(c.getColumnIndex("OjosPus"))));
                    cCMRecienNacido.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMRecienNacido.setNrespiraciones(c.getInt(c.getColumnIndex("NRespiraciones")));
                    cCMRecienNacido.setOtra(Boolean.valueOf(c.getString(c.getColumnIndex("Otra"))));
                    cCMRecienNacido.setObsevaciones(String.valueOf(c.getString(c.getColumnIndex("Obsevaciones"))));
                    cCMRecienNacido.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return cCMRecienNacido;
    }

    public static List<CCMRecienNacido> getAllCCMRecienNacidosList(Context context) throws SQLException {
        List<CCMRecienNacido> list = new ArrayList<CCMRecienNacido>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(CCMRecienNacido.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

                    cCMRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    cCMRecienNacido.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMRecienNacido.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMRecienNacido.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMRecienNacido.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMRecienNacido.setConvulsiones(Boolean.valueOf(c.getString(c.getColumnIndex("Convulsiones"))));
                    cCMRecienNacido.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMRecienNacido.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMRecienNacido.setRespRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespRapida"))));
                    cCMRecienNacido.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMRecienNacido.setFibre(Boolean.valueOf(c.getString(c.getColumnIndex("Fibre"))));
                    cCMRecienNacido.setTemperatura(Boolean.valueOf(c.getString(c.getColumnIndex("Temperatura"))));
                    cCMRecienNacido.setPielOjosAmarillos(Boolean.valueOf(c.getString(c.getColumnIndex("PielOjosAmarillos"))));
                    cCMRecienNacido.setMovEstimulos(Boolean.valueOf(c.getString(c.getColumnIndex("MovEstimulos"))));
                    cCMRecienNacido.setOmbligoPus(Boolean.valueOf(c.getString(c.getColumnIndex("OmbligoPus"))));
                    cCMRecienNacido.setPielUmbilicalRoja(Boolean.valueOf(c.getString(c.getColumnIndex("PielUmbilicalRoja"))));
                    cCMRecienNacido.setPielGranos(Boolean.valueOf(c.getString(c.getColumnIndex("PielGranos"))));
                    cCMRecienNacido.setOjosPus(Boolean.valueOf(c.getString(c.getColumnIndex("OjosPus"))));
                    cCMRecienNacido.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMRecienNacido.setNrespiraciones(c.getInt(c.getColumnIndex("NRespiraciones")));
                    cCMRecienNacido.setOtra(Boolean.valueOf(c.getString(c.getColumnIndex("Otra"))));
                    cCMRecienNacido.setObsevaciones(String.valueOf(c.getString(c.getColumnIndex("Obsevaciones"))));
                    cCMRecienNacido.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMRecienNacido);
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

    public static List<CCMRecienNacido> getAllCCMRecienNacidosListCustom(Context context, String parametro) throws SQLException {
        List<CCMRecienNacido> list = new ArrayList<CCMRecienNacido>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + CCMRecienNacido.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

                    cCMRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    cCMRecienNacido.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMRecienNacido.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMRecienNacido.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMRecienNacido.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMRecienNacido.setConvulsiones(Boolean.valueOf(c.getString(c.getColumnIndex("Convulsiones"))));
                    cCMRecienNacido.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMRecienNacido.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMRecienNacido.setRespRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespRapida"))));
                    cCMRecienNacido.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMRecienNacido.setFibre(Boolean.valueOf(c.getString(c.getColumnIndex("Fibre"))));
                    cCMRecienNacido.setTemperatura(Boolean.valueOf(c.getString(c.getColumnIndex("Temperatura"))));
                    cCMRecienNacido.setPielOjosAmarillos(Boolean.valueOf(c.getString(c.getColumnIndex("PielOjosAmarillos"))));
                    cCMRecienNacido.setMovEstimulos(Boolean.valueOf(c.getString(c.getColumnIndex("MovEstimulos"))));
                    cCMRecienNacido.setOmbligoPus(Boolean.valueOf(c.getString(c.getColumnIndex("OmbligoPus"))));
                    cCMRecienNacido.setPielUmbilicalRoja(Boolean.valueOf(c.getString(c.getColumnIndex("PielUmbilicalRoja"))));
                    cCMRecienNacido.setPielGranos(Boolean.valueOf(c.getString(c.getColumnIndex("PielGranos"))));
                    cCMRecienNacido.setOjosPus(Boolean.valueOf(c.getString(c.getColumnIndex("OjosPus"))));
                    cCMRecienNacido.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMRecienNacido.setNrespiraciones(c.getInt(c.getColumnIndex("NRespiraciones")));
                    cCMRecienNacido.setOtra(Boolean.valueOf(c.getString(c.getColumnIndex("Otra"))));
                    cCMRecienNacido.setObsevaciones(String.valueOf(c.getString(c.getColumnIndex("Obsevaciones"))));
                    cCMRecienNacido.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMRecienNacido);
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

    public static ArrayList<CCMRecienNacido> getAllCCMRecienNacidosArrayList(Context context) throws SQLException {

        ArrayList<CCMRecienNacido> list = new ArrayList<CCMRecienNacido>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(CCMRecienNacido.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

                    cCMRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    cCMRecienNacido.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMRecienNacido.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMRecienNacido.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMRecienNacido.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMRecienNacido.setConvulsiones(Boolean.valueOf(c.getString(c.getColumnIndex("Convulsiones"))));
                    cCMRecienNacido.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMRecienNacido.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMRecienNacido.setRespRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespRapida"))));
                    cCMRecienNacido.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMRecienNacido.setFibre(Boolean.valueOf(c.getString(c.getColumnIndex("Fibre"))));
                    cCMRecienNacido.setTemperatura(Boolean.valueOf(c.getString(c.getColumnIndex("Temperatura"))));
                    cCMRecienNacido.setPielOjosAmarillos(Boolean.valueOf(c.getString(c.getColumnIndex("PielOjosAmarillos"))));
                    cCMRecienNacido.setMovEstimulos(Boolean.valueOf(c.getString(c.getColumnIndex("MovEstimulos"))));
                    cCMRecienNacido.setOmbligoPus(Boolean.valueOf(c.getString(c.getColumnIndex("OmbligoPus"))));
                    cCMRecienNacido.setPielUmbilicalRoja(Boolean.valueOf(c.getString(c.getColumnIndex("PielUmbilicalRoja"))));
                    cCMRecienNacido.setPielGranos(Boolean.valueOf(c.getString(c.getColumnIndex("PielGranos"))));
                    cCMRecienNacido.setOjosPus(Boolean.valueOf(c.getString(c.getColumnIndex("OjosPus"))));
                    cCMRecienNacido.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMRecienNacido.setNrespiraciones(c.getInt(c.getColumnIndex("NRespiraciones")));
                    cCMRecienNacido.setOtra(Boolean.valueOf(c.getString(c.getColumnIndex("Otra"))));
                    cCMRecienNacido.setObsevaciones(String.valueOf(c.getString(c.getColumnIndex("Obsevaciones"))));
                    cCMRecienNacido.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMRecienNacido);
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

    public static ArrayList<CCMRecienNacido> getAllCCMRecienNacidosArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<CCMRecienNacido> list = new ArrayList<CCMRecienNacido>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + CCMRecienNacido.TABLE_NAME + parametro + orderBy, null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMRecienNacido cCMRecienNacido = new CCMRecienNacido();

                    cCMRecienNacido.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMRecienNacido.setIdCCMRecienNacido(c.getInt(c.getColumnIndex("IdCCMRecienNacido")));
                    cCMRecienNacido.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMRecienNacido.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMRecienNacido.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMRecienNacido.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMRecienNacido.setConvulsiones(Boolean.valueOf(c.getString(c.getColumnIndex("Convulsiones"))));
                    cCMRecienNacido.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMRecienNacido.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMRecienNacido.setRespRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespRapida"))));
                    cCMRecienNacido.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMRecienNacido.setFibre(Boolean.valueOf(c.getString(c.getColumnIndex("Fibre"))));
                    cCMRecienNacido.setTemperatura(Boolean.valueOf(c.getString(c.getColumnIndex("Temperatura"))));
                    cCMRecienNacido.setPielOjosAmarillos(Boolean.valueOf(c.getString(c.getColumnIndex("PielOjosAmarillos"))));
                    cCMRecienNacido.setMovEstimulos(Boolean.valueOf(c.getString(c.getColumnIndex("MovEstimulos"))));
                    cCMRecienNacido.setOmbligoPus(Boolean.valueOf(c.getString(c.getColumnIndex("OmbligoPus"))));
                    cCMRecienNacido.setPielUmbilicalRoja(Boolean.valueOf(c.getString(c.getColumnIndex("PielUmbilicalRoja"))));
                    cCMRecienNacido.setPielGranos(Boolean.valueOf(c.getString(c.getColumnIndex("PielGranos"))));
                    cCMRecienNacido.setOjosPus(Boolean.valueOf(c.getString(c.getColumnIndex("OjosPus"))));
                    cCMRecienNacido.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMRecienNacido.setNrespiraciones(c.getInt(c.getColumnIndex("NRespiraciones")));
                    cCMRecienNacido.setOtra(Boolean.valueOf(c.getString(c.getColumnIndex("Otra"))));
                    cCMRecienNacido.setObsevaciones(String.valueOf(c.getString(c.getColumnIndex("Obsevaciones"))));
                    cCMRecienNacido.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMRecienNacido);
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

    public static Cursor getAllCCMRecienNacidosCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdCCMRecienNacido", "IdCCMRecienNacido", "IdNino", "FechaCCM", "LugarAtencion", "NoPuedeTomarPecho", "Convulsiones", "HundePiel", "RuidosRespirar", "RespRapida", "FrecCardiaca", "Fibre", "Temperatura", "PielOjosAmarillos", "MovEstimulos", "OmbligoPus", "PielUmbilicalRoja", "PielGranos", "OjosPus", "EntregoReferencia", "NRespiraciones", "Otra", "Obsevaciones", "IdUsuario"};

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
            c = myDataBase.rawQuery("Select * from " + CCMRecienNacido.TABLE_NAME + parametro,null);

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

    public int insertarCCMRecienNacido(Context context, CCMRecienNacido cCMRecienNacido) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(CCMRecienNacido.TABLE_NAME, null, CCMRecienNacidoCV(cCMRecienNacido));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarCCMRecienNacido(Context context, CCMRecienNacido cCMRecienNacido) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
         SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(CCMRecienNacido.TABLE_NAME, CCMRecienNacidoCV(cCMRecienNacido), "_id = " + cCMRecienNacido.get_id() , null);


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues CCMRecienNacidoCV(CCMRecienNacido cCMRecienNacido){
        ContentValues values = new ContentValues();

        //values.put("_id", 	String.valueOf(cCMRecienNacido.get_id()));
        values.put("IdCCMRecienNacido", 	String.valueOf(cCMRecienNacido.getIdCCMRecienNacido()));
        values.put("IdNino", 	String.valueOf(cCMRecienNacido.getIdNino()));
        values.put("FechaCCM", 	String.valueOf(cCMRecienNacido.getFechaCCM()));
        values.put("LugarAtencion", 	String.valueOf(cCMRecienNacido.getLugarAtencion()));
        values.put("NoPuedeTomarPecho", 	String.valueOf(cCMRecienNacido.getNoPuedeTomarPecho()));
        values.put("Convulsiones", 	String.valueOf(cCMRecienNacido.getConvulsiones()));
        values.put("HundePiel", 	String.valueOf(cCMRecienNacido.getHundePiel()));
        values.put("RuidosRespirar", 	String.valueOf(cCMRecienNacido.getRuidosRespirar()));
        values.put("RespRapida", 	String.valueOf(cCMRecienNacido.getRespRapida()));
        values.put("FrecCardiaca", 	String.valueOf(cCMRecienNacido.getFrecCardiaca()));
        values.put("Fibre", 	String.valueOf(cCMRecienNacido.getFibre()));
        values.put("Temperatura", 	String.valueOf(cCMRecienNacido.getTemperatura()));
        values.put("PielOjosAmarillos", 	String.valueOf(cCMRecienNacido.getPielOjosAmarillos()));
        values.put("MovEstimulos", 	String.valueOf(cCMRecienNacido.getMovEstimulos()));
        values.put("OmbligoPus", 	String.valueOf(cCMRecienNacido.getOmbligoPus()));
        values.put("PielUmbilicalRoja", 	String.valueOf(cCMRecienNacido.getPielUmbilicalRoja()));
        values.put("PielGranos", 	String.valueOf(cCMRecienNacido.getPielGranos()));
        values.put("OjosPus", 	String.valueOf(cCMRecienNacido.getOjosPus()));
        values.put("EntregoReferencia", 	String.valueOf(cCMRecienNacido.getEntregoReferencia()));
        values.put("NRespiraciones", String.valueOf(cCMRecienNacido.getNrespiraciones()));
        values.put("Otra", 	String.valueOf(cCMRecienNacido.getOtra()));
        values.put("Obsevaciones", 	String.valueOf(cCMRecienNacido.getObsevaciones()));
        values.put("IdUsuario", 	String.valueOf(cCMRecienNacido.getIdUsuario()));

        return values;
    }
    //endregion

}

