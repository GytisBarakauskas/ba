package com.gytisdev.bahometask

import com.gytisdev.bahometask.application.common.ImageUrlResolverHelper
import org.junit.Test

class ImageUrlResolverHelperTest {
    @Test
    fun `url resolver returns correct url`() {
        val userID = 123
        val expectedUrl = "https://source.unsplash.com/collection/542909/?sig=123"

        val result = ImageUrlResolverHelper.getUserAvatarUrl(userID)

        assert(expectedUrl == result)
    }
}