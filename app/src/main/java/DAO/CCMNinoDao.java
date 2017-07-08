package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.CCMNino;
import Util.SQLiteHelper;

public class CCMNinoDao {

    //region Other Methods

    //endregion

    //region Public Methods
    public Boolean getExisteCCMNinoById(Context context, String idCCMNino) throws SQLException {

        Boolean flag = false;
        CCMNino cCMNino = new CCMNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idCCMNino};

            Cursor c = myDataBase.query(CCMNino.TABLE_NAME, null, "IdCCMNino=?", args, null, null, null);

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

    public CCMNino getCCMNinoById(Context context, String idCCMNino) throws SQLException {

        CCMNino cCMNino = new CCMNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idCCMNino};

            Cursor c = myDataBase.query(CCMNino.TABLE_NAME, null, "_id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    cCMNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    cCMNino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMNino.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMNino.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMNino.setDificilDespertar(Boolean.valueOf(c.getString(c.getColumnIndex("DificilDespertar"))));
                    cCMNino.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMNino.setVomitaTodo(Boolean.valueOf(c.getString(c.getColumnIndex("VomitaTodo"))));
                    cCMNino.setAtaques(Boolean.valueOf(c.getString(c.getColumnIndex("Ataques"))));
                    cCMNino.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMNino.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMNino.setRespiracionRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespiracionRapida"))));
                    cCMNino.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMNino.setSignoNeumonia(Boolean.valueOf(c.getString(c.getColumnIndex("SignoNeumonia"))));
                    cCMNino.setTosCatarro(Boolean.valueOf(c.getString(c.getColumnIndex("TosCatarro"))));
                    cCMNino.setMoco(Boolean.valueOf(c.getString(c.getColumnIndex("Moco"))));
                    cCMNino.setMuyDormido(Boolean.valueOf(c.getString(c.getColumnIndex("MuyDormido"))));
                    cCMNino.setDejoComer(Boolean.valueOf(c.getString(c.getColumnIndex("DejoComer"))));
                    cCMNino.setOjosHundido(Boolean.valueOf(c.getString(c.getColumnIndex("OjosHundido"))));
                    cCMNino.setSignoPliegue(Boolean.valueOf(c.getString(c.getColumnIndex("SignoPliegue"))));
                    cCMNino.setDiarreMayorDias(Boolean.valueOf(c.getString(c.getColumnIndex("DiarreMayorDias"))));
                    cCMNino.setPopuSangre(Boolean.valueOf(c.getString(c.getColumnIndex("PopuSangre"))));
                    cCMNino.setInquietoIrritable(Boolean.valueOf(c.getString(c.getColumnIndex("InquietoIrritable"))));
                    cCMNino.setBebeMuchaSed(Boolean.valueOf(c.getString(c.getColumnIndex("BebeMuchaSed"))));
                    cCMNino.setPresentaDeshidratacion(Boolean.valueOf(c.getString(c.getColumnIndex("PresentaDeshidratacion"))));
                    cCMNino.setCostadoCaliente(Boolean.valueOf(c.getString(c.getColumnIndex("CostadoCaliente"))));
                    cCMNino.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMNino.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    cCMNino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return cCMNino;
    }

