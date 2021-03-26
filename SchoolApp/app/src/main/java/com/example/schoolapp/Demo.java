package com.example.schoolapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Demo extends AppCompatActivity {

    EditText editName, editEmail, editPassword, editMobile;
    Button buttonAdd;
    Spinner spinner;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String itemClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

    }
}
