package com.example.mynews.retrofit

import com.example.mynews.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("v2/everything")
    fun getAppleNewsList(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Call<News>

    @GET("v2/everything")
    fun getTeslaList(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Call<News>

    @GET("v2/top-headlines")
    fun getBusinessHeadlineUsaNewsList(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<News>

    @GET("v2/top-headlines")
    fun getTechCrunchList(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): Call<News>

    @GET("v2/everything")
    fun getWallStreetJournalList(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String
    ): Call<News>
}