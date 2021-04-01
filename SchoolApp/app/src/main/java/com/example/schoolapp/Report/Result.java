package com.example.schoolapp.Report;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.schoolapp.R;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {
    LinearLayout layoutList;
    Button buttonAdd;
    List<String> subject = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });

         
         subject.add("English");
         subject.add("Hindi");
         subject.add("Maths");
         subject.add("E.V.S.");
    }



    private void addView() {

        View rowView = getLayoutInflater().inflate(R.layout.report_add,null,false);
        AppCompatSpinner spinner = (AppCompatSpinner)rowView.findViewById(R.id.spinner_item);
        EditText editMax = (EditText)rowView.findViewById(R.id.editMaximum);
        EditText editObtained = (EditText)rowView.findViewById(R.id.editObtained);
        ImageView imageClose = (ImageView)rowView.findViewById(R.id.imageClose);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,subject);
        spinner.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(rowView);
            }
        });

        layoutList.addView(rowView);

    }
    private void removeView(View view){
        layoutList.removeView(view);

    }
}
