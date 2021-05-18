package com.gytisdev.bahometask.postdetails

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.gytisdev.bahometask.application.common.AnimationHelper.createFadeInAnimation
import com.gytisdev.bahometask.application.common.AnimationHelper.createFadeOutAnimation
import com.gytisdev.bahometask.databinding.ViewPostsBinding
import com.gytisdev.bahometask.posts.data.MainPostsAdapter
import com.gytisdev.bahometask.posts.data.model.Post

interface MainViewDelegate {
    fun onItemSelected(id: Int)
    fun onPullToRefresh()
}

class MainView(context: Context) : FrameLayout(context) {

    var delegate: MainViewDelegate? = null

    private var binding: ViewPostsBinding =
        ViewPostsBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var listAdapter: MainPostsAdapter

    init {
        setupView()
    }

    fun setListItems(items: List<Post>) {
        listAdapter.updateItems(items)
    }

    fun showLoading() {
        binding.loadingView.startAnimation(createFadeInAnimation(onAnimationStart = {
            binding.loadingView.visibility = VISIBLE
        }))
    }

    fun stopLoading() {
        binding.loadingView.startAnimation(createFadeOutAnimation(onAnimationEnd = {
            binding.loadingView.visibility = GONE
        }))
    }

    fun hideSwipeToRefreshLoader() {
        binding.swipeRefresh.isRefreshing = false
    }

    private fun setupView() {
        listAdapter = MainPostsAdapter {
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
}