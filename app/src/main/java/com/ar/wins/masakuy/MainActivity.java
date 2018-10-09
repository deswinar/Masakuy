package com.ar.wins.masakuy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btDaftar;
    EditText etUsername, etEmail, etPassword;
    TextView btMasuk;
    SignInButton btMasukGoogle;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btDaftar = findViewById(R.id.bt_daftar_daftar);
        btMasukGoogle = findViewById(R.id.bt_daftar_google);
        btMasuk = findViewById(R.id.bt_daftar_masuk);
        etUsername = findViewById(R.id.et_daftar_username);
        etEmail = findViewById(R.id.et_daftar_email);
        etPassword = findViewById(R.id.et_daftar_password);

        btDaftar.setOnClickListener(this);
        btMasukGoogle.setOnClickListener(this);
        btMasuk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btDaftar){
            return;
        }
        if(view == btMasukGoogle){
            return;
        }
        if(view == btMasuk){
            return;
        }
    }
}
