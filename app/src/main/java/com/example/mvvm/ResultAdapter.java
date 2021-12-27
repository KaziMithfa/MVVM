package com.example.mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder>  {

    private List<ClassTest>notes = new ArrayList<>();

    private onItemClickListner listner;

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test,parent,false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        ClassTest testresult = notes.get(position);
        holder.nametxt.setText(testresult.getName());
        holder.rollnumbertxt.setText(String.valueOf(testresult.getRoll()));
        holder.resulttxt.setText(String.valueOf(testresult.getNumber()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<ClassTest>notes)
    {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private TextView nametxt, rollnumbertxt, resulttxt;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            nametxt =itemView.findViewById(R.id.name);
            rollnumbertxt =itemView.findViewById(R.id.roll_nmbr);
            resulttxt =itemView.findViewById(R.id.result);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listner != null && position  != RecyclerView.NO_POSITION)
                    listner.onItemClick(notes.get(position));
                }
            });
        }
    }

    public interface onItemClickListner
    {
        void onItemClick(ClassTest classTest);
    }

    public void setOnItemClickListner(onItemClickListner listner)
    {
        this.listner = listner;

    }


    public ClassTest getNoteAt(int position)
    {
        return notes.get(position);

    }


}
