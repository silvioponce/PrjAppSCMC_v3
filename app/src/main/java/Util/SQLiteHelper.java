package Util;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SQLiteHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 11;
	private String DB_PATH;
	private SQLiteDatabase myDataBase;
	private android.content.Context Context;
	private static final String DB_NAME = "SGCRN.sqlite";
	
	
	public SQLiteHelper(android.content.Context context)
	{
		super(context, DB_NAME, null, DATABASE_VERSION);
		Context = context;
		DB_PATH = Context.getApplicationInfo().dataDir + "/databases/";
		// DB_PATH = Environment.getExternalStorageDirectory().getPath()
		// + "/databases/";// sdcard/";
	}
	
	
	public SQLiteDatabase getdatabase()
	{
		return myDataBase;
	}
	
	
	public void createDataBase() throws IOException
	{
		if (!checkDataBase())
		{
			copyDataBase();
		}

        //copyDataBase();

		getReadableDatabase();
		this.close();
	}
	
	
	private boolean checkDataBase()
	{
		SQLiteDatabase checkDB = null;
		try
		{
			checkDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
					SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		}
		catch (SQLiteException sqle)
		{
			System.out.println(sqle);
		}
		boolean ok = checkDB != null;
		if (ok)
		{
			checkDB.close();
		}
		return ok;
	}
	
	
	private void copyDataBase() throws IOException
	{
		InputStream myInput = Context.getAssets().open(DB_NAME);
		new File(DB_PATH).mkdirs();
		OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0)
		{
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	
	
	public void openDataBase() throws SQLException
	{
		myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
				SQLiteDatabase.NO_LOCALIZED_COLLATORS);
	}
	
	
	@Override
	public synchronized void close()
	{
		if (myDataBase != null) myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// myContext.deleteDatabase(DB_NAME);
        //db.execSQL("DROP TABLE IF EXISTS Usuarios");

	}

    public boolean isOnline(android.content.Context ctx) {

        if (verificaWifiDatos(ctx)){
            if (ping()){
                return true;
            }
            else return false;
        }
        else return false;
    }

    public boolean verificaWifiDatos(android.content.Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < redes.length; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    public static boolean ping() {
        Boolean resultado = false;
        try {
            final URL url = new URL("http://www.google.com");
            final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(200); // mTimeout is in seconds
            final long startTime = System.currentTimeMillis();
            urlConn.connect();
            final long endTime = System.currentTimeMillis();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                resultado = true;
                return resultado;
            }
        } catch (final MalformedURLException e1) {
            e1.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

	public boolean hasInternetAccess(android.content.Context context) {
		if (verificaWifiDatos(context)) {
			try {
				HttpURLConnection urlc = (HttpURLConnection)
						(new URL("http://clients3.google.com/generate_204")
								.openConnection());
				urlc.setRequestProperty("User-Agent", "Android");
				urlc.setRequestProperty("Connection", "close");
				urlc.setConnectTimeout(100);
				urlc.connect();
				return (urlc.getResponseCode() == 204 &&
						urlc.getContentLength() == 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return  false;
		}
		return false;
	}



}
