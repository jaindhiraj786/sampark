package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOtp extends AppCompatActivity {
    String mobile;
    private String verifactionid;
    private FirebaseAuth mAuth;
    PinView pinFromUser;
    Button buttonCode;
    String phonenumber;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_otp);

        pinFromUser = findViewById(R.id.pinView);
        buttonCode = findViewById(R.id.buttonCode);



        mAuth = FirebaseAuth.getInstance();
        String code = "+91";


        mobile = getIntent().getStringExtra("phonenumber");
        Log.e("mobile",mobile);
         phonenumber = code + mobile;
        Log.e("phonenumber", phonenumber);
        sendVerificationCode(phonenumber);

        buttonCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = pinFromUser.getText().toString();
                verifyCode(code);
            }
        });


    }

    private void sendVerificationCode(String phonenumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verifactionid = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pinFromUser.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    };

    public void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifactionid, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(VerifyOtp.this, UpdatePassword.class);
                    intent.putExtra("phonenumber",mobile);
                    startActivity(intent);
                } else {
                    Toast.makeText(VerifyOtp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("OTP", task + "");
                }
            }
        });
    }
}


