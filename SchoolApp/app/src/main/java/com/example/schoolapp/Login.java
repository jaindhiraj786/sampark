package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText editPhone, editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editPhone = findViewById(R.id.editPhone);
        editTextPassword = findViewById(R.id.editTextPassword);


    }

    private Boolean validateUsername() {
        String val = editPhone.getText().toString();

        if (val.isEmpty()) {
            editPhone.setError("Field Cannot Be Empty");
            return false;
        } else {
            editPhone.setError(null);
            return true;
        }
    }

    private Boolean validPassword() {
        String val = editTextPassword.getText().toString();
        if (val.isEmpty()) {
            editTextPassword.setError("Field cannot be empty");
            return false;
        } else {
            editTextPassword.setError(null);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validateUsername() | !validPassword()) {

            return;

        }
        // get the data
        String phoneNumber = editPhone.getText().toString();
        String password = editTextPassword.getText().toString();

        // data base query
        Query checkUser = FirebaseDatabase.getInstance().getReference("admin").orderByChild("username").equalTo(phoneNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    editPhone.setError(null);

                    String firebasepassword = snapshot.child(phoneNumber).child("password").getValue(String.class);
                    if (firebasepassword.equals(password)) {
                        editTextPassword.setError(null);

                        Toast.makeText(Login.this, "Welcome Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Parents_Welcome_Screen.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(Login.this, "Password Does Not Match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "No Such User Exist !", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, "Database Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}


