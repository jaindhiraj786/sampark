package com.example.schoolapp.Notice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NotesAdapter extends FirebaseRecyclerAdapter<BroadcastNotice,NotesAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotesAdapter(@NonNull FirebaseRecyclerOptions<BroadcastNotice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull BroadcastNotice model) {
        holder.tab_title.setText(model.getTitle());
        holder.tab_information.setText(model.getInformation());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_detail,parent,false);
       return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

            TextView tab_title,tab_information;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
             tab_title=itemView.findViewById(R.id.tab_title);
             tab_information=itemView.findViewById(R.id.tab_information);

        }
    }

}