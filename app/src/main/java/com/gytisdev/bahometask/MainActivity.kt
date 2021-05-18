package com.gytisdev.bahometask

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gytisdev.bahometask.application.GlobalRouter
import com.gytisdev.bahometask.application.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override lateinit var navigationController: NavController

    @Inject
    lateinit var globalRouter: GlobalRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController = (
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            )
            .navController
        startListeningForRouteRequests()
    }

    private fun startListeningForRouteRequests() = launch {
        globalRouter.globalRouterChannel.asFlow().flowOn(Dispatchers.Main).collect {
            openDirection(it)
        }
    }
}