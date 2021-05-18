package com.gytisdev.bahometask.posts

import com.gytisdev.bahometask.application.GlobalRouter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class PostsRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) {

    @ExperimentalCoroutinesApi
    fun openPostDetails(id: Int) {
        globalRouter.openDestination(Destinations.toDetailsFragment(id))
    }

    private object Destinations {
        fun toDetailsFragment(postID: Int) =
            PostsFragmentDirections.actionMainFragmentToDetailsFragment(postID = postID)
    }
}