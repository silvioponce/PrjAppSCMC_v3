package com.sci.sponce.prjappscmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sci.sponce.prjappscmc.Fragment.DetVisitaMayoresFragment;

public class PruebaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        Bundle arguments = new Bundle();
        arguments.putInt(DetVisitaMayoresFragment.ID, getIntent().getIntExtra(DetVisitaMayoresFragment.ID, 0));
        arguments.putInt("idCCMRecienNacido", getIntent().getIntExtra("idCCMRecienNacido", 0));
        arguments.putInt("idNino", getIntent().getIntExtra("idCCMRecienNacido", 0));
        arguments.putBoolean("modoEdit", getIntent().getBooleanExtra("modoEdit", false));



        DetVisitaMayoresFragment fragment = new DetVisitaMayoresFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contenedor_detalle, fragment)
                .commit();
    }
}
