package com.example.schoolapp.Homework;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddHomework extends AppCompatActivity {

    Spinner spinnerClass,spinnerSubject;
    String itemClass,itemSubject;
    TextView textDate;
    EditText editDescription;
    Button buttonHomework;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatePickerDialog.OnDateSetListener setListener;
    private String date;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_homework);

        spinnerClass=findViewById(R.id.spinnerClass);
        spinnerSubject=findViewById(R.id.spinnerSubject);

        textDate=findViewById(R.id.textDate);
        editDescription=findViewById(R.id.editDescription);
        buttonHomework=findViewById(R.id.buttonHomework);

        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);


        List<String> classes = new ArrayList<>();
        classes.add(0,"Select Class");
        classes.add("Nursery");
        classes.add("L.K.G.");
        classes.add("U.K.G");
        classes.add("I");
        classes.add("II");
        classes.add("III");
        classes.add("IV");
        classes.add("V");
        classes.add("VI");
        classes.add("VII");
        classes.add("VIII");
        classes.add("IX");
        classes.add("X");
        classes.add("XI");
        classes.add("XII");

        //style and populate the spinner
        ArrayAdapter<String> classAdapter;
        classAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,classes);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerClass.setAdapter(classAdapter);

        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Class")) {
                    Toast.makeText(AddHomework.this, "Please Select Some Value", Toast.LENGTH_SHORT).show();


                } else {
                    itemClass = parent.getItemAtPosition(position).toString();


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> subject = new ArrayList<>();
        subject.add(0,"Select Subject");
        subject.add("English");;
        subject.add("Hindi");
        subject.add("Maths");
        subject.add("E.V.S.");
        ArrayAdapter<String> subjectAdapter;

        subjectAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,subject);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSubject.setAdapter(subjectAdapter);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Class")) {
                    Toast.makeText(AddHomework.this, "Please Select Some Value", Toast.LENGTH_SHORT).show();


                } else {
                    itemSubject = parent.getItemAtPosition(position).toString();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        // Text Date Coding For Automatic Date Picker

        textDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddHomework.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        date = day +"-" +month +"-" +year;
                        textDate.setText(date);


                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });






        buttonHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Homework");

                String description = editDescription.getText().toString();


                // get all values

                AddHomeworkClass addHomeworkClass = new AddHomeworkClass(itemClass,itemSubject,date,description);
                reference.push().setValue(addHomeworkClass);

            }
        });







    }
}

