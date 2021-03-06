package com.gytisdev.bahometask.postdetails

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.shimmer.ShimmerFrameLayout
import com.gytisdev.bahometask.application.common.helpers.ImageUrlResolverHelper
import com.gytisdev.bahometask.databinding.ViewPostDetailsBinding
import com.gytisdev.bahometask.postdetails.data.model.PostDetails
import com.gytisdev.bahometask.postdetails.data.model.User

class PostDetailsView(context: Context) : FrameLayout(context) {

    private val binding =
        ViewPostDetailsBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setupView()
    }

    fun setupPostDetails(postDetails: PostDetails) {
        with(binding) {
            enableShimmer(binding.postPlaceholder, false)

            postTitle.text = postDetails.title
            postBody.text = postDetails.body
        }
    }

    fun setupUser(user: User) {
        with(binding) {
            enableShimmer(binding.usernamePlaceholder, false)

            userName.text = user.name
            Glide
                .with(this@PostDetailsView)
                .load(ImageUrlResolverHelper.getUserAvatarUrl(user.id))
                .diskCacheStrategy(DiskCacheStrategy.NONE) // seems user image changes on every request, so do not cache them
                .placeholder(createPlaceholderDrawable())
                .centerCrop()
                .into(userAvatar)
        }
    }

    private fun setupView() {
        enableShimmer(binding.usernamePlaceholder, true)
        enableShimmer(binding.postPlaceholder, true)
    }

    private fun enableShimmer(
        shimmerLayout: ShimmerFrameLayout,
        enable: Boolean
    ) {
        if (enable) {
            shimmerLayout.startShimmer()
            shimmerLayout.visibility = VISIBLE
        } else {
            shimmerLayout.stopShimmer()
            shimmerLayout.visibility = GONE
        }
    }

    private fun createPlaceholderDrawable(): CircularProgressDrawable {
        return CircularProgressDrawable(this.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
    }
}