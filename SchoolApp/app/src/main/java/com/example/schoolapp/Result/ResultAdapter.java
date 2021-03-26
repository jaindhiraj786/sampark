package com.example.schoolapp.Result;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ResultAdapter extends FirebaseRecyclerAdapter<ResultClass,ResultAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ResultAdapter(@NonNull FirebaseRecyclerOptions<ResultClass> options) {
        super(options);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_design,parent,false);
        return new myViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ResultClass model) {
        holder.textSubject.setText(model.getSubject());
        holder.textmaximun.setText(model.getMaximummarks());
        holder.textmarks.setText(model.getMarks());

    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView textSubject,textmaximun,textmarks;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textSubject=itemView.findViewById(R.id.textSubject);
            textmaximun=itemView.findViewById(R.id.textMaximum);
            textmarks=itemView.findViewById(R.id.textmarks);

        }
    }




}