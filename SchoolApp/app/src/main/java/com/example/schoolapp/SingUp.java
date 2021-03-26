package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUp extends AppCompatActivity {

    ImageView imageProfile;
    EditText editTextfullname, editUserId, editEmail, editPassword, editPhone;
    Spinner spinner;
    Button buttonSignUp,buttonContinue;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String spinnerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        imageProfile = findViewById(R.id.imageProfile);
        editTextfullname = findViewById(R.id.editTextfullname);
        editUserId = findViewById(R.id.editUserId);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPhone = findViewById(R.id.editPhone);
        spinner = findViewById(R.id.spinner);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonContinue = findViewById(R.id.buttonContinue);


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("admin");

                // Get All The Values

                String name = editTextfullname.getText().toString();
                String username = editUserId.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String mobilenumber = editPhone.getText().toString();

                UserDataHelper helperClass = new UserDataHelper(name,username,email,password,mobilenumber);

                reference.child(username).setValue(helperClass);
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingUp.this,Login.class);
                startActivity(intent);
            }
        });
       
    }
}
