package com.example.schoolapp.Notice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNotice extends AppCompatActivity {
    EditText edit_title,edit_information;
    Button buttonNotice;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notice);
        edit_title=findViewById(R.id.edit_title);
        edit_information=findViewById(R.id.edir_information);
        buttonNotice=findViewById(R.id.buttonNotice);

        buttonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = edit_title.getText().toString();
                String info = edit_information.getText().toString();
                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Notice");

                AddNoticeClass addNoticeClass = new AddNoticeClass(title,info);
                reference.push().setValue(addNoticeClass);
            }
        });

    }
}
