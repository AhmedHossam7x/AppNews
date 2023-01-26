package com.example.thenews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thenews.Models.NewsApiResponse;
import com.example.thenews.Models.NewsHeading;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Select, View.OnClickListener{

    RecyclerAdapter recyclerAdapter= null;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    Button btn_business, btn_entertainment, btn_general, btn_health, btn_science, btn_sports, btn_technology;
    SearchView searchView;
    Request manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                dialog.setTitle("Searching... "+query);
                dialog.show();

                manager = new Request(MainActivity.this);
                manager.getNewsHeadlines(check, "general", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog= new ProgressDialog(this);
        dialog.setTitle("Load News");
        dialog.show();

        Request request= new Request(this);
        request.getNewsHeadlines(check, "general", null);

    }

    private void intiView() {
        recyclerView= findViewById(R.id.rv_main);
        searchView= findViewById(R.id.search_view_main_activity);
        btn_business= findViewById(R.id.btn_business_main_activity);
        btn_business.setOnClickListener(this);
        btn_entertainment= findViewById(R.id.btn_entertainment_main_activity);
        btn_entertainment.setOnClickListener(this);
        btn_general= findViewById(R.id.btn_general_main_activity);
        btn_general.setOnClickListener(this);
        btn_health= findViewById(R.id.btn_health_main_activity);
        btn_health.setOnClickListener(this);
        btn_science= findViewById(R.id.btn_science_main_activity);
        btn_science.setOnClickListener(this);
        btn_sports= findViewById(R.id.btn_sports_main_activity);
        btn_sports.setOnClickListener(this);
        btn_technology= findViewById(R.id.btn_technology_main_activity);
        btn_technology.setOnClickListener(this);
    }

    private final OnFetchData<NewsApiResponse> check= new OnFetchData<NewsApiResponse>() {
        @Override
        public void onFetch(List<NewsHeading> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(MainActivity.this,"No Result", Toast.LENGTH_SHORT).show();
            }else {
                showNews(list);
                dialog.dismiss();
            }
        }
        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this,"No Internet",Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeading> list) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerAdapter =new RecyclerAdapter(this, list, this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void OnNewsClicked(NewsHeading newsHeading) {
        startActivity(new Intent(MainActivity.this, Details.class)
                .putExtra("data", newsHeading));
    }

    @Override
    public void onClick(View view) {
        Button button= (Button) view;
        String category= button.getText().toString();
        dialog.setTitle("Update News.... " +category);
        dialog.show();
        Request manager = new Request(this);
        manager.getNewsHeadlines(check, category, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optinal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if(id == R.id.Register){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.Eg){
            Request.country_change="eg";
            dialog.setTitle("Changing News.... ");
            dialog.show();
            Request manager = new Request(this);
            manager.getNewsHeadlines(check, "general", null);
        }
        else if (id == R.id.US){
            Request.country_change="us";
            dialog.setTitle("Changing News.... ");
            dialog.show();
            Request manager = new Request(this);
            manager.getNewsHeadlines(check, "general", null);
        }
        else if (id == R.id.RS){
            Request.country_change="rs";
            dialog.setTitle("Changing News.... ");
            dialog.show();
            Request manager = new Request(this);
            manager.getNewsHeadlines(check, "general", null);
        }
        else if (id == R.id.TR){
            Request.country_change="tr";
            dialog.setTitle("Changing News.... ");
            dialog.show();
            Request manager = new Request(this);
            manager.getNewsHeadlines(check, "general", null);
        }
        else if (id == R.id.IN){
            Request.country_change="in";
            dialog.setTitle("Changing News.... ");
            dialog.show();
            Request manager = new Request(this);
            manager.getNewsHeadlines(check, "general", null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences= getSharedPreferences("sharedCountry", MODE_PRIVATE);
        sharedPreferences.edit().putString("country", Request.country_change);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences= getSharedPreferences("sharedCountry", MODE_PRIVATE);
        Request.country_change=sharedPreferences.getString("country", Request.country_change);
    }
}