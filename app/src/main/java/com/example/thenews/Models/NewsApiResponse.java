package com.example.thenews.Models;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {

    String status= "";
    int totalResults;
    List<NewsHeading> articles;

    public String getStatus() { return status; }
    public int getTotalResults() { return totalResults; }
    public List<NewsHeading> getArticles() { return articles; }

    public void setStatus(String status) { this.status = status; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }
    public void setArticles(List<NewsHeading> articles) { this.articles = articles; }

//    String status;
//    int totalResults;
//    List<NewsHeading> articles;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public int getTotalResults() {
//        return totalResults;
//    }
//
//    public void setTotalResults(int totalResults) {
//        this.totalResults = totalResults;
//    }
//
//    public List<NewsHeading> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(List<NewsHeading> articles) {
//        this.articles = articles;
//    }
}
