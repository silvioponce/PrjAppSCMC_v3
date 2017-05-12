package Entidades;

public class Comunidad {

    //region Variables Miembros
    private int _id;
    private int IdComunidad;
    private String NomComunidad;
    private int IdMunicipio;

    public static final String TABLE_NAME = "Comunidades";
    //endregion

    //region Constructors
    public Comunidad() {

    }

    public Comunidad(int _Id, int idComunidad, String nomComunidad, int idMunicipio) {
        this._id = _Id;
        this.IdComunidad = idComunidad;
        this.NomComunidad = nomComunidad;
        this.IdMunicipio = idMunicipio;
    }

    //endregion

    //region Public Properties
    public int get_id() {
        return _id;
    }

    public void set_id(int _Id) {
        _id = _Id;
    }

    public int getIdComunidad() {
        return IdComunidad;
    }

    public void setIdComunidad(int idComunidad) {
        IdComunidad = idComunidad;
    }


    public String getNomComunidad() {
        return NomComunidad;
    }

    public void setNomComunidad(String nomComunidad) {
        NomComunidad = nomComunidad;
    }


    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        IdMunicipio = idMunicipio;
    }

    //endregion

    @Override
    public String toString () {
        return NomComunidad;
    }

}

