package com.ar.wins.masakuy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerificationActivity extends AppCompatActivity {

    TextView tvStatusAkun;
    Button btResend;
    SpannableString textResend;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

//        if(firebaseUser.isEmailVerified()){
//            startActivity(new Intent(this, HomeActivity.class));
//        }

        tvStatusAkun = findViewById(R.id.tv_status_akun);
        btResend = findViewById(R.id.bt_resend);
        btResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerification();
            }
        });

//        sendVerification();

        tvStatusAkun.setText(String.valueOf(firebaseUser.isEmailVerified()));
    }

    public void sendVerification(){
        firebaseUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    firebaseUser.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            tvStatusAkun.setText(String.valueOf(firebaseUser.isEmailVerified()));
                        }
                    });
//                    firebaseAuth.signOut();
//                    finish();
//                    startActivity(new Intent(this, LoginActivity.class));
                    Toast.makeText(EmailVerificationActivity.this, "Email verifikasi telah terkirim", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EmailVerificationActivity.this, "Email verifikasi gagal terkirim", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
