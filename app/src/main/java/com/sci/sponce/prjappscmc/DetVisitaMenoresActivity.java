package com.sci.sponce.prjappscmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sci.sponce.prjappscmc.Fragment.DetVisitaMenoresFragment;

public class DetVisitaMenoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_visita_menores);

        if (savedInstanceState == null) {
            // Añadir fragmento de detalle
            Bundle arguments = new Bundle();
            arguments.putInt(DetVisitaMenoresFragment.ID, getIntent().getIntExtra(DetVisitaMenoresFragment.ID, 0));
            arguments.putInt("idCCMRecienNacido", getIntent().getIntExtra("idCCMRecienNacido", 0));
            arguments.putInt("idNino", getIntent().getIntExtra("idCCMRecienNacido", 0));
            arguments.putBoolean("modoEdit", getIntent().getBooleanExtra("modoEdit", false));



            DetVisitaMenoresFragment fragment = new DetVisitaMenoresFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_detalle, fragment)
                    .commit();
        }
    }
}
