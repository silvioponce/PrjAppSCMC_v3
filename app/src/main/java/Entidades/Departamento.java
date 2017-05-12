package Entidades;

public class Departamento {

    //region Variables Miembros
    private Integer _IdDepartamento;
    private Integer IdDepartamento;
    private String NomDepartamento;

    public static final String TABLE_NAME = "Departamentos";
    //endregion

    //region Constructors
    public Departamento() {

    }

    public Departamento(Integer _idDepartamento, Integer idDepartamento, String nomDepartamento) {
        this._IdDepartamento = _idDepartamento;
        this.IdDepartamento = idDepartamento;
        this.NomDepartamento = nomDepartamento;
    }

    //endregion

    //region Public Properties
    public Integer get_IdDepartamento() {
        return _IdDepartamento;
    }

    public void set_IdDepartamento(Integer _idDepartamento) {
        _IdDepartamento = _idDepartamento;
    }

    public Integer getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        IdDepartamento = idDepartamento;
    }


    public String getNomDepartamento() {
        return NomDepartamento;
    }

    public void setNomDepartamento(String nomDepartamento) {
        NomDepartamento = nomDepartamento;
    }

    //endregion


}

