package pl.szkolaandroida.newsviewer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable

interface NewsApi {
    //https://newsapi.org/v2/everything?q=android&sortBy=publishedAt&apiKey=APIKEY
    @GET("/v2/everything")
    fun getNews(
        @Query("q") topic: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "APIKEY"
    ): Call<NewsResponse>
}

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable

data class Source(
    val id: Any,
    val name: String
) : Serializable