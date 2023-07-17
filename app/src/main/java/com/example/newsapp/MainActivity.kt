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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.Source
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

    LaunchedEffect(true) {
        viewModel.fetchData()
    }

    NewsList(news = newsList)
}
@Composable
fun NewsListItem(article: Article) {
    Column(modifier = Modifier
        .padding(12.dp)
        .background(CustomColors.lighterCustomColor)
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

object CustomColors {
    val myCustomColor = Color(0xFF123456)
    val lighterCustomColor = myCustomColor.copy(alpha = 0.8f)

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
fun createMockNewsResponse(): NewsResponse {
    return NewsResponse(
        status = "ok",
        totalResults = 300,
        articles = createMockArticles()
    )
}

fun createMockArticles(): List<Article> {
    return listOf(
        Article(
            source = Source(null, "The Guardian"),
            author = "Samuel Gibbs Consumer technology editor",
            title = "Amazon Fire Max 11 review: nice-looking tablet but poor software",
            description = "Biggest and most premium Amazon slate yet is let down badly by Fire OS and lack of key appsThe Fire Max 11 is Amazon’s first premium tablet and is designed to look and feel more like an iPad at half the cost. But while the appearance of the new machine is a s…",
            url = "https://www.theguardian.com/technology/2023/jul/17/amazon-fire-max-11-review-nice-looking-tablet-but-poor-software",
            urlToImage = "https://i.guim.co.uk/img/media/fe8765e9367851322e26b0f4189496213260cbb8/731_506_4641_2784/master/4641.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctcmV2aWV3LTMucG5n&enable=upscale&s=d6ba2bc933d8b6ef138a2af2215658b9",
            publishedAt = "2023-07-17T06:00:21Z",
            content = "The Fire Max 11 is Amazon's first premium tablet and is designed to look and feel more like an iPad at half the cost. But while the appearance of the new machine is a step up, it falls far short of ex… [+7179 chars]"
        ),
        Article(
            source = Source(null, "The Guardian"),
            author = "Hannah Marriott",
            title = "‘We used to check every day, now it’s every minute’: how we got addicted to weather apps",
            description = "As unprecedented weather leads to increasing climate anxiety, there’s a raft of different apps catering for every kind of forecastOne day in 2020, close to the beginning of the Covid-19 pandemic, Matt Rickett realized he was checking weather apps all the time…",
            url = "https://www.theguardian.com/us-news/2023/jul/17/weather-apps-addiction-climate-crisis-anxiety",
            urlToImage = "https://i.guim.co.uk/img/media/9cd9aeb5414215b63ae40905e036c32591de4ea4/248_1026_3925_2355/master/3925.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctZGVmYXVsdC5wbmc&enable=upscale&s=f1b7d9bc4e140b929e307c391f1f7070",
            publishedAt = "2023-07-17T05:00:19Z",
            content = "One day in 2020, close to the beginning of the Covid-19 pandemic, Matt Rickett realized he was checking weather apps all the time. He immediately understood why: Everything felt so unpredictable, so … [+10279 chars]"
        )
    )
}