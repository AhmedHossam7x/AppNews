package com.example.thenews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewHolder extends RecyclerView.ViewHolder{

    TextView textView_title, textView_source;
    ImageView imageView, imageView_share;
    CardView cardView;

    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_title= itemView.findViewById(R.id.tv_title_item_design);
        textView_source= itemView.findViewById(R.id.tv_source_item_design);
        imageView= itemView.findViewById(R.id.iv_image_item_design);
        cardView= itemView.findViewById(R.id.cardView_item_design);
        imageView_share= itemView.findViewById(R.id.iv_share_head_item);

    }

}
