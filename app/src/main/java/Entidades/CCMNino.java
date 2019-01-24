package Entidades;

import java.util.Date;

public class    CCMNino {

    //region Variables Miembros
    private int _id;
    private int IdCCMNino;
    private int IdNino;
    private Date FechaCCM;
    private String LugarAtencion;
    private Boolean DificilDespertar;
    private Boolean NoPuedeTomarPecho;
    private Boolean VomitaTodo;
    private Boolean Ataques;
    private Boolean HundePiel;
    private Boolean RuidosRespirar;
    private Boolean RespiracionRapida;
    private int FrecCardiaca;
    private Boolean SignoNeumonia;
    private Boolean TosCatarro;
    private Boolean Moco;
    private Boolean MuyDormido;
    private Boolean DejoComer;
    private Boolean OjosHundido;
    private Boolean SignoPliegue;
    private Boolean DiarreMayorDias;
    private Boolean PopuSangre;
    private Boolean InquietoIrritable;
    private Boolean BebeMuchaSed;
    private Boolean PresentaDeshidratacion;
    private Boolean CostadoCaliente;
    private Boolean EntregoReferencia;
    private int NRespiraciones;
    private String Observacion;
    private int IdUsuario;

    public static final String TABLE_NAME = "CCMNinos";
    //endregion

    //region Constructors
    public CCMNino() {

    }

