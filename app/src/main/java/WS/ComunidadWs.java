package WS;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;

import Entidades.Comunidad;
import Util.WSConfiguration;

public class ComunidadWs {

    private static String NAMESPACE = WSConfiguration.NAMESPACE;
    private static String SOAP_ACTION = WSConfiguration.SOAP_ACTION;
    public static final String SOAP_ADDRESS = WSConfiguration.SOAP_ADDRESS;

    static String DATE_FORMAT = "MM/dd/yyyy";
    static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public static Comunidad getComunidadById(int idComunidad, String OPERATION_NAME) {
        String Resultado = "";
        Comunidad comunidad = new Comunidad();

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo IdComunidad = new PropertyInfo();
        IdComunidad.setName("IdComunidad");
        IdComunidad.setValue(idComunidad);
        IdComunidad.setType(Integer.class);
        request.addProperty(IdComunidad);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject objComunidad = (SoapObject) response.getProperty(i);

                comunidad.setIdComunidad(Integer.parseInt(objComunidad.getProperty("IdComunidad").toString()));
                comunidad.setNomComunidad(objComunidad.getProperty("NomComunidad").toString());
                comunidad.setIdMunicipio(Integer.parseInt(objComunidad.getProperty("IdMunicipio").toString()));

            }


        } catch (Exception exception) {

        }
        return comunidad;
    }

    public static int insertComunidad(Comunidad comunidad,int idComunidad, String OPERATION_NAME) {
        int Resultado = 0;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Comunidad = new PropertyInfo();

        Comunidad.setName("IdComunidad");
        Comunidad.setValue(idComunidad);
        Comunidad.setType(int.class);
        request.addProperty(Comunidad);

        Comunidad = new PropertyInfo();
        Comunidad.setName("NomComunidad");
        Comunidad.setValue(comunidad.getNomComunidad());
        Comunidad.setType(String.class);
        request.addProperty(Comunidad);

        Comunidad = new PropertyInfo();
        Comunidad.setName("IdMunicipio");
        Comunidad.setValue(comunidad.getIdMunicipio());
        Comunidad.setType(int.class);
        request.addProperty(Comunidad);

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
