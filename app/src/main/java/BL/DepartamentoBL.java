package BL;


import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.List;

import DAO.DepartamentoDao;
import Entidades.Departamento;

public class DepartamentoBL {

    DepartamentoDao departamentoDao = new DepartamentoDao();

    public List<Departamento> getAllDepartamentoList(Context context) throws SQLException {

        return departamentoDao.getAllDepartamentosList(context);
    }

    public Departamento getDepartamentoById(Context context, String IdDepartamento) throws SQLException {
        return departamentoDao.getDepartamentoById(context,IdDepartamento);
    }

    public Cursor getAllDepartamentosCursor(Context context) throws SQLException {

        return departamentoDao.getAllDepartamentosCursor(context);
    }


}


