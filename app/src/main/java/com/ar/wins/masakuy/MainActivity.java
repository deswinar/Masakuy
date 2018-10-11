package com.ar.wins.masakuy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btDaftar;
    EditText etUsername, etEmail, etPassword;
    TextView btMasuk;
    SignInButton btMasukGoogle;

    String username, email, password;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private UserProfileChangeRequest profileChangeRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

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

    public void daftarAkun(){
        username = etUsername.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Sukses Membuat Akun Email dan Password", Toast.LENGTH_SHORT).show();
                    firebaseUser = firebaseAuth.getCurrentUser();
                    profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                    firebaseUser.updateProfile(profileChangeRequest).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Sukses Membuat Akun " + firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Gagal Membuat Akun", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "Gagal Membuat Akun Email dan Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btDaftar){
            daftarAkun();
            startActivity(new Intent(this, EmailVerificationActivity.class));
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
