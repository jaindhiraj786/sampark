package com.example.schoolapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.widget.TextView;


public class PhoneInfo extends AppCompatActivity {
    TextView textInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_info);

        textInfo=findViewById(R.id.textInfo);


        String info = textInfo.getText().toString();
        textInfo.setText("Info:-" +Build.SERIAL+"\n" +Build.MODEL+"\n" +Build.ID+"\n" +Build.MANUFACTURER+"\n" +Build.BRAND+"\n" +Build.TYPE+"\n" +Build.USER+"\n" +Build.VERSION_CODES.BASE+"\n" +Build.VERSION.INCREMENTAL+"\n" +Build.BOARD +"\n" +Build.HOST+"\n" +Build.FINGERPRINT+"\n" +Build.VERSION.RELEASE+"\n"+Build.VERSION.SDK);



    }
}
