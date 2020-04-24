package pl.szkolaandroida.newsviewer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.article_fragment.*

class ArticleFragment : Fragment(R.layout.article_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.article, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_share -> {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, arguments?.getString(URL_ARG))
                startActivity(Intent.createChooser(share, "Share an article!"))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(URL_ARG)?.let {
            articleWebView.loadUrl(it)
        }
    }

    companion object {
        private const val URL_ARG = "url"

        fun newInstance(url: String) = ArticleFragment().apply {
            arguments = Bundle().apply {
                putString(URL_ARG, url)
            }
        }
    }
}