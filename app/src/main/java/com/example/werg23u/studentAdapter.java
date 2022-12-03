package com.example.werg23u;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.studentViewHolder> {

    ArrayList<studentitem> studentItems;
    Context context;
     private onitemClicklistener onitemClicklistener;
     public interface onitemClicklistener{
        void onClick(int position);
    }


    public void setOnitemClicklistener(onitemClicklistener onitemClicklistener){

         this.onitemClicklistener=onitemClicklistener;
    }

    public studentAdapter(Context context, ArrayList<studentitem> studentitems) {
        this.studentItems = studentitems;
        this.context=context;
    }

    public static class studentViewHolder extends RecyclerView.ViewHolder {
        TextView roll,name;
          TextView statues;
        CardView cardView;

        public studentViewHolder(@NonNull View itemView , onitemClicklistener onitemClicklistener) {
            super(itemView);
             roll=itemView.findViewById(R.id.roll);
             name=itemView.findViewById(R.id.name);
             statues=itemView.findViewById(R.id.status);
             itemView.setOnClickListener(v -> onitemClicklistener.onClick(getAdapterPosition()));
           cardView =itemView.findViewById(R.id.cardview);


        }
    }
    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
             return new studentViewHolder(itemview,onitemClicklistener);
    }
    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {
        holder.roll.setText(studentItems.get(position).getRoll());
        holder.name.setText(studentItems.get(position).getName());
        holder.statues.setText(studentItems.get(position).getStatus());
        holder.cardView.setCardBackgroundColor(getColor(position));

     }

    private int getColor(int position) {
         String status= studentItems.get(position).getStatus();
        if(status.equals("P"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.present)));
else if (status.equals("A"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.absent)));

        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context, R.color.white)));

    }


    @Override
    public int getItemCount() {
        return studentItems.size();
    }




}
