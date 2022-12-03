package com.example.werg23u;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    ArrayList<ClassItem> classItems;
    Context context;
     private onitemClicklistener onitemClicklistener;
    public interface onitemClicklistener{
        void onClick(int position);
    }


    public void setOnitemClicklistener(onitemClicklistener onitemClicklistener){
        this.onitemClicklistener=onitemClicklistener;
    }

    public ClassAdapter(Context context, ArrayList<ClassItem> classItems) {
        this.classItems = classItems;
        this.context=context;
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView className,subjectName;



        public ClassViewHolder(@NonNull View itemView ,onitemClicklistener onitemClicklistener) {
            super(itemView);
  className=itemView.findViewById(R.id.class_tv);
  subjectName=itemView.findViewById(R.id.subject_tv);
         itemView.setOnClickListener(v -> onitemClicklistener.onClick(getAdapterPosition()));
        }
    }
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item,parent,false);
        return new ClassViewHolder(itemview,onitemClicklistener);
    }
    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        holder.className.setText(classItems.get(position).getClassName());
        holder.subjectName.setText(classItems.get(position).getSubjectName());
    }
    @Override
    public int getItemCount() {
        return classItems.size();
    }




}
