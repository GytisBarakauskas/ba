package com.gytisdev.bahometask.postdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gytisdev.bahometask.application.base.BaseFragment

class PostDetailsFragment: BaseFragment() {

    private val arguments: PostDetailsFragmentArgs by navArgs()

    private lateinit var contentView: PostDetailsView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contentView = PostDetailsView(inflater.context)
        return contentView
    }
}