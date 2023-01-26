package com.example.thenews;

import com.example.thenews.Models.NewsHeading;

import java.util.List;

public interface OnFetchData<NewsApiResponse> {
    void onFetch(List<NewsHeading> list, String message);
    void onError(String message);
}
