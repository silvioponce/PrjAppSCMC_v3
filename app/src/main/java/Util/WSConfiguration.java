package Util;

import android.app.Activity;

@SuppressWarnings("deprecation")
public class WSConfiguration extends Activity
{

    public static final String NAMESPACE = "http://tempuri.org/";
    //public static final String SOAP_ADDRESS = "http://it.sc.org.ni/scmc/servicioSTI.asmx";
    public static final String SOAP_ACTION = "http://tempuri.org/";
    public  static final String SOAP_ADDRESS = "http://192.168.56.1/scmc/servicioSTI.asmx";

     
    public WSConfiguration()
    {
    }
 

}