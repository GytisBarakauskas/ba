package com.gytisdev.bahometask.application.common.helpers

object ImageUrlResolverHelper {

    private const val baseUrl = "https://source.unsplash.com/collection/542909/?sig=%s"

    fun getUserAvatarUrl(userID: Int): String {
        return baseUrl.format(userID)
    }
}