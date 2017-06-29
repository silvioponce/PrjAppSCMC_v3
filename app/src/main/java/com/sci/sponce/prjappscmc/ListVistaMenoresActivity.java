package com.sci.sponce.prjappscmc;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sci.sponce.prjappscmc.Fragment.ListCasoMenoresFragment;
import com.sci.sponce.prjappscmc.Fragment.ListVisitaMenoresFragment;

public class ListVistaMenoresActivity extends AppCompatActivity implements ListVisitaMenoresFragment.OnFragmentInteractionListener {

    public String IdNino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vista_menores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("IdNino"))
            IdNino = getIntent().getStringExtra("IdNino");


        // Agregar fragmento de lista

        Bundle bundle = new Bundle();

        bundle.putString("IdNino", IdNino);
        ListVisitaMenoresFragment frag = new ListVisitaMenoresFragment();
        frag.setArguments(bundle);


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor_lista, frag)
                .commit();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
