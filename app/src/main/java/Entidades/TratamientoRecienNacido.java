package Entidades;

public class TratamientoRecienNacido {

    //region Variables Miembros
    private int _id;
    private int IdTratamientoReciendNacido;
    private int IdCCMRecienNacido;
    private int IdTratamiento;

    public static final String TABLE_NAME = "TratamientoRecienNacido";
    //endregion

    //region Constructors
    public TratamientoRecienNacido() {

    }

    public TratamientoRecienNacido(int _Id,  int idTratamientoReciendNacido, int idCCMRecienNacido, int idTratamiento) {
        this._id = _Id;
        this.IdTratamientoReciendNacido = idTratamientoReciendNacido;
        this.IdCCMRecienNacido = idCCMRecienNacido;
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

    public int getIdTratamientoReciendNacido() {
        return IdTratamientoReciendNacido;
    }

    public void setIdTratamientoReciendNacido(int idTratamientoReciendNacido) {
        IdTratamientoReciendNacido = idTratamientoReciendNacido;
    }


    public int getIdCCMRecienNacido() {
        return IdCCMRecienNacido;
    }

    public void setIdCCMRecienNacido(int idCCMRecienNacido) {
        IdCCMRecienNacido = idCCMRecienNacido;
    }


    public int getIdTratamiento() {
        return IdTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        IdTratamiento = idTratamiento;
    }

    //endregion
}

