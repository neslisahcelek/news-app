package com.example.newsapp.network

import com.example.newsapp.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface NewsAPIService{
    companion object {
        private const val API_KEY = "b5ae5ac7e3784c80aaa04f9803aa7737"
    }

    @GET("everything")
        suspend fun getNews(
            @Query("q") q: String = "Technology",
            @Query("from") from: String = Date().toString(),
            @Query("sortBy") sortBy: String = "popularity",
            @Query("apiKey") apiKey: String = API_KEY
        ): NewsResponse
        
}