    public CCMNino(int _Id, int idCCMNino, int idNino, Date fechaCCM, String lugarAtencion, Boolean dificilDespertar, Boolean noPuedeTomarPecho, Boolean vomitaTodo, Boolean ataques, Boolean hundePiel, Boolean ruidosRespirar, Boolean respiracionRapida, int frecCardiaca, Boolean signoNeumonia, Boolean tosCatarro, Boolean moco, Boolean muyDormido, Boolean dejoComer, Boolean ojosHundido, Boolean signoPliegue, Boolean diarreMayorDias, Boolean popuSangre, Boolean inquietoIrritable, Boolean bebeMuchaSed, Boolean presentaDeshidratacion, Boolean costadoCaliente, Boolean entregoReferencia, int nrespiraciones, String observacion, int idUsuario) {
        this._id = _Id;
        this.IdCCMNino = idCCMNino;
        this.IdNino = idNino;
        this.FechaCCM = fechaCCM;
        this.LugarAtencion = lugarAtencion;
        this.DificilDespertar = dificilDespertar;
        this.NoPuedeTomarPecho = noPuedeTomarPecho;
        this.VomitaTodo = vomitaTodo;
        this.Ataques = ataques;
        this.HundePiel = hundePiel;
        this.RuidosRespirar = ruidosRespirar;
        this.RespiracionRapida = respiracionRapida;
        this.FrecCardiaca = frecCardiaca;
        this.SignoNeumonia = signoNeumonia;
        this.TosCatarro = tosCatarro;
        this.Moco = moco;
        this.MuyDormido = muyDormido;
        this.DejoComer = dejoComer;
        this.OjosHundido = ojosHundido;
        this.SignoPliegue = signoPliegue;
        this.DiarreMayorDias = diarreMayorDias;
        this.PopuSangre = popuSangre;
        this.InquietoIrritable = inquietoIrritable;
        this.BebeMuchaSed = bebeMuchaSed;
        this.PresentaDeshidratacion = presentaDeshidratacion;
        this.CostadoCaliente = costadoCaliente;
        this.EntregoReferencia = entregoReferencia;
        this.NRespiraciones = nrespiraciones;
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

    public int getIdCCMNino() {
        return IdCCMNino;
    }

    public void setIdCCMNino(int idCCMNino) {
        IdCCMNino = idCCMNino;
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


    public Boolean getDificilDespertar() {
        return DificilDespertar;
    }

    public void setDificilDespertar(Boolean dificilDespertar) {
        DificilDespertar = dificilDespertar;
    }


    public Boolean getNoPuedeTomarPecho() {
        return NoPuedeTomarPecho;
    }

    public void setNoPuedeTomarPecho(Boolean noPuedeTomarPecho) {
        NoPuedeTomarPecho = noPuedeTomarPecho;
    }


    public Boolean getVomitaTodo() {
        return VomitaTodo;
    }

    public void setVomitaTodo(Boolean vomitaTodo) {
        VomitaTodo = vomitaTodo;
    }


    public Boolean getAtaques() {
        return Ataques;
    }

    public void setAtaques(Boolean ataques) {
        Ataques = ataques;
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


    public Boolean getRespiracionRapida() {
        return RespiracionRapida;
    }

    public void setRespiracionRapida(Boolean respiracionRapida) {
        RespiracionRapida = respiracionRapida;
    }


    public int getFrecCardiaca() {
        return FrecCardiaca;
    }

    public void setFrecCardiaca(int frecCardiaca) {
        FrecCardiaca = frecCardiaca;
    }


    public Boolean getSignoNeumonia() {
        return SignoNeumonia;
    }

    public void setSignoNeumonia(Boolean signoNeumonia) {
        SignoNeumonia = signoNeumonia;
    }


    public Boolean getTosCatarro() {
        return TosCatarro;
    }

    public void setTosCatarro(Boolean tosCatarro) {
        TosCatarro = tosCatarro;
    }


    public Boolean getMoco() {
        return Moco;
    }

    public void setMoco(Boolean moco) {
        Moco = moco;
    }


    public Boolean getMuyDormido() {
        return MuyDormido;
    }

    public void setMuyDormido(Boolean muyDormido) {
        MuyDormido = muyDormido;
    }


    public Boolean getDejoComer() {
        return DejoComer;
    }

    public void setDejoComer(Boolean dejoComer) {
        DejoComer = dejoComer;
    }


    public Boolean getOjosHundido() {
        return OjosHundido;
    }

    public void setOjosHundido(Boolean ojosHundido) {
        OjosHundido = ojosHundido;
    }


    public Boolean getSignoPliegue() {
        return SignoPliegue;
    }

    public void setSignoPliegue(Boolean signoPliegue) {
        SignoPliegue = signoPliegue;
    }


    public Boolean getDiarreMayorDias() {
        return DiarreMayorDias;
    }

    public void setDiarreMayorDias(Boolean diarreMayorDias) {
        DiarreMayorDias = diarreMayorDias;
    }


    public Boolean getPopuSangre() {
        return PopuSangre;
    }

    public void setPopuSangre(Boolean popuSangre) {
        PopuSangre = popuSangre;
    }


    public Boolean getInquietoIrritable() {
        return InquietoIrritable;
    }

    public void setInquietoIrritable(Boolean inquietoIrritable) {
        InquietoIrritable = inquietoIrritable;
    }


    public Boolean getBebeMuchaSed() {
        return BebeMuchaSed;
    }

    public void setBebeMuchaSed(Boolean bebeMuchaSed) {
        BebeMuchaSed = bebeMuchaSed;
    }


    public Boolean getPresentaDeshidratacion() {
        return PresentaDeshidratacion;
    }

    public void setPresentaDeshidratacion(Boolean presentaDeshidratacion) {
        PresentaDeshidratacion = presentaDeshidratacion;
    }


    public Boolean getCostadoCaliente() {
        return CostadoCaliente;
    }

    public void setCostadoCaliente(Boolean costadoCaliente) {
        CostadoCaliente = costadoCaliente;
    }


    public Boolean getEntregoReferencia() {
        return EntregoReferencia;
    }

    public void setEntregoReferencia(Boolean entregoReferencia) {
        EntregoReferencia = entregoReferencia;
    }

    public int getNrespiraciones() {
        return NRespiraciones;
    }

    public void setNrespiraciones(int nrespiraciones) {
        NRespiraciones = nrespiraciones;
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

