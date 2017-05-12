package WS;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import Entidades.Rol;
import Entidades.Usuario;
import Util.WSConfiguration;

/**
 * Created by sponce on 07/04/2015.
 */
public class UsuarioWs {

    private static String NAMESPACE = WSConfiguration.NAMESPACE;
    private static String SOAP_ACTION = WSConfiguration.SOAP_ACTION;
    public static final String SOAP_ADDRESS = WSConfiguration.SOAP_ADDRESS;

    public static Usuario getUsuarioById(int idUsuario, String OPERATION_NAME) {
        String Resultado = "";
        Usuario usuario = new Usuario();

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo IdUsuario = new PropertyInfo();
        IdUsuario.setName("IdUsuario");
        IdUsuario.setValue(idUsuario);
        IdUsuario.setType(Integer.class);
        request.addProperty(IdUsuario);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Rol rol = new Rol();

            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject objUsuario = (SoapObject) response.getProperty(i);

                usuario.setIdUsuario(Integer.parseInt(objUsuario.getProperty("IdUsuario").toString()));
                usuario.setNomUsuario(objUsuario.getProperty("NomUsuario").toString());
                usuario.setUsuario(objUsuario.getProperty("Usuario").toString());
                usuario.setIdComunidad(Integer.parseInt(objUsuario.getProperty("IdComunidad").toString()));
                usuario.setIdRol(Integer.parseInt(objUsuario.getProperty("IdRol").toString()));

            }


        } catch (Exception exception) {

        }
        return usuario;
    }

    public static Usuario getVerificaUsuario(String User, String Password, String OPERATION_NAME) {
        String Resultado = "";
        Usuario usuario = new Usuario();

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        PropertyInfo Usuario = new PropertyInfo();
        Usuario.setName("User");
        Usuario.setValue(User);
        Usuario.setType(String.class);
        request.addProperty(Usuario);

        Usuario = new PropertyInfo();
        Usuario.setName("Password");
        Usuario.setValue(Password);
        Usuario.setType(String.class);
        request.addProperty(Usuario);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            httpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            //Object obj = envelope.getResponse();
            SoapObject response = (SoapObject) envelope.bodyIn;

            Rol rol = new Rol();

            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject objUsuario = (SoapObject) response.getProperty(i);

                if (Integer.valueOf(objUsuario.getProperty("IdUsuario").toString())==0){
                    break;
                }

                usuario.setIdUsuario(Integer.parseInt(objUsuario.getProperty("IdUsuario").toString()));
                usuario.setNomUsuario(objUsuario.getProperty("NomUsuario").toString());
                usuario.setUsuario(objUsuario.getProperty("Usuario").toString());
                usuario.setContrasena(objUsuario.getProperty("Contrasena").toString());
                usuario.setEstado(Boolean.parseBoolean((String) objUsuario.getProperty("Estado").toString()));
                usuario.setIdComunidad(Integer.parseInt(objUsuario.getProperty("IdComunidad").toString()));
                usuario.setIdRol(Integer.parseInt(objUsuario.getProperty("IdRol").toString()));

                break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }


}
