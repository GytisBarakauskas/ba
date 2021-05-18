package com.gytisdev.bahometask.application

import androidx.navigation.NavDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalRouter @Inject constructor() {

    @ExperimentalCoroutinesApi
    val globalRouterChannel = BroadcastChannel<NavDirections>(capacity = 1)

    @ExperimentalCoroutinesApi
    fun openDestination(direction: NavDirections) = globalRouterChannel.offer(direction)
}