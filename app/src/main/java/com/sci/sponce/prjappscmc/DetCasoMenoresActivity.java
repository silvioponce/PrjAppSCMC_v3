package com.sci.sponce.prjappscmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sci.sponce.prjappscmc.Fragment.DetCasoMenoresFragment;
import com.sci.sponce.prjappscmc.Fragment.DetNinoFragment;

public class DetCasoMenoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_caso_menores);

        if (savedInstanceState == null) {
            // Añadir fragmento de detalle
            Bundle arguments = new Bundle();
            arguments.putString(DetCasoMenoresFragment.ID,
                    getIntent().getStringExtra(DetCasoMenoresFragment.ID));
            DetCasoMenoresFragment fragment = new DetCasoMenoresFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_detalle, fragment)
                    .commit();
        }
    }
}
