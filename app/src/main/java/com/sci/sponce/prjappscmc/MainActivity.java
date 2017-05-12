package com.sci.sponce.prjappscmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.preference.PreferenceActivity;

import android.widget.Button;

public class MainActivity extends PreferenceActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.preferencias);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnLogin:

        }
    }
}
