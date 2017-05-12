package WS;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Entidades.CCMNino;
import Entidades.CCMRecienNacido;
import Entidades.Nino;
import Entidades.TratamientoNino;
import Entidades.TratamientoRecienNacido;
import Entidades.VisitasNinosMayor;
import Entidades.VisitasNinosMenor;
import Util.WSConfiguration;

public class NinoWs {

    private static String NAMESPACE = WSConfiguration.NAMESPACE;
    private static String SOAP_ACTION = WSConfiguration.SOAP_ACTION;
    public static final String SOAP_ADDRESS = WSConfiguration.SOAP_ADDRESS;

    static DateFormat formate = DateFormat.getDateInstance(DateFormat.MEDIUM);

    static String DATE_FORMAT = "MM/dd/yyyy";
    static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    static Calendar calendar = Calendar.getInstance();

    public static int insertNino(Nino nino, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Nino = new PropertyInfo();

        Date date = new Date(String.valueOf(nino.getFechaNac()));

        calendar.setTime(date);

        Nino.setName("IdComunidad");
        Nino.setValue(nino.getIdComunidad());
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("IdUsuario");
        Nino.setValue(nino.getIdUsuario());
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("NomMadre");
        Nino.setValue(nino.getNomMadre());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("NomNino");
        Nino.setValue(nino.getNomNino());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Sexo");
        Nino.setValue(nino.getSexo());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FechaRegistro");
        Nino.setValue(sdf.format(nino.getFechaNac()));
        Nino.setType(java.sql.Date.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FechaNac");
        Nino.setValue(sdf.format(nino.getFechaNac()));
        Nino.setType(java.sql.Date.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("PesoMas2500");
        Nino.setValue(nino.getPesoMas2500());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Resultado = Integer.parseInt(response.getProperty(0).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resultado;
    }

    public static int insertCCMRecienNacido(CCMRecienNacido ccmRecienNacido, int idNino, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Nino = new PropertyInfo();

        Nino.setName("IdNino");
        Nino.setValue(idNino);
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FechaCCM");
        Nino.setValue(sdf.format(ccmRecienNacido.getFechaCCM()));
        Nino.setType(java.sql.Date.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("LugarAtencion");
        Nino.setValue(ccmRecienNacido.getLugarAtencion());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("NoPuedeTomarPecho");
        Nino.setValue(ccmRecienNacido.getNoPuedeTomarPecho());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Convulsiones");
        Nino.setValue(ccmRecienNacido.getConvulsiones());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("HundePiel");
        Nino.setValue(ccmRecienNacido.getHundePiel());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("RuidosRespirar");
        Nino.setValue(ccmRecienNacido.getRuidosRespirar());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("RespRapida");
        Nino.setValue(ccmRecienNacido.getRespRapida());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FrecCardiaca");
        Nino.setValue(ccmRecienNacido.getFrecCardiaca());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Fibre");
        Nino.setValue(ccmRecienNacido.getFibre());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Temperatura");
        Nino.setValue(ccmRecienNacido.getTemperatura());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("PielOjosAmarillos");
        Nino.setValue(ccmRecienNacido.getPielOjosAmarillos());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("MovEstimulos");
        Nino.setValue(ccmRecienNacido.getMovEstimulos());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("OmbligoPus");
        Nino.setValue(ccmRecienNacido.getOmbligoPus());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("PielUmbilicalRoja");
        Nino.setValue(ccmRecienNacido.getPielUmbilicalRoja());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("PielGranos");
        Nino.setValue(ccmRecienNacido.getPielGranos());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("OjosPus");
        Nino.setValue(ccmRecienNacido.getOjosPus());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("EntregoReferencia");
        Nino.setValue(ccmRecienNacido.getEntregoReferencia());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Otra");
        Nino.setValue(ccmRecienNacido.getOtra());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Obsevaciones");
        Nino.setValue(ccmRecienNacido.getObsevaciones());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("IdUsuario");
        Nino.setValue(ccmRecienNacido.getIdUsuario());
        Nino.setType(int.class);
        request.addProperty(Nino);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Resultado = Integer.parseInt(response.getProperty(0).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resultado;
    }

    public static int insertCCMNino(CCMNino ccmNino,int idNino, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Nino = new PropertyInfo();

        Nino.setName("IdNino");
        Nino.setValue(idNino);
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FechaCCM");
        Nino.setValue(sdf.format(ccmNino.getFechaCCM()));
        Nino.setType(java.sql.Date.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("LugarAtencion");
        Nino.setValue(ccmNino.getLugarAtencion());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("DificilDespertar");
        Nino.setValue(ccmNino.getDificilDespertar());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("NoPuedeTomarPecho");
        Nino.setValue(ccmNino.getNoPuedeTomarPecho());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("VomitaTodo");
        Nino.setValue(ccmNino.getVomitaTodo());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Ataques");
        Nino.setValue(ccmNino.getAtaques());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("HundePiel");
        Nino.setValue(ccmNino.getHundePiel());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("RuidosRespirar");
        Nino.setValue(ccmNino.getRuidosRespirar());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("RespiracionRapida");
        Nino.setValue(ccmNino.getRespiracionRapida());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FrecCardiaca");
        Nino.setValue(ccmNino.getFrecCardiaca());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("SignoNeumonia");
        Nino.setValue(ccmNino.getSignoNeumonia());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("TosCatarro");
        Nino.setValue(ccmNino.getTosCatarro());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Moco");
        Nino.setValue(ccmNino.getMoco());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("MuyDormido");
        Nino.setValue(ccmNino.getMuyDormido());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("DejoComer");
        Nino.setValue(ccmNino.getDejoComer());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("OjosHundido");
        Nino.setValue(ccmNino.getOjosHundido());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("SignoPliegue");
        Nino.setValue(ccmNino.getSignoPliegue());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("DiarreMayorDias");
        Nino.setValue(ccmNino.getDiarreMayorDias());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("PopuSangre");
        Nino.setValue(ccmNino.getPopuSangre());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("InquietoIrritable");
        Nino.setValue(ccmNino.getInquietoIrritable());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("BebeMuchaSed");
        Nino.setValue(ccmNino.getBebeMuchaSed());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("PresentaDeshidratacion");
        Nino.setValue(ccmNino.getPresentaDeshidratacion());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("CostadoCaliente");
        Nino.setValue(ccmNino.getCostadoCaliente());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("EntregoReferencia");
        Nino.setValue(ccmNino.getEntregoReferencia());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Observacion");
        Nino.setValue(ccmNino.getObservacion());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("IdUsuario");
        Nino.setValue(ccmNino.getIdUsuario());
        Nino.setType(int.class);
        request.addProperty(Nino);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Resultado = Integer.parseInt(response.getProperty(0).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resultado;
    }

    public static int insertVisitaMenores(VisitasNinosMenor visitasNinosMenor, int idCCMNinosMenores, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Nino = new PropertyInfo();

        Nino.setName("IdCCMRecienNacido");
        Nino.setValue(idCCMNinosMenores);
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FechaVisita");
        Nino.setValue(sdf.format(visitasNinosMenor.getFechaVisita()));
        Nino.setType(java.sql.Date.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("CumpMedRect");
        Nino.setValue(visitasNinosMenor.getCumpMedRect());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("TomandoDosisIndicada");
        Nino.setValue(visitasNinosMenor.getTomandoDosisIndicada());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("ResultadoVisita");
        Nino.setValue(visitasNinosMenor.getResultadoVisita());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Observacion");
        Nino.setValue(visitasNinosMenor.getObservacion());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("IdUsuario");
        Nino.setValue(visitasNinosMenor.getIdUsuario());
        Nino.setType(int.class);
        request.addProperty(Nino);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Resultado = Integer.parseInt(response.getProperty(0).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resultado;
    }

    public static int insertVisitaMayores(VisitasNinosMayor visitasNinosMayor, int IdCCMNino, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Nino = new PropertyInfo();

        Nino.setName("IdCCMNino");
        Nino.setValue(IdCCMNino);
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("FechaVisita");
        Nino.setValue(sdf.format(visitasNinosMayor.getFechaVisita()));
        Nino.setType(java.sql.Date.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("CumpMedRect");
        Nino.setValue(visitasNinosMayor.getCumpMedRect());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("TomandoDosisIndicada");
        Nino.setValue(visitasNinosMayor.getTomandoDosisIndicada());
        Nino.setType(Boolean.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("ResultadoVisita");
        Nino.setValue(visitasNinosMayor.getResultadoVisita());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("Observacion");
        Nino.setValue(visitasNinosMayor.getObservacion());
        Nino.setType(String.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("IdUsuario");
        Nino.setValue(visitasNinosMayor.getIdUsuario());
        Nino.setType(int.class);
        request.addProperty(Nino);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Resultado = Integer.parseInt(response.getProperty(0).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resultado;
    }

    public static int InsertTratamientoRecienNacido(TratamientoRecienNacido tratamientoRecienNacido, int IdCCMNino, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Nino = new PropertyInfo();

        Nino.setName("IdCCMRecienNacido");
        Nino.setValue(IdCCMNino);
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("IdTratamiento");
        Nino.setValue(tratamientoRecienNacido.getIdTratamiento());
        Nino.setType(int.class);
        request.addProperty(Nino);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Resultado = Integer.parseInt(response.getProperty(0).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resultado;
    }

    public static int InsertTratamientoNino(TratamientoNino tratamientoNino, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Nino = new PropertyInfo();

        Nino.setName("IdCCMNino");
        Nino.setValue(tratamientoNino.getIdCCMNino());
        Nino.setType(int.class);
        request.addProperty(Nino);

        Nino = new PropertyInfo();
        Nino.setName("IdTratamiento");
        Nino.setValue(tratamientoNino.getIdTratamiento());
        Nino.setType(int.class);
        request.addProperty(Nino);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Resultado = Integer.parseInt(response.getProperty(0).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resultado;
    }

}
