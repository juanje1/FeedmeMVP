package com.juanje1.feedmemvp.ui.post

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanje1.feedmemvp.R
import com.juanje1.feedmemvp.base.BaseActivity
import com.juanje1.feedmemvp.databinding.ActivityPostBinding
import com.juanje1.feedmemvp.model.Post

/**
 * Activity displaying the list of posts
 */
class PostActivity : BaseActivity<PostPresenter>(), PostView {
    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityPostBinding

    /**
     * The adapter for the list of posts
     */
    private val postsAdapter = PostAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updatePosts(posts: List<Post>) {
        postsAdapter.updatePosts(posts)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this)
    }
}