    public Boolean getExisteCCMNinoByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        CCMNino cCMNino = new CCMNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + CCMNino.TABLE_NAME + " where " + Parametro + "",null);

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

    public CCMNino getCCMNinoByCustomer(Context context, String Parametro) throws SQLException {

        CCMNino cCMNino = new CCMNino();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + CCMNino.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    cCMNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    cCMNino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMNino.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMNino.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMNino.setDificilDespertar(Boolean.valueOf(c.getString(c.getColumnIndex("DificilDespertar"))));
                    cCMNino.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMNino.setVomitaTodo(Boolean.valueOf(c.getString(c.getColumnIndex("VomitaTodo"))));
                    cCMNino.setAtaques(Boolean.valueOf(c.getString(c.getColumnIndex("Ataques"))));
                    cCMNino.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMNino.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMNino.setRespiracionRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespiracionRapida"))));
                    cCMNino.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMNino.setSignoNeumonia(Boolean.valueOf(c.getString(c.getColumnIndex("SignoNeumonia"))));
                    cCMNino.setTosCatarro(Boolean.valueOf(c.getString(c.getColumnIndex("TosCatarro"))));
                    cCMNino.setMoco(Boolean.valueOf(c.getString(c.getColumnIndex("Moco"))));
                    cCMNino.setMuyDormido(Boolean.valueOf(c.getString(c.getColumnIndex("MuyDormido"))));
                    cCMNino.setDejoComer(Boolean.valueOf(c.getString(c.getColumnIndex("DejoComer"))));
                    cCMNino.setOjosHundido(Boolean.valueOf(c.getString(c.getColumnIndex("OjosHundido"))));
                    cCMNino.setSignoPliegue(Boolean.valueOf(c.getString(c.getColumnIndex("SignoPliegue"))));
                    cCMNino.setDiarreMayorDias(Boolean.valueOf(c.getString(c.getColumnIndex("DiarreMayorDias"))));
                    cCMNino.setPopuSangre(Boolean.valueOf(c.getString(c.getColumnIndex("PopuSangre"))));
                    cCMNino.setInquietoIrritable(Boolean.valueOf(c.getString(c.getColumnIndex("InquietoIrritable"))));
                    cCMNino.setBebeMuchaSed(Boolean.valueOf(c.getString(c.getColumnIndex("BebeMuchaSed"))));
                    cCMNino.setPresentaDeshidratacion(Boolean.valueOf(c.getString(c.getColumnIndex("PresentaDeshidratacion"))));
                    cCMNino.setCostadoCaliente(Boolean.valueOf(c.getString(c.getColumnIndex("CostadoCaliente"))));
                    cCMNino.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMNino.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    cCMNino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
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
        return cCMNino;
    }

    public static List<CCMNino> getAllCCMNinosList(Context context) throws SQLException {
        List<CCMNino> list = new ArrayList<CCMNino>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(CCMNino.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMNino cCMNino = new CCMNino();

                    cCMNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    cCMNino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMNino.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMNino.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMNino.setDificilDespertar(Boolean.valueOf(c.getString(c.getColumnIndex("DificilDespertar"))));
                    cCMNino.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMNino.setVomitaTodo(Boolean.valueOf(c.getString(c.getColumnIndex("VomitaTodo"))));
                    cCMNino.setAtaques(Boolean.valueOf(c.getString(c.getColumnIndex("Ataques"))));
                    cCMNino.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMNino.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMNino.setRespiracionRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespiracionRapida"))));
                    cCMNino.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMNino.setSignoNeumonia(Boolean.valueOf(c.getString(c.getColumnIndex("SignoNeumonia"))));
                    cCMNino.setTosCatarro(Boolean.valueOf(c.getString(c.getColumnIndex("TosCatarro"))));
                    cCMNino.setMoco(Boolean.valueOf(c.getString(c.getColumnIndex("Moco"))));
                    cCMNino.setMuyDormido(Boolean.valueOf(c.getString(c.getColumnIndex("MuyDormido"))));
                    cCMNino.setDejoComer(Boolean.valueOf(c.getString(c.getColumnIndex("DejoComer"))));
                    cCMNino.setOjosHundido(Boolean.valueOf(c.getString(c.getColumnIndex("OjosHundido"))));
                    cCMNino.setSignoPliegue(Boolean.valueOf(c.getString(c.getColumnIndex("SignoPliegue"))));
                    cCMNino.setDiarreMayorDias(Boolean.valueOf(c.getString(c.getColumnIndex("DiarreMayorDias"))));
                    cCMNino.setPopuSangre(Boolean.valueOf(c.getString(c.getColumnIndex("PopuSangre"))));
                    cCMNino.setInquietoIrritable(Boolean.valueOf(c.getString(c.getColumnIndex("InquietoIrritable"))));
                    cCMNino.setBebeMuchaSed(Boolean.valueOf(c.getString(c.getColumnIndex("BebeMuchaSed"))));
                    cCMNino.setPresentaDeshidratacion(Boolean.valueOf(c.getString(c.getColumnIndex("PresentaDeshidratacion"))));
                    cCMNino.setCostadoCaliente(Boolean.valueOf(c.getString(c.getColumnIndex("CostadoCaliente"))));
                    cCMNino.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMNino.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    cCMNino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMNino);
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

    public static List<CCMNino> getAllCCMNinosListCustom(Context context, String parametro) throws SQLException {
        List<CCMNino> list = new ArrayList<CCMNino>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + CCMNino.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMNino cCMNino = new CCMNino();

                    cCMNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    cCMNino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMNino.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMNino.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMNino.setDificilDespertar(Boolean.valueOf(c.getString(c.getColumnIndex("DificilDespertar"))));
                    cCMNino.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMNino.setVomitaTodo(Boolean.valueOf(c.getString(c.getColumnIndex("VomitaTodo"))));
                    cCMNino.setAtaques(Boolean.valueOf(c.getString(c.getColumnIndex("Ataques"))));
                    cCMNino.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMNino.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMNino.setRespiracionRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespiracionRapida"))));
                    cCMNino.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMNino.setSignoNeumonia(Boolean.valueOf(c.getString(c.getColumnIndex("SignoNeumonia"))));
                    cCMNino.setTosCatarro(Boolean.valueOf(c.getString(c.getColumnIndex("TosCatarro"))));
                    cCMNino.setMoco(Boolean.valueOf(c.getString(c.getColumnIndex("Moco"))));
                    cCMNino.setMuyDormido(Boolean.valueOf(c.getString(c.getColumnIndex("MuyDormido"))));
                    cCMNino.setDejoComer(Boolean.valueOf(c.getString(c.getColumnIndex("DejoComer"))));
                    cCMNino.setOjosHundido(Boolean.valueOf(c.getString(c.getColumnIndex("OjosHundido"))));
                    cCMNino.setSignoPliegue(Boolean.valueOf(c.getString(c.getColumnIndex("SignoPliegue"))));
                    cCMNino.setDiarreMayorDias(Boolean.valueOf(c.getString(c.getColumnIndex("DiarreMayorDias"))));
                    cCMNino.setPopuSangre(Boolean.valueOf(c.getString(c.getColumnIndex("PopuSangre"))));
                    cCMNino.setInquietoIrritable(Boolean.valueOf(c.getString(c.getColumnIndex("InquietoIrritable"))));
                    cCMNino.setBebeMuchaSed(Boolean.valueOf(c.getString(c.getColumnIndex("BebeMuchaSed"))));
                    cCMNino.setPresentaDeshidratacion(Boolean.valueOf(c.getString(c.getColumnIndex("PresentaDeshidratacion"))));
                    cCMNino.setCostadoCaliente(Boolean.valueOf(c.getString(c.getColumnIndex("CostadoCaliente"))));
                    cCMNino.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMNino.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    cCMNino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMNino);
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

    public static ArrayList<CCMNino> getAllCCMNinosArrayList(Context context) throws SQLException {

        ArrayList<CCMNino> list = new ArrayList<CCMNino>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(CCMNino.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMNino cCMNino = new CCMNino();

                    cCMNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    cCMNino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMNino.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMNino.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMNino.setDificilDespertar(Boolean.valueOf(c.getString(c.getColumnIndex("DificilDespertar"))));
                    cCMNino.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMNino.setVomitaTodo(Boolean.valueOf(c.getString(c.getColumnIndex("VomitaTodo"))));
                    cCMNino.setAtaques(Boolean.valueOf(c.getString(c.getColumnIndex("Ataques"))));
                    cCMNino.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMNino.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMNino.setRespiracionRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespiracionRapida"))));
                    cCMNino.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMNino.setSignoNeumonia(Boolean.valueOf(c.getString(c.getColumnIndex("SignoNeumonia"))));
                    cCMNino.setTosCatarro(Boolean.valueOf(c.getString(c.getColumnIndex("TosCatarro"))));
                    cCMNino.setMoco(Boolean.valueOf(c.getString(c.getColumnIndex("Moco"))));
                    cCMNino.setMuyDormido(Boolean.valueOf(c.getString(c.getColumnIndex("MuyDormido"))));
                    cCMNino.setDejoComer(Boolean.valueOf(c.getString(c.getColumnIndex("DejoComer"))));
                    cCMNino.setOjosHundido(Boolean.valueOf(c.getString(c.getColumnIndex("OjosHundido"))));
                    cCMNino.setSignoPliegue(Boolean.valueOf(c.getString(c.getColumnIndex("SignoPliegue"))));
                    cCMNino.setDiarreMayorDias(Boolean.valueOf(c.getString(c.getColumnIndex("DiarreMayorDias"))));
                    cCMNino.setPopuSangre(Boolean.valueOf(c.getString(c.getColumnIndex("PopuSangre"))));
                    cCMNino.setInquietoIrritable(Boolean.valueOf(c.getString(c.getColumnIndex("InquietoIrritable"))));
                    cCMNino.setBebeMuchaSed(Boolean.valueOf(c.getString(c.getColumnIndex("BebeMuchaSed"))));
                    cCMNino.setPresentaDeshidratacion(Boolean.valueOf(c.getString(c.getColumnIndex("PresentaDeshidratacion"))));
                    cCMNino.setCostadoCaliente(Boolean.valueOf(c.getString(c.getColumnIndex("CostadoCaliente"))));
                    cCMNino.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMNino.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    cCMNino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMNino);
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

    public static ArrayList<CCMNino> getAllCCMNinosArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<CCMNino> list = new ArrayList<CCMNino>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + CCMNino.TABLE_NAME + parametro + orderBy,null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    CCMNino cCMNino = new CCMNino();

                    cCMNino.set_id(c.getInt(c.getColumnIndex("_id")));
                    cCMNino.setIdCCMNino(c.getInt(c.getColumnIndex("IdCCMNino")));
                    cCMNino.setIdNino(c.getInt(c.getColumnIndex("IdNino")));
                    cCMNino.setFechaCCM(new java.util.Date(c.getString(c.getColumnIndex("FechaCCM"))));
                    cCMNino.setLugarAtencion(String.valueOf(c.getString(c.getColumnIndex("LugarAtencion"))));
                    cCMNino.setDificilDespertar(Boolean.valueOf(c.getString(c.getColumnIndex("DificilDespertar"))));
                    cCMNino.setNoPuedeTomarPecho(Boolean.valueOf(c.getString(c.getColumnIndex("NoPuedeTomarPecho"))));
                    cCMNino.setVomitaTodo(Boolean.valueOf(c.getString(c.getColumnIndex("VomitaTodo"))));
                    cCMNino.setAtaques(Boolean.valueOf(c.getString(c.getColumnIndex("Ataques"))));
                    cCMNino.setHundePiel(Boolean.valueOf(c.getString(c.getColumnIndex("HundePiel"))));
                    cCMNino.setRuidosRespirar(Boolean.valueOf(c.getString(c.getColumnIndex("RuidosRespirar"))));
                    cCMNino.setRespiracionRapida(Boolean.valueOf(c.getString(c.getColumnIndex("RespiracionRapida"))));
                    cCMNino.setFrecCardiaca(c.getInt(c.getColumnIndex("FrecCardiaca")));
                    cCMNino.setSignoNeumonia(Boolean.valueOf(c.getString(c.getColumnIndex("SignoNeumonia"))));
                    cCMNino.setTosCatarro(Boolean.valueOf(c.getString(c.getColumnIndex("TosCatarro"))));
                    cCMNino.setMoco(Boolean.valueOf(c.getString(c.getColumnIndex("Moco"))));
                    cCMNino.setMuyDormido(Boolean.valueOf(c.getString(c.getColumnIndex("MuyDormido"))));
                    cCMNino.setDejoComer(Boolean.valueOf(c.getString(c.getColumnIndex("DejoComer"))));
                    cCMNino.setOjosHundido(Boolean.valueOf(c.getString(c.getColumnIndex("OjosHundido"))));
                    cCMNino.setSignoPliegue(Boolean.valueOf(c.getString(c.getColumnIndex("SignoPliegue"))));
                    cCMNino.setDiarreMayorDias(Boolean.valueOf(c.getString(c.getColumnIndex("DiarreMayorDias"))));
                    cCMNino.setPopuSangre(Boolean.valueOf(c.getString(c.getColumnIndex("PopuSangre"))));
                    cCMNino.setInquietoIrritable(Boolean.valueOf(c.getString(c.getColumnIndex("InquietoIrritable"))));
                    cCMNino.setBebeMuchaSed(Boolean.valueOf(c.getString(c.getColumnIndex("BebeMuchaSed"))));
                    cCMNino.setPresentaDeshidratacion(Boolean.valueOf(c.getString(c.getColumnIndex("PresentaDeshidratacion"))));
                    cCMNino.setCostadoCaliente(Boolean.valueOf(c.getString(c.getColumnIndex("CostadoCaliente"))));
                    cCMNino.setEntregoReferencia(Boolean.valueOf(c.getString(c.getColumnIndex("EntregoReferencia"))));
                    cCMNino.setObservacion(String.valueOf(c.getString(c.getColumnIndex("Observacion"))));
                    cCMNino.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));

                    list.add(cCMNino);
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

    public static Cursor getAllCCMNinosCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdCCMNino", "IdCCMNino", "IdNino", "FechaCCM", "LugarAtencion", "DificilDespertar", "NoPuedeTomarPecho", "VomitaTodo", "Ataques", "HundePiel", "RuidosRespirar", "RespiracionRapida", "FrecCardiaca", "SignoNeumonia", "TosCatarro", "Moco", "MuyDormido", "DejoComer", "OjosHundido", "SignoPliegue", "DiarreMayorDias", "PopuSangre", "InquietoIrritable", "BebeMuchaSed", "PresentaDeshidratacion", "CostadoCaliente", "EntregoReferencia", "Observacion", "IdUsuario"};

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
            c = myDataBase.rawQuery("Select * from " + CCMNino.TABLE_NAME + parametro,null);

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

    public int insertarCCMNino(Context context, CCMNino cCMNino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(CCMNino.TABLE_NAME, null, CCMNinoCV(cCMNino));


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int actualizarCCMNino(Context context, CCMNino cCMNino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(CCMNino.TABLE_NAME, CCMNinoCV(cCMNino), "_id = " + cCMNino.get_id() , null);

            if (result>0)
                result = cCMNino.get_id();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public int eliminarCCMNino(Context context, int IdCCMNino) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(CCMNino.TABLE_NAME, "_id = " + IdCCMNino, null);

            if (result>0)
                result = IdCCMNino;

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }

    public ContentValues CCMNinoCV(CCMNino cCMNino){
        ContentValues values = new ContentValues();

        //values.put("_id", 	String.valueOf(cCMNino.get_id()));
        values.put("IdCCMNino", 	String.valueOf(cCMNino.getIdCCMNino()));
        values.put("IdNino", 	String.valueOf(cCMNino.getIdNino()));
        values.put("FechaCCM", 	String.valueOf(cCMNino.getFechaCCM()));
        values.put("LugarAtencion", 	String.valueOf(cCMNino.getLugarAtencion()));
        values.put("DificilDespertar", 	String.valueOf(cCMNino.getDificilDespertar()));
        values.put("NoPuedeTomarPecho", 	String.valueOf(cCMNino.getNoPuedeTomarPecho()));
        values.put("VomitaTodo", 	String.valueOf(cCMNino.getVomitaTodo()));
        values.put("Ataques", 	String.valueOf(cCMNino.getAtaques()));
        values.put("HundePiel", 	String.valueOf(cCMNino.getHundePiel()));
        values.put("RuidosRespirar", 	String.valueOf(cCMNino.getRuidosRespirar()));
        values.put("RespiracionRapida", 	String.valueOf(cCMNino.getRespiracionRapida()));
        values.put("FrecCardiaca", 	String.valueOf(cCMNino.getFrecCardiaca()));
        values.put("SignoNeumonia", 	String.valueOf(cCMNino.getSignoNeumonia()));
        values.put("TosCatarro", 	String.valueOf(cCMNino.getTosCatarro()));
        values.put("Moco", 	String.valueOf(cCMNino.getMoco()));
        values.put("MuyDormido", 	String.valueOf(cCMNino.getMuyDormido()));
        values.put("DejoComer", 	String.valueOf(cCMNino.getDejoComer()));
        values.put("OjosHundido", 	String.valueOf(cCMNino.getOjosHundido()));
        values.put("SignoPliegue", 	String.valueOf(cCMNino.getSignoPliegue()));
        values.put("DiarreMayorDias", 	String.valueOf(cCMNino.getDiarreMayorDias()));
        values.put("PopuSangre", 	String.valueOf(cCMNino.getPopuSangre()));
        values.put("InquietoIrritable", 	String.valueOf(cCMNino.getInquietoIrritable()));
        values.put("BebeMuchaSed", 	String.valueOf(cCMNino.getBebeMuchaSed()));
        values.put("PresentaDeshidratacion", 	String.valueOf(cCMNino.getPresentaDeshidratacion()));
        values.put("CostadoCaliente", 	String.valueOf(cCMNino.getCostadoCaliente()));
        values.put("EntregoReferencia", 	String.valueOf(cCMNino.getEntregoReferencia()));
        values.put("Observacion", 	String.valueOf(cCMNino.getObservacion()));
        values.put("IdUsuario", 	String.valueOf(cCMNino.getIdUsuario()));

        return values;
    }
    //endregion

}

