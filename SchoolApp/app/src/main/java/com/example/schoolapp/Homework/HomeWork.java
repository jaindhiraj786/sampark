package com.example.schoolapp.Homework;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeWork extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<HomeworkMenu> arrayHome = new ArrayList<HomeworkMenu>();
    HomeworkAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework);

        HomeworkMenu h1 = new HomeworkMenu("English","16-03-2021","Learn Question Answer of Lesson 2");
        arrayHome.add(h1);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


            FirebaseRecyclerOptions<Work> options =
                    new FirebaseRecyclerOptions.Builder<Work>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Homework"),Work.class)
                            .build();


            adapter = new HomeworkAdapter(options);
            recyclerView.hasFixedSize();
            recyclerView.setAdapter(adapter);

            int count = adapter.getItemCount();
            Log.e("Count",count+"");



    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}



