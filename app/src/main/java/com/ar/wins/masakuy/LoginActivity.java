package com.ar.wins.masakuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btMasuk;
    EditText etEmail, etPassword;
    TextView btDaftar;
    SignInButton btMasukGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btMasuk = findViewById(R.id.bt_masuk_masuk);
        btDaftar = findViewById(R.id.bt_masuk_daftar);
        btMasukGoogle = findViewById(R.id.bt_masuk_google);
        etEmail = findViewById(R.id.et_masuk_email);
        etPassword = findViewById(R.id.et_masuk_password);

        btMasuk.setOnClickListener(this);
        btDaftar.setOnClickListener(this);
        btMasukGoogle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btMasuk){
            return;
        }
        if(view == btDaftar){
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        if(view == btMasukGoogle){
            return;
        }
    }
}
