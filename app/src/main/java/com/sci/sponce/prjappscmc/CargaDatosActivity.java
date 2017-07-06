package com.sci.sponce.prjappscmc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BL.AdminBL;
import BL.CCMNinoBL;
import BL.CCMRecienNacidoBL;
import BL.NinoBL;
import BL.TratamientoNinoBL;
import BL.TratamientoRecienNacidoBL;
import BL.VisitasNinosMayorBL;
import BL.VisitasNinosMenorBL;
import Entidades.CCMNino;
import Entidades.CCMRecienNacido;
import Entidades.Nino;
import Entidades.TratamientoNino;
import Entidades.TratamientoRecienNacido;
import Entidades.VisitasNinosMayor;
import Entidades.VisitasNinosMenor;
import WS.NinoWs;

public class CargaDatosActivity extends AppCompatActivity implements View.OnClickListener {

    AdminBL adminBL = new AdminBL();

    CCMRecienNacido ccmRecienNacido = new CCMRecienNacido();
    CCMNino ccmNino = new CCMNino();

    Button btnCargarNinos;
    ImageButton btnSubirArchivos;
    NinoBL ninoBL = new NinoBL();
    CCMNinoBL ccmNinoBL = new CCMNinoBL();
    CCMRecienNacidoBL ccmRecienNacidoBL = new CCMRecienNacidoBL();
    VisitasNinosMenorBL visitasNinosMenorBL = new VisitasNinosMenorBL();
    VisitasNinosMayorBL visitasNinosMayorBL = new VisitasNinosMayorBL();

    ProgressDialog progressDialog;

    Handler _handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_datos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _handler = new Handler();

        btnCargarNinos = (Button) findViewById(R.id.btnCargarNinos);
        btnCargarNinos.setOnClickListener(this);

