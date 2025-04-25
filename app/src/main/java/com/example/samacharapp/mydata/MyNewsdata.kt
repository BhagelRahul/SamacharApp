package com.example.samacharapp.mydata

data class MyNewsdata(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)