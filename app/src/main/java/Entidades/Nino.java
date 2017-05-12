package Entidades;

import java.util.Date;

public class Nino {

    //region Variables Miembros
    private int _id;
    private int IdNino;
    private int IdComunidad;
    private Date FechaRegistro;
    private String NomMadre;
    private String NomNino;
    private Date FechaNac;
    private String Sexo;
    private Boolean PesoMas2500;
    private int IdUsuario;

    public static final String TABLE_NAME = "Ninos";
    //endregion

    //region Constructors
    public Nino() {

    }

    public Nino(int _Id, int idNino, int idComunidad, Date fechaRegistro, String nomMadre, String nomNino, Date fechaNac, String sexo, Boolean pesoMas2500, int idUsuario) {
        this._id = _Id;
        this.IdNino = idNino;
        this.IdComunidad = idComunidad;
        this.FechaRegistro = fechaRegistro;
        this.NomMadre = nomMadre;
        this.NomNino = nomNino;
        this.FechaNac = fechaNac;
        this.Sexo = sexo;
        this.PesoMas2500 = pesoMas2500;
        this.IdUsuario = idUsuario;
    }

    //endregion

    //region Public Properties
    public int get_id() {
        return _id;
    }

    public void set_id(int _Id) {
        _id = _Id;
    }

    public int getIdNino() {
        return IdNino;
    }

    public void setIdNino(int idNino) {
        IdNino = idNino;
    }


    public int getIdComunidad() {
        return IdComunidad;
    }

    public void setIdComunidad(int idComunidad) {
        IdComunidad = idComunidad;
    }


    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }


    public String getNomMadre() {
        return NomMadre;
    }

    public void setNomMadre(String nomMadre) {
        NomMadre = nomMadre;
    }


    public String getNomNino() {
        return NomNino;
    }

    public void setNomNino(String nomNino) {
        NomNino = nomNino;
    }


    public Date getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        FechaNac = fechaNac;
    }


    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }


    public Boolean getPesoMas2500() {
        return PesoMas2500;
    }

    public void setPesoMas2500(Boolean pesoMas2500) {
        PesoMas2500 = pesoMas2500;
    }


    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    //endregion
}