        btnSubirArchivos = (ImageButton) findViewById(R.id.btnSubirArchivos);
        btnSubirArchivos.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubirArchivos:
                progressBarDialog();
               /* if (adminBL.isOnline(getApplicationContext())) {
                    progressBarDialog();
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(CargaDatosActivity.this);

                    alertDialog.setTitle("ERROR...");
                    alertDialog.setMessage("No hay conexion de Internet");
                    alertDialog.setIcon(R.mipmap.ic_upload);
                    alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alertDialog.show();

                }
*/
                break;

        }
    }

    private void guardarCasos(Nino nino) {
        List<CCMRecienNacido> listCCCMRecienNacido = new ArrayList<CCMRecienNacido>();
        List<CCMNino> listCCMNino = new ArrayList<CCMNino>();

        try {
            listCCCMRecienNacido = ccmRecienNacidoBL.getAllCCMRecienNacidosArrayListCustom(getApplicationContext(), "IdNino = " + nino.get_id());
            listCCMNino = ccmNinoBL.getAllCCMNinosArrayListCustom(getApplicationContext(), "IdNino = " + nino.get_id());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < listCCCMRecienNacido.size(); x++) {
            ccmRecienNacido = listCCCMRecienNacido.get(x);

            if (ccmRecienNacido.getIdCCMRecienNacido() == 0) {
                //ccmRecienNacido.setIdNino(nino.getIdNino());
                ccmRecienNacido.setIdCCMRecienNacido(NinoWs.insertCCMRecienNacido(ccmRecienNacido, nino.getIdNino(), "InsertCCMRecienNacido"));
                try {
                    ccmRecienNacidoBL.GuardarNino(getApplicationContext(), ccmRecienNacido);





                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            TratamientoRecienNacido tratamientoRecienNacido = new TratamientoRecienNacido();
            TratamientoRecienNacidoBL tratamientoRecienNacidoBL = new TratamientoRecienNacidoBL();

            tratamientoRecienNacido = tratamientoRecienNacidoBL.getTratamientoRecienNacidoByCustomer(getApplicationContext(), "IdCCMRecienNacido = " + ccmRecienNacido.get_id());

            if (tratamientoRecienNacido.getIdTratamientoReciendNacido() == 0) {
                tratamientoRecienNacido.setIdCCMRecienNacido(ccmRecienNacido.getIdCCMRecienNacido());
                tratamientoRecienNacido.setIdTratamientoReciendNacido(NinoWs.InsertTratamientoRecienNacido(tratamientoRecienNacido, tratamientoRecienNacido.getIdCCMRecienNacido(), "InsertTratamientoRecienNacido"));

                try {
                    tratamientoRecienNacido.setIdCCMRecienNacido(ccmRecienNacido.get_id());
                    tratamientoRecienNacidoBL.GuardarTratamientoRecienNacido(getApplicationContext(), tratamientoRecienNacido);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            guardarVisitasMenores(ccmRecienNacido.get_id());

        }

        for (int x = 0; x < listCCMNino.size(); x++) {
            ccmNino = listCCMNino.get(x);

            if (ccmNino.getIdCCMNino() == 0) {
                //ccmNino.setIdNino(nino.getIdNino());
                ccmNino.setIdCCMNino(NinoWs.insertCCMNino(ccmNino, nino.getIdNino(), "InsertCCMNino"));
                try {
                    ccmNinoBL.GuardarCCMNino(getApplicationContext(), ccmNino);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            List<TratamientoNino> listTratatamientoNino = new ArrayList<TratamientoNino>();
            TratamientoNinoBL tratamientoNinoBL = new TratamientoNinoBL();
            TratamientoNino tratamientoNino = new TratamientoNino();

            int intIdTratamiento;

            listTratatamientoNino = tratamientoNinoBL.getAllTratamientoNinosListCustom(getApplicationContext(), "IdCCMNino = " + String.valueOf(ccmNino.get_id()));

            for (int i = 0; i < listTratatamientoNino.size(); i++) {

                tratamientoNino = listTratatamientoNino.get(i);
                tratamientoNino.setIdCCMNino(ccmNino.getIdCCMNino());

                if (tratamientoNino.getIdTratamientoNinos() == 0) {
                    tratamientoNino.setIdTratamientoNinos(NinoWs.InsertTratamientoNino(tratamientoNino, "InsertTratamientoNino" ));

                    try {
                        tratamientoNino.setIdCCMNino(ccmNino.get_id());
                        tratamientoNinoBL.GuardarTratamientoNino(getApplicationContext(), tratamientoNino);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }

            guardarVisitasMayores(ccmNino.get_id());
        }


    }

    private void guardarVisitasMenores(int idCCMNinosMenores) {
        List<VisitasNinosMenor> visitasNinosMenors = new ArrayList<VisitasNinosMenor>();

        try {
            visitasNinosMenors = visitasNinosMenorBL.getAllVisitasNinosMenoresArrayListCustom(getApplicationContext(), "IdCCMRecienNacido = " + idCCMNinosMenores);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < visitasNinosMenors.size(); x++) {
            VisitasNinosMenor visitasNinosMenor = visitasNinosMenors.get(x);

            if (visitasNinosMenor.getIdVisita() == 0) {
                visitasNinosMenor.setIdVisita(NinoWs.insertVisitaMenores(visitasNinosMenor, ccmRecienNacido.getIdCCMRecienNacido(), "InsertVisitasNinosMenore"));
                try {
                    visitasNinosMenorBL.GuardarVisitaNinosMenores(getApplicationContext(), visitasNinosMenor);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private void guardarVisitasMayores(int IdCCMNino) {
        List<VisitasNinosMayor> visitasNinosMayors = new ArrayList<VisitasNinosMayor>();

        try {
            visitasNinosMayors = visitasNinosMayorBL.getAllVisitasNinosMayoresArrayListCustom(getApplicationContext(), "IdCCMNino = " + IdCCMNino);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < visitasNinosMayors.size(); x++) {
            VisitasNinosMayor visitasNinosMayor = visitasNinosMayors.get(x);

            if (visitasNinosMayor.getIdVisita() == 0) {
                visitasNinosMayor.setIdVisita(NinoWs.insertVisitaMayores(visitasNinosMayor, ccmNino.getIdCCMNino(), "InsertVisitasNinosMayore"));

                visitasNinosMayorBL.GuardarVisitaNinosMayores(getApplicationContext(), visitasNinosMayor);

            }
        }

    }

    public void progressBarDialog() {

        progressDialog = new ProgressDialog(CargaDatosActivity.this);

        progressDialog.setTitle("Por favor Espere ...");
        progressDialog.setMessage("Subiendo Archivos ...");
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);

        progressDialog.show();

        try {
            progressDialog.setMax(ninoBL.getAllNinosArrayList(getApplicationContext()).size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    List<Nino> listNino = new ArrayList<Nino>();
                    try {
                        listNino = ninoBL.getAllNinosArrayList(getApplicationContext());
                        progressDialog.incrementProgressBy(1);
                        for (int x = 0; x < listNino.size(); x++) {
                            Nino nino = listNino.get(x);

                            if (nino.getIdNino() == 0) {
                                nino.setIdNino(NinoWs.insertNino(nino, "InsertNino"));
                                ninoBL.GuardarNino(getApplicationContext(), nino);

                            }

                            guardarCasos(nino);

                            Thread.sleep(1000);

                            _handler.post(new Runnable() {
                                public void run() {
                                    progressDialog.incrementProgressBy(1);
                                }
                            });

                            if (progressDialog.getProgress() == progressDialog.getMax()) {
                                progressDialog.dismiss();
                            }

                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // some stuff to do the task...

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
