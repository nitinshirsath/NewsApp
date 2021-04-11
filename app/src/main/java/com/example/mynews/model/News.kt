package com.example.mynews.model

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)

data class Articles(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val publishedAt: String,
    val urlToImage: String
)