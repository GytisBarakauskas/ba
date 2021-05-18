package com.gytisdev.bahometask.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gytisdev.bahometask.application.common.OutcomeConverter
import com.gytisdev.bahometask.application.common.TaskStatus
import com.gytisdev.bahometask.posts.data.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val converter: OutcomeConverter,
    private val postsRouter: PostsRouter
) : ViewModel() {

    private val _posts = MutableLiveData<TaskStatus<List<Post>>>()
    val posts: LiveData<TaskStatus<List<Post>>> = _posts

    private var postsJob: Job? = null

    fun fetchPosts(forceRefresh: Boolean = false) {
        postsJob?.cancel()
        postsJob = viewModelScope.launch {
            postsRepository
                .getPosts(forceRefresh)
                .map { converter.toTaskStatusWithResolvedError(it) }
                .collect {
                    _posts.value = it
                }
        }
    }

    fun onItemSelected(id: Int) {
        postsRouter.openPostDetails(id)
    }
}