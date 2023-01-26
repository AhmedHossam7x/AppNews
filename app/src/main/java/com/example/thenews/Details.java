package com.example.thenews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thenews.Models.NewsHeading;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    NewsHeading newsHeadLine;
    TextView textView_title, textView_time, textView_author, textView_details, textView_contact;
    ImageView imageView_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Define();

        newsHeadLine= (NewsHeading) getIntent().getSerializableExtra("data");

        textView_title.setText(newsHeadLine.getTitle());
        textView_author.setText(newsHeadLine.getAuthor());
        textView_time.setText(newsHeadLine.getPublishedAt());
        textView_details.setText(newsHeadLine.getDescription());
        textView_contact.setText(newsHeadLine.getContent());

        Picasso.get().load(newsHeadLine.getUrlToImage()).into(imageView_details);

    }
    private void Define() {
        textView_title= findViewById(R.id.tv_title_details_activity);
        textView_author= findViewById(R.id.tv_text_author_details_activity);
        textView_details= findViewById(R.id.tv_details_details_activity);
        textView_time= findViewById(R.id.tv_text_time_details_activity);
        textView_contact= findViewById(R.id.tv_content_details_activity);
        imageView_details= findViewById(R.id.iv_img_details_activity);
    }
}