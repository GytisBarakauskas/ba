package com.gytisdev.bahometask.postdetails

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.gytisdev.bahometask.databinding.ViewPostDetailsBinding

class PostDetailsView(context: Context) : FrameLayout(context) {

    private val binding =
        ViewPostDetailsBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupView(post: String) {

    }
}