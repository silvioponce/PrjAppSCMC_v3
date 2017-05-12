package Entidades;

import java.util.Date;

public class VisitasNinosMayor {

    //region Variables Miembros
    private int _id;
    private int IdVisita;
    private int IdCCMNino;
    private Date FechaVisita;
    private Boolean CumpMedRect;
    private Boolean TomandoDosisIndicada;
    private String ResultadoVisita;
    private String Observacion;
    private int IdUsuario;

    public static final String TABLE_NAME = "VisitasNinosMayores";
    //endregion

    //region Constructors
    public VisitasNinosMayor() {

    }

    public VisitasNinosMayor(int _Id, int idVisita, int idCCMNino, Date fechaVisita, Boolean cumpMedRect, Boolean tomandoDosisIndicada, String resultadoVisita, String observacion, int idUsuario) {
        this._id = _Id;
        this.IdVisita = idVisita;
        this.IdCCMNino = idCCMNino;
        this.FechaVisita = fechaVisita;
        this.CumpMedRect = cumpMedRect;
        this.TomandoDosisIndicada = tomandoDosisIndicada;
        this.ResultadoVisita = resultadoVisita;
        this.Observacion = observacion;
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

    public int getIdVisita() {
        return IdVisita;
    }

    public void setIdVisita(int idVisita) {
        IdVisita = idVisita;
    }


    public int getIdCCMNino() {
        return IdCCMNino;
    }

    public void setIdCCMNino(int idCCMNino) {
        IdCCMNino = idCCMNino;
    }


    public Date getFechaVisita() {
        return FechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        FechaVisita = fechaVisita;
    }


    public Boolean getCumpMedRect() {
        return CumpMedRect;
    }

    public void setCumpMedRect(Boolean cumpMedRect) {
        CumpMedRect = cumpMedRect;
    }


    public Boolean getTomandoDosisIndicada() {
        return TomandoDosisIndicada;
    }

    public void setTomandoDosisIndicada(Boolean tomandoDosisIndicada) {
        TomandoDosisIndicada = tomandoDosisIndicada;
    }


    public String getResultadoVisita() {
        return ResultadoVisita;
    }

    public void setResultadoVisita(String resultadoVisita) {
        ResultadoVisita = resultadoVisita;
    }


    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }


    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    //endregion
}

