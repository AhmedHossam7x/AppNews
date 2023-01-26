package com.example.thenews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thenews.Models.NewsHeading;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecycleViewHolder>{

    Context context;
    List<NewsHeading> list;
    Select select;

    public RecyclerAdapter(Context context, List<NewsHeading> list, Select select) {
        this.context = context;
        this.list = list;
        this.select = select;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_source.setText(list.get(position).getSource().getName());
        holder.imageView_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(intent.EXTRA_TEXT, holder.textView_title.getText().toString());
                ((AppCompatActivity)context).startActivity(Intent.createChooser(intent, "share"));
            }
        });
        if(list.get(position).getUrlToImage() != null){
            Picasso.get().load(list.get(position).getUrlToImage()).into(holder.imageView);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.OnNewsClicked(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
