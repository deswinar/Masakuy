package com.ar.wins.masakuy;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerificationActivity extends AppCompatActivity {

    TextView tvResend;
    SpannableString textResend;
    Context context;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser.isEmailVerified()){
            Toast.makeText(context, "Email telah terverifikasi", Toast.LENGTH_SHORT).show();
        }

        tvResend = findViewById(R.id.tv_resend);
        setTextResend();

        sendVerification();


    }

    public void setTextResend(){

        textResend = new SpannableString("Link verifikasi telah dikirim ke email anda. Mohon verifikasi akun melalui email anda. Resend Verification");
        textResend.setSpan(new ForegroundColorSpan(Color.RED), 87, 106, 0 );

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                sendVerification();
            }
        };
        textResend.setSpan(clickableSpan, 87, 106, 0);

        tvResend.setMovementMethod(LinkMovementMethod.getInstance());
        tvResend.setText(textResend, TextView.BufferType.SPANNABLE);
    }

    public void sendVerification(){
        firebaseUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Email verifikasi telah terkirim", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Email verifikasi gagal terkirim", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
