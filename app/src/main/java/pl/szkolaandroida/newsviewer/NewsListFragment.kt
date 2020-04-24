package pl.szkolaandroida.newsviewer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.news_list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsListFragment : Fragment(R.layout.news_list_fragment) {

    private var showArticleListener: ShowArticleListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsAdapter {
            showArticleListener?.showArticle(it)
        }

        newsListRecyclerView.adapter = adapter
        newsListRecyclerView.layoutManager = LinearLayoutManager(context)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val newsApi = retrofit.create(NewsApi::class.java)
        val call = newsApi.getNews(arguments?.getString(TOPIC_ARG) ?: "")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("TAG", t.message)
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("TAG", response.body().toString())
                response.body()?.let {
                    adapter.setArticles(it.articles)
                }

            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showArticleListener = context as ShowArticleListener
    }

    override fun onDetach() {
        super.onDetach()
        showArticleListener = null
    }

    interface ShowArticleListener {

        fun showArticle(article: Article)
    }

    companion object {

        private const val TOPIC_ARG = "topic"

        fun newInstance(topic: String) = NewsListFragment().apply {
            arguments = Bundle().apply {
                putString(TOPIC_ARG, topic)
            }
        }
    }
}