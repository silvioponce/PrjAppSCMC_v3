package Entidades;

public class Rol {

    //region Variables Miembros
    private Integer _IdRol;
    private Integer IdRol;
    private String NomRol;
    private String DesRol;

    public static final String TABLE_NAME = "Roles";
    //endregion

    //region Constructors
    public Rol() {

    }

    public Rol(Integer _idRol, Integer idRol, String nomRol, String desRol) {
        this._IdRol = _idRol;
        this.IdRol = idRol;
        this.NomRol = nomRol;
        this.DesRol = desRol;
    }

    //endregion

    //region Public Properties
    public Integer get_IdRol() {
        return _IdRol;
    }

    public void set_IdRol(Integer _idRol) {
        _IdRol = _idRol;
    }

    public Integer getIdRol() {
        return IdRol;
    }

    public void setIdRol(Integer idRol) {
        IdRol = idRol;
    }


    public String getNomRol() {
        return NomRol;
    }

    public void setNomRol(String nomRol) {
        NomRol = nomRol;
    }


    public String getDesRol() {
        return DesRol;
    }

    public void setDesRol(String desRol) {
        DesRol = desRol;
    }

    //endregion

}

