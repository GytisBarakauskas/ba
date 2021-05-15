package com.gytisdev.bahometask.posts

import com.gytisdev.bahometask.application.GlobalRouter
import javax.inject.Inject

class PostsRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) {

    fun openPostDetails(id: String) {
        globalRouter.openDestination(Destinations.toDetailsFragment(id))
    }

    private object Destinations {
        fun toDetailsFragment(postID: String) =
            PostsFragmentDirections.actionMainFragmentToDetailsFragment(postID = postID)
    }
}