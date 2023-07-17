package com.example.newsapp.model

import javax.inject.Inject

data class Source @Inject constructor(
    val id: String?,
    val name: String
)