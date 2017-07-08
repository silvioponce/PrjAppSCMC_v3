package BL;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CCMNinoDao;
import Entidades.CCMNino;

public class CCMNinoBL {

    CCMNinoDao ccmNinoDao = new CCMNinoDao();

    public int GuardarCCMNino(Context context, CCMNino ccmNino) throws SQLException {
        if (ccmNino.get_id() != 0) {
            return ccmNinoDao.actualizarCCMNino(context, ccmNino);

        } else {
            return ccmNinoDao.insertarCCMNino(context, ccmNino);
        }
    }

    public ArrayList<CCMNino> getAllCCMNinosArrayListCustom(Context context, String strWhere, String strOrderBy) throws SQLException {

        return ccmNinoDao.getAllCCMNinosArrayListCustom(context, strWhere, strOrderBy);
    }

    public ArrayList<CCMNino> getAllCCMNinosArrayList(Context context) throws SQLException {

        return ccmNinoDao.getAllCCMNinosArrayList(context);
    }

    public CCMNino getCCMNinoById(Context context, String idCCMNino) throws SQLException {
        return ccmNinoDao.getCCMNinoById(context, idCCMNino);
    }

    public String clasificarEnfermedad(CCMNino ccmNino) {

        boolean enfermedad_muy_gr, Neumonia_grave, Neumonia_no_complicada, Tos_catarro, deshidratacion_grave_Severa, diarrea_persistente, diarrea_pupu_moco_sangre, diarrea_deshidratacion_moderada,
                diarrea_sin_deshidratación, fiebre;

        int intDeshidratacionGrave = 0, intDeshidratacionModerada = 0, intNeumoniaGrave = 0, intNeumoniaNoComplicada = 0, intTosOCatarro = 0, intDiarreaSinDeshidratacion = 0;

        String strResultado = "";

        // Efermendad muy Grave

        if (ccmNino.getDificilDespertar() || ccmNino.getNoPuedeTomarPecho() || ccmNino.getVomitaTodo() || ccmNino.getAtaques()) {
            enfermedad_muy_gr = true;
            strResultado += "Enfermedad muy Grave" + ", ";

        }

        // Neumonia Grave

        if (ccmNino.getHundePiel() || ccmNino.getRuidosRespirar()) {
            Neumonia_grave = true;
            intNeumoniaGrave += 1;
            strResultado += "Neumonía Grave" + ", ";
        }

        // Neumonia no Complicada

        if (ccmNino.getRespiracionRapida()) {
            Neumonia_no_complicada = true;
            intNeumoniaNoComplicada += 1;
            strResultado += "Neumonía no Complicada" + ", ";
        }

        // Tos o Catarro

        if (ccmNino.getSignoNeumonia() || ccmNino.getTosCatarro() || ccmNino.getMoco()) {
            Tos_catarro = true;
            intTosOCatarro += 1;
            strResultado += "Tos o Catarro" + ", ";
        }

        //Deshidratacion Grave o Sever

        if (ccmNino.getMuyDormido()) {
            intDeshidratacionGrave += 1;
        }

        if (ccmNino.getDejoComer()) {
            intDeshidratacionGrave += 1;
            ;
        }

        if (ccmNino.getOjosHundido()) {
            intDeshidratacionGrave += 1;
        }

        if (ccmNino.getSignoPliegue()) {
            intDeshidratacionGrave += 1;
        }

        if (intDeshidratacionGrave >= 3) {
            intDeshidratacionGrave = 1;
            deshidratacion_grave_Severa = true;
            strResultado += "Deshidratación Grave o Severa" + ", ";

        } else {
            intDeshidratacionGrave = 0;
        }

        // Diarrea Persistente

        if (ccmNino.getDiarreMayorDias()) {
            diarrea_persistente = true;
            strResultado += "Diarrea Persistente" + ", ";
        }

        // diarrea o pupú con moco y sangre

        if (ccmNino.getPopuSangre()) {
            diarrea_pupu_moco_sangre = true;
            strResultado += "Diarrea o Popú con Moco y Sangre" + ", ";
        }

        //Diarrea con Deshidratacion Moderada

        if (ccmNino.getInquietoIrritable()) {
            intDeshidratacionModerada += 1;
        }

        if (ccmNino.getBebeMuchaSed()) {
            intDeshidratacionModerada += 1;
        }

        if (ccmNino.getOjosHundido()) {
            intDeshidratacionModerada += 1;
        }

        if (ccmNino.getSignoPliegue()) {
            intDeshidratacionModerada += 1;

        }

        if (intDeshidratacionModerada >= 2) {
            intDeshidratacionModerada = 1;
            diarrea_deshidratacion_moderada = true;
            strResultado += "Diarrea con Deshidratación Moderada" + ", ";

        } else {
            intDeshidratacionModerada = 0;
        }

        //Diarrea sin Deshidratacion

        if (ccmNino.getPresentaDeshidratacion()) {
            diarrea_sin_deshidratación = true;
            intDiarreaSinDeshidratacion += 1;
            strResultado += "Diarrea sin Deshidratación" + ", ";
        }

        //Fiebre

        if (ccmNino.getCostadoCaliente()) {
            fiebre = true;
            strResultado += "Fiebre" + ", ";
        }

        //Verificaciones

        if ((intNeumoniaGrave + intNeumoniaNoComplicada + intTosOCatarro) > 1) {
            strResultado = "NoAplicaNeumonia  ";
        }

        if ((intDeshidratacionGrave + intDeshidratacionModerada + intDiarreaSinDeshidratacion) > 1) {
            strResultado = "NoDeshidratacion  ";
        }

        if (strResultado.length() == 0) {
            strResultado = "NoClasificación  ";
        }

        return strResultado.substring(0, strResultado.length() - 2);

    }

    public boolean getExisteCCMNinoByCustomer(Context context, String parametro)throws SQLException {
        return ccmNinoDao.getExisteCCMNinoByCustomer(context, parametro);
    }

    public ArrayList<CCMNino> getAllCCMNinosArrayListByName(Context context, String strWhere, String strOrderBy) throws SQLException {

        return ccmNinoDao.getAllCCMNinosArrayListCustom(context, "IdNino in (\n" +
                "select _id from ninos where NomNino like '%"+ strWhere +"%')", strOrderBy);
    }

}
