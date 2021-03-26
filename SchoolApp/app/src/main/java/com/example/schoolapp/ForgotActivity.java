package com.example.schoolapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgotActivity extends AppCompatActivity {
    TextView verifyMobile;
    EditText editNumber;
    Button buttonVerify;
    ProgressBar progressBar;
    String mobile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_activity);

        verifyMobile=findViewById(R.id.verifyMobile);
        editNumber=findViewById(R.id.editNumber);
        buttonVerify=findViewById(R.id.buttonVerify);
        progressBar=findViewById(R.id.progressBar);

        mobile = getIntent().getStringExtra("mobile");
        editNumber.setText(mobile);
        progressBar.setVisibility(View.GONE);

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Query checkUser = FirebaseDatabase.getInstance().getReference("student").orderByChild("phonenumber").equalTo(editNumber.getText().toString());
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            editNumber.setError(null);
                            Intent intent = new Intent(ForgotActivity.this,VerifyOtp.class);
                            intent.putExtra("phonenumber",editNumber.getText().toString());
                            startActivity(intent);
                            finish();
                        }else {
                            editNumber.setError("No Such User Exist");
                            editNumber.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}
