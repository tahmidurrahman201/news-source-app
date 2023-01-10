package com.example.newssources;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Connector extends RecyclerView.Adapter<Connector.ViewHolder> {

   List<ModelOfNews> list;
   Context context;

    public Connector(List<ModelOfNews> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Connector.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Connector.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ti.setText(list.get(position).getTitle());
        holder.de.setText(list.get(position).getDescription());
        holder.ty.setText("Type\n"+ list.get(position).getType());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,Details.class);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ti,de,ty;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ti=itemView.findViewById(R.id.TITLE);
            de=itemView.findViewById(R.id.DESCRYPTION);
            ty=itemView.findViewById(R.id.TYPE);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
