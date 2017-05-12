package Entidades;

public class Tratamiento {

    //region Variables Miembros
    private int _Id;
    private int IdTratamiento;
    private String Pregunta;
    private String Tratamiento;
    private String Grupo;

    public static final String TABLE_NAME = "Tratamiento";
    //endregion

    //region Constructors
    public Tratamiento() {

    }

    public Tratamiento(int _id, int idTratamiento, String pregunta, String tratamiento, String grupo) {
        this._Id = _id;
        this.IdTratamiento = idTratamiento;
        this.Pregunta = pregunta;
        this.Tratamiento = tratamiento;
        this.Grupo = grupo;
    }

    //endregion

    //region Public Properties
    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _id) {
        _Id = _id;
    }

    public int getIdTratamiento() {
        return IdTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        IdTratamiento = idTratamiento;
    }


    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }


    public String getTratamiento() {
        return Tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        Tratamiento = tratamiento;
    }


    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
    }

    //endregion
}

