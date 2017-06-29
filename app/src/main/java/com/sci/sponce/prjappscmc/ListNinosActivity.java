package com.sci.sponce.prjappscmc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sci.sponce.prjappscmc.Fragment.DetNinoFragment;
import com.sci.sponce.prjappscmc.Fragment.ListNinosFragment;

public class ListNinosActivity extends AppCompatActivity implements ListNinosFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ninos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetNinoActivity.class);
                intent.putExtra(DetNinoFragment.ID, 0);

                startActivity(intent);
            }
        });

        // Agregar fragmento de lista
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, ListNinosFragment.crear())

                .commit();
    }

    private void cargarFragmentoDetalle(String id) {
        Bundle arguments = new Bundle();
        arguments.putString(DetNinoFragment.ID, id);
        DetNinoFragment fragment = new DetNinoFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()

                .replace(R.id.contenedor_detalle, fragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
