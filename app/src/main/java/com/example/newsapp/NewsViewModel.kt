package com.example.newsapp

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.network.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository): ViewModel() {

    private val _newsList = mutableStateListOf<Article>()
    val newsList: List<Article> get() = _newsList

    fun fetchData() = viewModelScope.launch {
        val response = newsRepository.getNews()
        _newsList.addAll(response.articles)
        Log.d("NewsViewModel", "fetchData: ${response}")
    }

}