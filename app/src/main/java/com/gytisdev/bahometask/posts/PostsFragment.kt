package com.gytisdev.bahometask.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gytisdev.bahometask.application.base.BaseFragment
import com.gytisdev.bahometask.postdetails.MainView
import com.gytisdev.bahometask.postdetails.MainViewDelagate
import com.gytisdev.bahometask.application.common.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment: BaseFragment(), MainViewDelagate {

    @Inject
    lateinit var postsRouter: PostsRouter

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
        if(savedInstanceState == null) {
            viewModel.initialize()
        }

        return contentView
    }

    private fun observeChanges() {
        viewModel.loadPostsTask.observe(viewLifecycleOwner,
            onLoading = {
                contentView.showLoading()
            },
            onSuccess = {
                contentView.stopLoading()
            },
            onErrorMessage = {
                contentView.stopLoading()
                showDialog(
                    title = "Error",
                    message = it,
                    cancelButtonText = "Cancel",
                    okButtonText = "Try again",
                    onOkClick = {
                        viewModel.refreshData()
                    }
                )
            }
        )

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            contentView.setListItems(posts)
        }
    }

    // MainViewDelegate

    override fun onItemSelected(id: String) {
        postsRouter.openPostDetails(id)
    }

    override fun onPullToRefresh() {
        viewModel.refreshData()
    }
}