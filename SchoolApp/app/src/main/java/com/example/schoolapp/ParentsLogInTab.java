package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ParentsLogInTab extends Fragment implements View.OnClickListener {
    EditText email, password;
    TextView textForgot;
    Button buttonLogin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.parents_login_tabs_fragment, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.editpassword);
        textForgot = root.findViewById(R.id.textForgot);
        buttonLogin = root.findViewById(R.id.buttonLogin);



        textForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ForgotActivity.class);
                intent.putExtra("mobile", email.getText().toString());
                startActivityForResult(intent,101);


            }
        });

        buttonLogin.setOnClickListener(this::checkStudent);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        textForgot.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        buttonLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        return root;

    }


    // Here The Code Start

    private Boolean validateUsername() {
        String val = email.getText().toString();

        if (val.isEmpty()) {
            email.setError("Field Cannot Be Empty");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private Boolean validPassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void checkStudent(View view) {
        if (!validateUsername() | !validPassword()) {
            return;


        }
        String mobilenumber = email.getText().toString();
        String passwords = password.getText().toString();

        // data base query

        Query checkuser = FirebaseDatabase.getInstance().getReference("student").orderByChild("phonenumber").equalTo(mobilenumber);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    email.setError(null);

                    String firebasePassword = snapshot.child(mobilenumber).child("password").getValue(String.class);
                    if (firebasePassword.equals(passwords)) {
                        password.setError(null);

                        Toast.makeText(getActivity(), "Welcome Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), Parents_Welcome_Screen.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getActivity(), "Password Does Not Match", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getActivity(), "No Such User Exist !", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onClick(View v) {


    }
}

