package WS;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import Entidades.Municipio;
import Util.WSConfiguration;

public class MunicipioWs {


    private static String NAMESPACE = WSConfiguration.NAMESPACE;
    private static String SOAP_ACTION = WSConfiguration.SOAP_ACTION;
    public static final String SOAP_ADDRESS = WSConfiguration.SOAP_ADDRESS;


    public static Municipio getMunicipioById(int idMunicipio, String OPERATION_NAME) {
        String Resultado = "";
        Municipio municipio = new Municipio();

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo IdMunicipio = new PropertyInfo();
        IdMunicipio.setName("IdMunicipio");
        IdMunicipio.setValue(idMunicipio);
        IdMunicipio.setType(Integer.class);
        request.addProperty(IdMunicipio);

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
                SoapObject objMunicipio = (SoapObject) response.getProperty(i);

                municipio.setIdMunicipio(Integer.parseInt(objMunicipio.getProperty("IdMunicipio").toString()));
                municipio.setNomMunicipio(objMunicipio.getProperty("NomMunicipio").toString());
                municipio.setIdDepartamento(Integer.parseInt(objMunicipio.getProperty("IdDepartamento").toString()));

            }


        } catch (Exception exception) {

        }
        return municipio;
    }



}
