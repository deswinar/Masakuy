package com.ar.wins.masakuy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btMasuk, btDaftar;
    EditText etEmail, etPassword;
    String email, password;
    SignInButton btMasukGoogle;
    ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                finish();
                startActivity(new Intent(context, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }else{
                startActivity(new Intent(context, EmailVerificationActivity.class));
            }
        }

        btMasuk = findViewById(R.id.bt_masuk_masuk);
        btDaftar = findViewById(R.id.bt_masuk_daftar);
        btMasukGoogle = findViewById(R.id.bt_masuk_google);
        etEmail = findViewById(R.id.et_masuk_email);
        etPassword = findViewById(R.id.et_masuk_password);
        progressDialog = new ProgressDialog(this);

        btMasuk.setOnClickListener(this);
        btDaftar.setOnClickListener(this);
        btMasukGoogle.setOnClickListener(this);
    }

    public void masukAkun(){
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this, "Please enter your email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Please enter your password...", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    firebaseUser = firebaseAuth.getCurrentUser();
                    if(firebaseUser.isEmailVerified()) {
                        finish();
                        startActivity(new Intent(context, HomeActivity.class));
                    }else {
                        startActivity(new Intent(context, EmailVerificationActivity.class));
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btMasuk){
            masukAkun();
            return;
        }
        if(view == btDaftar){
            startActivity(new Intent(context, MainActivity.class));
            return;
        }
        if(view == btMasukGoogle){
            return;
        }
    }
}
