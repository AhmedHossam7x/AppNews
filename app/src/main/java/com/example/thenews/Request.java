package com.example.thenews;

import android.content.Context;
import android.widget.Toast;

import com.example.thenews.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Request {

    Context context;
    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static String country_change = "us";

    public void getNewsHeadlines(OnFetchData onFetchData, String category, String query){
        CallNews callNews= retrofit.create(CallNews.class);
        Call<NewsApiResponse> call= callNews.callHeadlines(country_change, category, query, context.getString(R.string.api_key));
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "failed load data", Toast.LENGTH_SHORT).show();
                    }
                    onFetchData.onFetch(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    onFetchData.onError("Request failed");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Request(Context context) {
        this.context = context;
    }

    public interface CallNews{
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("api_key") String api_key
        );
    }



}
