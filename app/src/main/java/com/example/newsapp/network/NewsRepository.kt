package com.example.newsapp.network

import com.example.newsapp.model.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsAPIService
) {
    suspend fun getNews() : NewsResponse{
        return newsService.getNews()
    }
}