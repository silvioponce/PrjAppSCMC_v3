package Entidades;

public class TratamientoNino {

    //region Variables Miembros
    private int _id;
    private int IdTratamientoNinos;
    private int IdCCMNino;
    private int IdTratamiento;

    public static final String TABLE_NAME = "TratamientoNinos";
    //endregion

    //region Constructors
    public TratamientoNino() {

    }

    public TratamientoNino(int _Id,  int idTratamientoNinos, int idCCMNino, int idTratamiento) {
        this._id = _Id;
        this.IdTratamientoNinos = idTratamientoNinos;
        this.IdCCMNino = idCCMNino;
        this.IdTratamiento = idTratamiento;
    }

    //endregion

    //region Public Properties
    public int get_id() {
        return _id;
    }

    public void set_id(int _Id) {
        _id = _Id;
    }

    public int getIdTratamientoNinos() {
        return IdTratamientoNinos;
    }

    public void setIdTratamientoNinos(int idTratamientoNinos) {
        IdTratamientoNinos = idTratamientoNinos;
    }


    public int getIdCCMNino() {
        return IdCCMNino;
    }

    public void setIdCCMNino(int idCCMNino) {
        IdCCMNino = idCCMNino;
    }


    public int getIdTratamiento() {
        return IdTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        IdTratamiento = idTratamiento;
    }

    //endregion
}

