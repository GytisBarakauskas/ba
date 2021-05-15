package com.gytisdev.bahometask.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gytisdev.bahometask.application.common.ErrorHelper
import com.gytisdev.bahometask.application.common.LiveEvent
import com.gytisdev.bahometask.application.common.TaskStatus
import com.gytisdev.bahometask.application.common.launch
import com.gytisdev.bahometask.posts.data.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val errorHelper: ErrorHelper
) : ViewModel() {

    val loadPostsTask = LiveEvent<TaskStatus<List<Post>>>()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun initialize() {
        refreshData()
    }

    fun refreshData() {
        loadPostsTask.value = TaskStatus.loading(true)
        viewModelScope.launch(work = {
            postsRepository.getPosts()
        }, onSuccess = {
            loadPostsTask.value = TaskStatus.success()
            _posts.value = it
        }, onError = {
            loadPostsTask.value = TaskStatus.failure(errorHelper.getErrorMessage(it))
        })
    }
}