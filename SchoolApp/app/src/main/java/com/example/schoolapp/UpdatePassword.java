package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolapp.Activity.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatePassword extends AppCompatActivity {
    EditText editCreate,editConfirm;
    Button buttonUpdate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password);

        editCreate=findViewById(R.id.editCreate);
        editConfirm=findViewById(R.id.editConfirm);
        buttonUpdate=findViewById(R.id.buttonUpdate);

        String phonenumber = getIntent().getStringExtra("phonenumber");

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = editConfirm.getText().toString().trim();

                // Update data base
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("student");
                reference.child(phonenumber).child("password").setValue(newPassword);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();


            }
        });




    }
}
