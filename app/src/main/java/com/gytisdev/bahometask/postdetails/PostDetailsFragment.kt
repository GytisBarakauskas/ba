package com.gytisdev.bahometask.postdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gytisdev.bahometask.R
import com.gytisdev.bahometask.application.base.BaseFragment
import com.gytisdev.bahometask.application.common.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : BaseFragment() {

    private val arguments: PostDetailsFragmentArgs by navArgs()
    private val viewModel: PostDetailsViewModel by viewModels()
    private lateinit var contentView: PostDetailsView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contentView = PostDetailsView(inflater.context)

        viewModel.getPostDetails(arguments.postID)
        observeChanges()

        return contentView
    }

    private fun observeChanges() {
        viewModel.postDetails.observe(viewLifecycleOwner,
            onSuccess = {
                it?.let {
                    viewModel.getUser(it.userID)
                    contentView.setupPostDetails(it)
                }
            },
            onErrorMessage = { message, data ->
                data?.let {
                    contentView.setupPostDetails(it)
                }
                showTryAgainErrorDialog(message)
            }
        )

        viewModel.user.observe(viewLifecycleOwner,
            onSuccess = {
                it?.let {
                    contentView.setupUser(it)
                }
            },
            onErrorMessage = { message, data ->
                data?.let {
                    contentView.setupUser(it)
                }
                showTryAgainErrorDialog(message)
            })
    }

    private fun showTryAgainErrorDialog(message: String) {
        showDialog(
            title = getString(R.string.error),
            message = message,
            cancelButtonText = getString(R.string.dismiss),
            okButtonText = getString(R.string.try_again),
            onOkClick = {
                viewModel.getPostDetails(arguments.postID)
            }
        )
    }
}