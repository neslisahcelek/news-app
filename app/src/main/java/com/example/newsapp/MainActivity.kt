package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.Source
import com.example.newsapp.ui.theme.Purple40
import com.example.newsapp.ui.theme.PurpleGrey80
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
@Composable
fun MainScreen(
    viewModel: NewsViewModel = viewModel()
) {
    val newsList = viewModel.newsList

    LaunchedEffect(viewModel) {
        viewModel.fetchData()
    }

    NewsList(news = newsList)
}
@Composable
fun NewsListItem(article: Article) {
    Column(modifier = Modifier
        .padding(12.dp)
        .background(color = PurpleGrey80)
        .clip(shape = RoundedCornerShape(14.dp))
        .fillMaxWidth()
        .clickable { }
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Text(text = article.content, style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(top = 8.dp, start = 5.dp, end = 5.dp)
        )
        Text(text = article.author,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, end = 5.dp)
                .align(Alignment.End)
        )
        //Text(text = article.urlToImage)
    }
}
@Composable
fun NewsList(news: List<Article>) {
    LazyColumn {
        items(news) { article ->
            NewsListItem(article)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

