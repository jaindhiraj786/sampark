package com.example.schoolapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView textDeparture,textaddress,textarrival,textFather,textClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        textDeparture=findViewById(R.id.textDeparture);
        textaddress=findViewById(R.id.textAddress);
        textarrival=findViewById(R.id.textarrival);
        textClass=findViewById(R.id.textClass);
        textFather=findViewById(R.id.textFather);

        String mobile = getIntent().getStringExtra("mobile");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("detail");
        Query checkuser =reference.orderByChild("prefered mobile").equalTo(mobile);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String address =snapshot.child(mobile).child("address").getValue(String.class);
                    String arrival =snapshot.child(mobile).child("arrival bus number").getValue(String.class);
                    String class_section =snapshot.child(mobile).child("class section").getValue(String.class);
                    String departure =snapshot.child(mobile).child("departure bus number").getValue(String.class);
                    String father =snapshot.child(mobile).child("father name").getValue(String.class);

                    textaddress.setText(address);
                    textarrival.setText(arrival);
                    textClass.setText(class_section);
                    textDeparture.setText(departure);
                    textFather.setText(father);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
