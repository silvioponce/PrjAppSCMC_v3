package com.sci.sponce.prjappscmc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sci.sponce.prjappscmc.Fragment.DetCasoMenoresFragment;
import com.sci.sponce.prjappscmc.Fragment.DetNinoFragment;
import com.sci.sponce.prjappscmc.Fragment.ListCasoMenoresFragment;
import com.sci.sponce.prjappscmc.Fragment.ListNinosFragment;



public class ListCasoMenoresActivity extends AppCompatActivity implements ListCasoMenoresFragment.OnFragmentInteractionListener {

    public String IdNino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_caso_menores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetCasoMenoresActivity.class);
                intent.putExtra(DetCasoMenoresFragment.ID, 0);

                startActivity(intent);
            }
        });


        if (getIntent().hasExtra("Idnino"))
            IdNino = getIntent().getStringExtra("IdNino");


        // Agregar fragmento de lista

        Bundle bundle = new Bundle();

        bundle.putString("IdNino", IdNino);
        ListCasoMenoresFragment frag = new ListCasoMenoresFragment();
        frag.setArguments(bundle);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, frag.crear())
                .commit();



    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
