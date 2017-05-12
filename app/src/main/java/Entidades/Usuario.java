package Entidades;

public class Usuario {

    //region Variables Miembros
    private int _id;
    private int IdUsuario;
    private String NomUsuario;
    private int IdComunidad;
    private String Usuario;
    private String Contrasena;
    private int IdRol;
    private Boolean Estado;

    public static final String TABLE_NAME = "Usuarios";
    //endregion

    //region Constructors
    public Usuario() {

    }

    public Usuario(int _Id, int idUsuario, String nomUsuario, int idComunidad, String usuario, String contrasena, int idRol, Boolean estado) {
        this._id = _Id;
        this.IdUsuario = idUsuario;
        this.NomUsuario = nomUsuario;
        this.IdComunidad = idComunidad;
        this.Usuario = usuario;
        this.Contrasena = contrasena;
        this.IdRol = idRol;
        this.Estado = estado;
    }

    //endregion

    //region Public Properties
    public int get_id() {
        return _id;
    }

    public void set_id(int _Id) {
        _id = _Id;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }


    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        NomUsuario = nomUsuario;
    }


    public int getIdComunidad() {
        return IdComunidad;
    }

    public void setIdComunidad(int idComunidad) {
        IdComunidad = idComunidad;
    }


    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }


    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }


    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        IdRol = idRol;
    }


    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    //endregion
}

