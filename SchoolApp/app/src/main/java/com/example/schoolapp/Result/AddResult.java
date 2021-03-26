package com.example.schoolapp.Result;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.schoolapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddResult extends AppCompatActivity implements View.OnClickListener {
    ConstraintLayout layoutList;
    Button buttonAdd;
    List<String> subjectList= new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_result);

        layoutList=findViewById(R.id.layout_list);
        buttonAdd=findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(this);

        subjectList.add("Select Subject");
        subjectList.add("ENGLISH");
        subjectList.add("HINDI");
        subjectList.add("MATHS");
        subjectList.add("E.V.S.");

    }

    @Override
    public void onClick(View v) {
        addView();

    }

    private void addView() {
        View resultView = getLayoutInflater().inflate(R.layout.row_add,null,false);

        AppCompatSpinner spinner = (AppCompatSpinner)resultView.findViewById(R.id.spinnerItem);
        EditText editMax = (EditText)resultView.findViewById(R.id.editMaximum);
        EditText editGet = (EditText)resultView.findViewById(R.id.editGet);
        ImageView imageCancel = (ImageView)resultView.findViewById(R.id.imageCancel);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,subjectList);
        spinner.setAdapter(arrayAdapter);

        imageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeField(resultView);
            }
        });
        layoutList.addView(resultView);

    }
    public void removeField(View view){
        layoutList.removeView(view);

    }
}
