package com.example.newsapp.model

import javax.inject.Inject

data class NewsResponse

@Inject constructor(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
){
    fun getAes(): List<Article> {
        return articles
    }
}