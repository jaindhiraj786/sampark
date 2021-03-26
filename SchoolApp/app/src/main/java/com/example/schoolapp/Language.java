package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolapp.Activity.MainActivity;

public class Language extends AppCompatActivity {
    Button buttonInfo,buttonContinue;
    TextView textView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);


        buttonInfo=findViewById(R.id.buttonInfo);
        buttonContinue=findViewById(R.id.buttonContinue);


        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Language.this,PhoneInfo.class);
                startActivity(intent);
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Language.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
