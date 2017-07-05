package com.sci.sponce.prjappscmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sci.sponce.prjappscmc.Fragment.DetNinoFragment;
import com.sci.sponce.prjappscmc.Fragment.ListNinosFragment;

public class DetNinoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_nino);

        if (savedInstanceState == null) {
            // Añadir fragmento de detalle
            Bundle arguments = new Bundle();
            arguments.putInt(DetNinoFragment.ID,
                    getIntent().getIntExtra(DetNinoFragment.ID, 0));


            DetNinoFragment fragment = new DetNinoFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_detalle, fragment)
                    .commit();
        }

    }
}
