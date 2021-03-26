package com.example.schoolapp.Notice;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Notes extends AppCompatActivity {
    RecyclerView recyclerViewNotes;
    NotesAdapter notesAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        recyclerViewNotes=findViewById(R.id.recyclerViewNotes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<BroadcastNotice> options =
                new FirebaseRecyclerOptions.Builder<BroadcastNotice>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice"), BroadcastNotice.class)
                        .build();


        notesAdapter = new NotesAdapter(options);
        recyclerViewNotes.hasFixedSize();
        recyclerViewNotes.setAdapter(notesAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        notesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notesAdapter.stopListening();
    }

}
