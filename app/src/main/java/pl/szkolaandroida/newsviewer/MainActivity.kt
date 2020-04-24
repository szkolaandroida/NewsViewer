package pl.szkolaandroida.newsviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity(), NewsListFragment.ShowArticleListener,
    ChangeTopicDialogFragment.ChangeTopicListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showNewsForTopic("android")
        }
    }

    private fun showNewsForTopic(topic: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, NewsListFragment.newInstance(topic))
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.news, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_topic) {
            val dialogFragment = ChangeTopicDialogFragment()
            dialogFragment.show(supportFragmentManager, DIALOG_TAG)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showArticle(article: Article) {
        if (findViewById<View>(R.id.articleContainer) == null) {
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra(ArticleActivity.ARTICLE_EXTRA, article)
            startActivity(intent)
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.articleContainer, ArticleFragment.newInstance(article.url))
                .commit()
        }
    }

    companion object {
        private const val DIALOG_TAG = "dialog_tag"
    }

    override fun changeTopic(topic: String) {
        showNewsForTopic(topic)
    }
}
