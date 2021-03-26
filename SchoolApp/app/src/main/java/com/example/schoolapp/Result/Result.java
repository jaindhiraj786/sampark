package com.example.schoolapp.Result;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Result extends AppCompatActivity {

    RecyclerView recyclerResult;
    ResultAdapter resultAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        recyclerResult =findViewById(R.id.recyclerResult);
        recyclerResult.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ResultClass> options =
                new FirebaseRecyclerOptions.Builder<ResultClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Result"), ResultClass.class)
                        .build();


        resultAdapter = new ResultAdapter (options);
        recyclerResult.hasFixedSize();
        recyclerResult.setAdapter(resultAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        resultAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        resultAdapter.stopListening();
    }
}
