package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Usuario;
import Util.SQLiteHelper;

public class UsuarioDao {

    //region Other Methods
    public Usuario getVerificaUsuario(Context context, String user, String contrasena) throws SQLException {

        Usuario usuario = new Usuario();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{user,contrasena};

            //Cursor c = myDataBase.query(Usuario.TABLE_NAME, null, "usuario=? and contrasena = ?", args, null, null, null);
            Cursor c = myDataBase.rawQuery("Select * from Usuarios where usuario = '" +  user + "' and contrasena = '" + contrasena + "'",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    usuario.setIdUsuario(Integer.valueOf(c.getString(c.getColumnIndex("IdUsuario"))));
                    usuario.set_id(Integer.valueOf(c.getString(c.getColumnIndex("_id"))));
                    usuario.setNomUsuario(String.valueOf(c.getString(c.getColumnIndex("NomUsuario"))));
                    usuario.setIdComunidad(Integer.valueOf(c.getString(c.getColumnIndex("IdComunidad"))));
                    usuario.setUsuario(String.valueOf(c.getString(c.getColumnIndex("Usuario"))));
                    usuario.setContrasena(String.valueOf(c.getString(c.getColumnIndex("Contrasena"))));
                    usuario.setIdRol(Integer.valueOf(c.getString(c.getColumnIndex("IdRol"))));
                    usuario.setEstado(Boolean.valueOf(c.getString(c.getColumnIndex("Estado"))));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return usuario;
    }
    //endregion

    //region Public Methods
    public Boolean getExisteUsuarioById(Context context, String idUsuario) throws SQLException {

        Boolean flag = false;
        Usuario usuario = new Usuario();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idUsuario};

            Cursor c = myDataBase.query(Usuario.TABLE_NAME, null, "IdUsuario=?", args, null, null, null);

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

    public Usuario getUsuarioById(Context context, String idUsuario) throws SQLException {

        Usuario usuario = new Usuario();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            String[] args = new String[]{idUsuario};

            Cursor c = myDataBase.query(Usuario.TABLE_NAME, null, "_Id=?", args, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    usuario.set_id(c.getInt(c.getColumnIndex("_id")));
                    usuario.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    usuario.setNomUsuario(String.valueOf(c.getString(c.getColumnIndex("NomUsuario"))));
                    usuario.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    usuario.setUsuario(String.valueOf(c.getString(c.getColumnIndex("Usuario"))));
                    usuario.setContrasena(String.valueOf(c.getString(c.getColumnIndex("Contrasena"))));
                    usuario.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    usuario.setEstado(Boolean.valueOf(c.getString(c.getColumnIndex("Estado"))));
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
        return usuario;
    }

    public Boolean getExisteUsuarioByCustomer(Context context, String Parametro) throws SQLException {

        Boolean flag = false;
        Usuario usuario = new Usuario();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Usuario.TABLE_NAME + " where " + Parametro + "",null);

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

    public Usuario getUsuarioByCustomer(Context context, String Parametro) throws SQLException {

        Usuario usuario = new Usuario();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor c = myDataBase.rawQuery("Select * from " + Usuario.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {

                    usuario.set_id(c.getInt(c.getColumnIndex("_id")));
                    usuario.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    usuario.setNomUsuario(String.valueOf(c.getString(c.getColumnIndex("NomUsuario"))));
                    usuario.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    usuario.setUsuario(String.valueOf(c.getString(c.getColumnIndex("Usuario"))));
                    usuario.setContrasena(String.valueOf(c.getString(c.getColumnIndex("Contrasena"))));
                    usuario.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    usuario.setEstado(Boolean.valueOf(c.getString(c.getColumnIndex("Estado"))));
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
        return usuario;
    }

    public static List<Usuario> getAllUsuariosList(Context context) throws SQLException {
        List<Usuario> list = new ArrayList<Usuario>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Usuario.TABLE_NAME, null, null, null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Usuario usuario = new Usuario();

                    usuario.set_id(c.getInt(c.getColumnIndex("_id")));
                    usuario.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    usuario.setNomUsuario(String.valueOf(c.getString(c.getColumnIndex("NomUsuario"))));
                    usuario.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    usuario.setUsuario(String.valueOf(c.getString(c.getColumnIndex("Usuario"))));
                    usuario.setContrasena(String.valueOf(c.getString(c.getColumnIndex("Contrasena"))));
                    usuario.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    usuario.setEstado(Boolean.valueOf(c.getString(c.getColumnIndex("Estado"))));

                    list.add(usuario);
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

    public static List<Usuario> getAllUsuariosListCustom(Context context, String parametro) throws SQLException {
        List<Usuario> list = new ArrayList<Usuario>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Usuario.TABLE_NAME + parametro, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Usuario usuario = new Usuario();

                    usuario.set_id(c.getInt(c.getColumnIndex("_id")));
                    usuario.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    usuario.setNomUsuario(String.valueOf(c.getString(c.getColumnIndex("NomUsuario"))));
                    usuario.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    usuario.setUsuario(String.valueOf(c.getString(c.getColumnIndex("Usuario"))));
                    usuario.setContrasena(String.valueOf(c.getString(c.getColumnIndex("Contrasena"))));
                    usuario.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    usuario.setEstado(Boolean.valueOf(c.getString(c.getColumnIndex("Estado"))));

                    list.add(usuario);
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

    public static ArrayList<Usuario> getAllUsuariosArrayList(Context context) throws SQLException {

        ArrayList<Usuario> list = new ArrayList<Usuario>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Usuario.TABLE_NAME, null, null, null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Usuario usuario = new Usuario();

                    usuario.set_id(c.getInt(c.getColumnIndex("_id")));
                    usuario.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    usuario.setNomUsuario(String.valueOf(c.getString(c.getColumnIndex("NomUsuario"))));
                    usuario.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    usuario.setUsuario(String.valueOf(c.getString(c.getColumnIndex("Usuario"))));
                    usuario.setContrasena(String.valueOf(c.getString(c.getColumnIndex("Contrasena"))));
                    usuario.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    usuario.setEstado(Boolean.valueOf(c.getString(c.getColumnIndex("Estado"))));

                    list.add(usuario);
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

    public static ArrayList<Usuario> getAllUsuariosArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<Usuario> list = new ArrayList<Usuario>();
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
            orderBy = " " + orderBy + " Order By DESC" ;
        }
        else {
            orderBy = "";
        }

        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.rawQuery("Select * from " + Usuario.TABLE_NAME + parametro + orderBy, null);

            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Usuario usuario = new Usuario();

                    usuario.set_id(c.getInt(c.getColumnIndex("_id")));
                    usuario.setIdUsuario(c.getInt(c.getColumnIndex("IdUsuario")));
                    usuario.setNomUsuario(String.valueOf(c.getString(c.getColumnIndex("NomUsuario"))));
                    usuario.setIdComunidad(c.getInt(c.getColumnIndex("IdComunidad")));
                    usuario.setUsuario(String.valueOf(c.getString(c.getColumnIndex("Usuario"))));
                    usuario.setContrasena(String.valueOf(c.getString(c.getColumnIndex("Contrasena"))));
                    usuario.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    usuario.setEstado(Boolean.valueOf(c.getString(c.getColumnIndex("Estado"))));

                    list.add(usuario);
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

    public static Cursor getAllUsuariosCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdUsuario", "IdUsuario", "NomUsuario", "IdComunidad", "Usuario", "Contrasena", "IdRol", "Estado"};

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
            c = myDataBase.rawQuery("Select * from " + Usuario.TABLE_NAME + parametro,null);

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

    public Boolean insertarUsuario(Context context, Usuario usuario) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.insert(Usuario.TABLE_NAME, null, UsuarioCV(usuario)) != -1)
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

    public Boolean actualizarUsuario(Context context, Usuario usuario) throws SQLException {

        Boolean flag = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            if (myDataBase.update(Usuario.TABLE_NAME, UsuarioCV(usuario), "IdUsuario = " + usuario.getIdUsuario(), null) != -1)
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

    public ContentValues UsuarioCV(Usuario usuario){
        ContentValues values = new ContentValues();

        //values.put("_Id", 	String.valueOf(usuario.get_Id()));
        values.put("IdUsuario", 	String.valueOf(usuario.getIdUsuario()));
        values.put("NomUsuario", 	String.valueOf(usuario.getNomUsuario()));
        values.put("IdComunidad", 	String.valueOf(usuario.getIdComunidad()));
        values.put("Usuario", 	String.valueOf(usuario.getUsuario()));
        values.put("Contrasena", 	String.valueOf(usuario.getContrasena()));
        values.put("IdRol", 	String.valueOf(usuario.getIdRol()));
        values.put("Estado", 	String.valueOf(usuario.getEstado()));

        return values;
    }
    //endregion

}

