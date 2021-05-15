package com.gytisdev.bahometask.postdetails

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.gytisdev.bahometask.databinding.ViewPostsBinding
import com.gytisdev.bahometask.posts.data.MainPostsAdapter
import com.gytisdev.bahometask.posts.data.model.Post

interface MainViewDelagate {
    fun onItemSelected(id: String)
    fun onPullToRefresh()
}

class MainView(context: Context) : FrameLayout(context) {

    var delegate: MainViewDelagate? = null

    private var binding: ViewPostsBinding =
        ViewPostsBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var listAdapter: MainPostsAdapter

    init {
        setupView()
    }

    private fun setupView() {
        listAdapter = MainPostsAdapter(emptyList()) {
            delegate?.onItemSelected(it)
        }
        with(binding) {
            postsList.adapter = listAdapter
            postsList.layoutManager = LinearLayoutManager(context)

            swipeRefresh.setOnRefreshListener {
                delegate?.onPullToRefresh()
            }
        }
    }

    fun setListItems(items: List<Post>) {
        listAdapter.updateItems(items)
    }

    fun showLoading() {
        binding.loadingView.visibility = VISIBLE
    }

    fun stopLoading() {
        binding.swipeRefresh.isRefreshing = false
        binding.loadingView.visibility = GONE
    }
}