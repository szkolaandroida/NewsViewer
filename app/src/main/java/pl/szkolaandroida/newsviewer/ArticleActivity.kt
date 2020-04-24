package pl.szkolaandroida.newsviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_activity)

        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as Article
        title = article.title

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.articleContainer, ArticleFragment.newInstance(article.url))
                .commit()
        }
    }

    companion object {
        const val ARTICLE_EXTRA = "article"
    }
}
