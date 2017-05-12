package Entidades;

public class Municipio {

    //region Variables Miembros
    private Integer _IdMunicipio;
    private Integer IdMunicipio;
    private String NomMunicipio;
    private Integer IdDepartamento;

    public static final String TABLE_NAME = "Municipios";
    //endregion

    //region Constructors
    public Municipio() {

    }

    public Municipio(Integer _idMunicipio, Integer idMunicipio, String nomMunicipio, Integer idDepartamento) {
        this._IdMunicipio = _idMunicipio;
        this.IdMunicipio = idMunicipio;
        this.NomMunicipio = nomMunicipio;
        this.IdDepartamento = idDepartamento;
    }

    //endregion

    //region Public Properties
    public Integer get_IdMunicipio() {
        return _IdMunicipio;
    }

    public void set_IdMunicipio(Integer _idMunicipio) {
        _IdMunicipio = _idMunicipio;
    }

    public Integer getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        IdMunicipio = idMunicipio;
    }

    public String getNomMunicipio() {
        return NomMunicipio;
    }

    public void setNomMunicipio(String nomMunicipio) {
        NomMunicipio = nomMunicipio;
    }

    public Integer getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        IdDepartamento = idDepartamento;
    }

    //endregion

    @Override
    public String toString () {
        return NomMunicipio;
    }

}

