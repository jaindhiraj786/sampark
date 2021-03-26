package com.example.schoolapp.Homework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HomeworkAdapter extends FirebaseRecyclerAdapter<Work,HomeworkAdapter.myViewHolder>  {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HomeworkAdapter(@NonNull FirebaseRecyclerOptions<Work> options) {
       super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Work model) {
        holder.tab_subject.setText(model.getSubject());
        holder.tab_date.setText(model.getDate());
        holder.tab_detail.setText(model.getDescription());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homemenu,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TableLayout tableLayoutHomework;
        TextView tab_subject,tab_date,tab_detail;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tableLayoutHomework = itemView.findViewById(R.id.tableLayoutHomework);
            tab_subject=itemView.findViewById(R.id.tab_subject);
            tab_date=itemView.findViewById(R.id.tab_date);
            tab_detail=itemView.findViewById(R.id.tab_detail);
        }
    }
}
