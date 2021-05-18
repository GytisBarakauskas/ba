package com.gytisdev.bahometask.application.common

import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

object AnimationHelper {

    inline fun createFadeInAnimation(
        crossinline onAnimationStart: () -> Unit = {},
        crossinline onAnimationEnd: () -> Unit = {},
        duration: Long = 300
    ) = with(AlphaAnimation(0F, 1F)) {
        interpolator = AccelerateInterpolator()
        this.duration = duration
        fillAfter = true
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationStart(animation: Animation?) {
                onAnimationStart.invoke()
            }
        })
        this
    }

    inline fun createFadeOutAnimation(
        crossinline onAnimationStart: () -> Unit = {},
        crossinline onAnimationEnd: () -> Unit = {},
        duration: Long = 300
    ) = with(AlphaAnimation(1F, 0F)) {
        interpolator = AccelerateInterpolator()
        this.duration = duration
        fillAfter = true
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationStart(animation: Animation?) {
                onAnimationStart.invoke()
            }
        })
        this
    }
}