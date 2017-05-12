package Entidades;

import java.util.Date;

public class CCMRecienNacido {

    //region Variables Miembros
    private int _id;
    private int IdCCMRecienNacido;
    private int IdNino;
    private Date FechaCCM;
    private String LugarAtencion;
    private Boolean NoPuedeTomarPecho;
    private Boolean Convulsiones;
    private Boolean HundePiel;
    private Boolean RuidosRespirar;
    private Boolean RespRapida;
    private int FrecCardiaca;
    private Boolean Fibre;
    private Boolean Temperatura;
    private Boolean PielOjosAmarillos;
    private Boolean MovEstimulos;
    private Boolean OmbligoPus;
    private Boolean PielUmbilicalRoja;
    private Boolean PielGranos;
    private Boolean OjosPus;
    private Boolean EntregoReferencia;
    private Boolean Otra;
    private String Obsevaciones;
    private int IdUsuario;

    public static final String TABLE_NAME = "CCMRecienNacidos";
    //endregion

    //region Constructors
    public CCMRecienNacido() {

    }

    public CCMRecienNacido(int _Id, int idCCMRecienNacido, int idNino, Date fechaCCM, String lugarAtencion, Boolean noPuedeTomarPecho, Boolean convulsiones, Boolean hundePiel, Boolean ruidosRespirar, Boolean respRapida, int frecCardiaca, Boolean fibre, Boolean temperatura, Boolean pielOjosAmarillos, Boolean movEstimulos, Boolean ombligoPus, Boolean pielUmbilicalRoja, Boolean pielGranos, Boolean ojosPus, Boolean entregoReferencia, Boolean otra, String obsevaciones, int idUsuario) {
        this._id = _Id;
        this.IdCCMRecienNacido = idCCMRecienNacido;
        this.IdNino = idNino;
        this.FechaCCM = fechaCCM;
        this.LugarAtencion = lugarAtencion;
        this.NoPuedeTomarPecho = noPuedeTomarPecho;
        this.Convulsiones = convulsiones;
        this.HundePiel = hundePiel;
        this.RuidosRespirar = ruidosRespirar;
        this.RespRapida = respRapida;
        this.FrecCardiaca = frecCardiaca;
        this.Fibre = fibre;
        this.Temperatura = temperatura;
        this.PielOjosAmarillos = pielOjosAmarillos;
        this.MovEstimulos = movEstimulos;
        this.OmbligoPus = ombligoPus;
        this.PielUmbilicalRoja = pielUmbilicalRoja;
        this.PielGranos = pielGranos;
        this.OjosPus = ojosPus;
        this.EntregoReferencia = entregoReferencia;
        this.Otra = otra;
        this.Obsevaciones = obsevaciones;
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

    public int getIdCCMRecienNacido() {
        return IdCCMRecienNacido;
    }

    public void setIdCCMRecienNacido(int idCCMRecienNacido) {
        IdCCMRecienNacido = idCCMRecienNacido;
    }


    public int getIdNino() {
        return IdNino;
    }

    public void setIdNino(int idNino) {
        IdNino = idNino;
    }


    public Date getFechaCCM() {
        return FechaCCM;
    }

    public void setFechaCCM(Date fechaCCM) {
        FechaCCM = fechaCCM;
    }


    public String getLugarAtencion() {
        return LugarAtencion;
    }

    public void setLugarAtencion(String lugarAtencion) {
        LugarAtencion = lugarAtencion;
    }


    public Boolean getNoPuedeTomarPecho() {
        return NoPuedeTomarPecho;
    }

    public void setNoPuedeTomarPecho(Boolean noPuedeTomarPecho) {
        NoPuedeTomarPecho = noPuedeTomarPecho;
    }


    public Boolean getConvulsiones() {
        return Convulsiones;
    }

    public void setConvulsiones(Boolean convulsiones) {
        Convulsiones = convulsiones;
    }


    public Boolean getHundePiel() {
        return HundePiel;
    }

    public void setHundePiel(Boolean hundePiel) {
        HundePiel = hundePiel;
    }


    public Boolean getRuidosRespirar() {
        return RuidosRespirar;
    }

    public void setRuidosRespirar(Boolean ruidosRespirar) {
        RuidosRespirar = ruidosRespirar;
    }


    public Boolean getRespRapida() {
        return RespRapida;
    }

    public void setRespRapida(Boolean respRapida) {
        RespRapida = respRapida;
    }


    public int getFrecCardiaca() {
        return FrecCardiaca;
    }

    public void setFrecCardiaca(int frecCardiaca) {
        FrecCardiaca = frecCardiaca;
    }


    public Boolean getFibre() {
        return Fibre;
    }

    public void setFibre(Boolean fibre) {
        Fibre = fibre;
    }


    public Boolean getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(Boolean temperatura) {
        Temperatura = temperatura;
    }


    public Boolean getPielOjosAmarillos() {
        return PielOjosAmarillos;
    }

    public void setPielOjosAmarillos(Boolean pielOjosAmarillos) {
        PielOjosAmarillos = pielOjosAmarillos;
    }


    public Boolean getMovEstimulos() {
        return MovEstimulos;
    }

    public void setMovEstimulos(Boolean movEstimulos) {
        MovEstimulos = movEstimulos;
    }


    public Boolean getOmbligoPus() {
        return OmbligoPus;
    }

    public void setOmbligoPus(Boolean ombligoPus) {
        OmbligoPus = ombligoPus;
    }


    public Boolean getPielUmbilicalRoja() {
        return PielUmbilicalRoja;
    }

    public void setPielUmbilicalRoja(Boolean pielUmbilicalRoja) {
        PielUmbilicalRoja = pielUmbilicalRoja;
    }


    public Boolean getPielGranos() {
        return PielGranos;
    }

    public void setPielGranos(Boolean pielGranos) {
        PielGranos = pielGranos;
    }


    public Boolean getOjosPus() {
        return OjosPus;
    }

    public void setOjosPus(Boolean ojosPus) {
        OjosPus = ojosPus;
    }


    public Boolean getEntregoReferencia() {
        return EntregoReferencia;
    }

    public void setEntregoReferencia(Boolean entregoReferencia) {
        EntregoReferencia = entregoReferencia;
    }


    public Boolean getOtra() {
        return Otra;
    }

    public void setOtra(Boolean otra) {
        Otra = otra;
    }


    public String getObsevaciones() {
        return Obsevaciones;
    }

    public void setObsevaciones(String obsevaciones) {
        Obsevaciones = obsevaciones;
    }


    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    //endregion
}

