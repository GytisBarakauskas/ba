package com.gytisdev.bahometask.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gytisdev.bahometask.R
import com.gytisdev.bahometask.application.base.BaseFragment
import com.gytisdev.bahometask.application.common.observe
import com.gytisdev.bahometask.postdetails.MainView
import com.gytisdev.bahometask.postdetails.MainViewDelegate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment : BaseFragment(), MainViewDelegate {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var contentView: MainView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contentView = MainView(inflater.context).also {
            it.delegate = this
        }

        observeChanges()
        viewModel.fetchPosts()

        return contentView
    }

    private fun observeChanges() {
        viewModel.posts.observe(viewLifecycleOwner,
            onLoading = {
                contentView.showLoading()
                contentView.setListItems(it ?: emptyList())
            },
            onSuccess = {
                contentView.stopLoading()
                contentView.setListItems(it)
            },
            onErrorMessage = { message, staleData ->
                staleData?.let {
                    contentView.setListItems(staleData)
                }
                contentView.stopLoading()
                showDialog(
                    title = getString(R.string.error),
                    message = message,
                    cancelButtonText = getString(R.string.dismiss),
                    okButtonText = getString(R.string.try_again),
                    onOkClick = {
                        viewModel.fetchPosts(true)
                    }
                )
            }
        )
    }

    // MainViewDelegate

    override fun onItemSelected(id: Int) {
        viewModel.onItemSelected(id)
    }

    override fun onPullToRefresh() {
        viewModel.fetchPosts(forceRefresh = true)
        contentView.hideSwipeToRefreshLoader()
    }
}