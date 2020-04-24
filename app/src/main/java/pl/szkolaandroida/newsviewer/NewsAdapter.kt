package pl.szkolaandroida.newsviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_item.view.*

class NewsAdapter(private val onItemClick: (Article) -> Unit) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    private val articles = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_item,
                parent,
                false
            ),
            onItemClick
        )
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun setArticles(items: List<Article>) {
        articles.addAll(items)
        notifyDataSetChanged()
    }
}

class ArticleViewHolder(
    override val containerView: View,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(article: Article) {
        containerView.setOnClickListener {
            onItemClick(article)
        }
        containerView.articleTitle.text = article.title
        containerView.articleImage.load(article.urlToImage)
    }
}