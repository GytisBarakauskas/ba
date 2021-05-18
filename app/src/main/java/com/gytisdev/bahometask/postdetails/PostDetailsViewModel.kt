package com.gytisdev.bahometask.postdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gytisdev.bahometask.application.common.OutcomeConverter
import com.gytisdev.bahometask.application.common.TaskStatus
import com.gytisdev.bahometask.postdetails.data.model.PostDetails
import com.gytisdev.bahometask.postdetails.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val postDetailsRepository: PostDetailsRepository,
    private val usersRepository: UsersRepository,
    private val converter: OutcomeConverter
) : ViewModel() {

    private val _postDetails = MutableLiveData<TaskStatus<PostDetails?>>()
    val postDetails: LiveData<TaskStatus<PostDetails?>> = _postDetails

    private val _user = MutableLiveData<TaskStatus<User?>>()
    val user: LiveData<TaskStatus<User?>> = _user

    private var postDetailsJob: Job? = null
    private var userJob: Job? = null

    fun getPostDetails(id: Int) {
        postDetailsJob?.cancel()
        postDetailsJob = viewModelScope.launch {
            postDetailsRepository.getPostDetails(id)
                .map {
                    converter.toTaskStatusWithResolvedError(it)
                }
                .collect {
                    _postDetails.value = it
                }
        }
    }

    fun getUser(id: Int) {
        userJob?.cancel()
        userJob = viewModelScope.launch {
            usersRepository.getUser(id)
                .map {
                    converter.toTaskStatusWithResolvedError(it)
                }
                .collect {
                    _user.value = it
                }
        }
    }
}