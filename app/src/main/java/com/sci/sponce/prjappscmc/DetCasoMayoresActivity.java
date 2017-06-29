package com.sci.sponce.prjappscmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sci.sponce.prjappscmc.Fragment.DetCasoMayoresFragment;
import com.sci.sponce.prjappscmc.R;

public class DetCasoMayoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_caso_mayores);

        if (savedInstanceState == null) {
            // Añadir fragmento de detalle
            Bundle arguments = new Bundle();
            arguments.putInt(DetCasoMayoresFragment.ID,
                    getIntent().getIntExtra(DetCasoMayoresFragment.ID, 0));

            arguments.putBoolean("modoEdit", getIntent().getBooleanExtra("modoEdit", false));
            arguments.putString("idCCMRecienNacido", getIntent().getStringExtra("idCCMRecienNacido"));

            DetCasoMayoresFragment fragment = new DetCasoMayoresFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_detalle, fragment)
                    .commit();
        }
    }
}
