package BL;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;

import DAO.ComunidadDao;
import DAO.MunicipioDao;
import DAO.RolDao;
import DAO.UsuarioDao;
import Entidades.Comunidad;
import Entidades.Municipio;
import Entidades.Usuario;
import WS.ComunidadWs;
import WS.UsuarioWs;

/**
 * Created by sponce on 07/04/2015.
 */
public class UsuarioBL {

    AdminBL adminBL = new AdminBL();
    UsuarioDao usuarioDao = new UsuarioDao();
    RolDao rolDao = new RolDao();
    Usuario usuario = new Usuario();

    ComunidadDao comunidadDao = new ComunidadDao();
    Comunidad comunidad = new Comunidad();
    MunicipioDao municipioDao = new MunicipioDao();
    Municipio municipio = new Municipio();

    private Cursor cursor;

    public Usuario getUsuarioById(Context context, String idUsuario) throws SQLException {

        usuario = usuarioDao.getUsuarioById(context, idUsuario);

        return usuario;
    }

    public Usuario getVerificaUsuario(Context context, String user, String password) throws SQLException {

        Boolean flag = false;
        usuario = new Usuario();
        if (adminBL.isOnline(context)) {
            usuario = UsuarioWs.getVerificaUsuario(user, password, "getVerificarUsuario");

            if (usuario.getIdUsuario()!=0){

                comunidad = ComunidadWs.getComunidadById(usuario.getIdComunidad(), "getComunidades");
                if (!comunidadDao.getExisteComunidadById(context, String.valueOf(usuario.getIdComunidad()))){

                    comunidadDao.insertarComunidad(context, comunidad);

                }else {
                    comunidadDao.actualizarComunidad(context, comunidad);
                }

                if (usuarioDao.getExisteUsuarioById(context, String.valueOf(usuario.getIdUsuario()))) {
                    usuarioDao.actualizarUsuario(context, usuario);

                } else {
                    usuarioDao.insertarUsuario(context, usuario);
                }
            }

        } else {
            usuario = usuarioDao.getVerificaUsuario(context, user, password);
        }

        if (usuario.getIdUsuario() == 0) {

            return usuario;

        } else {
            return usuario;
        }

    }

    public Cursor getCursorAllUsuario(Context context) throws SQLException {

        cursor = usuarioDao.getAllUsuariosCursor(context, null);
        return cursor;
    }


}
