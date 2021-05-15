package com.gytisdev.bahometask.application.di

import com.gytisdev.bahometask.network.PostsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Reusable
    @Provides
    fun provideApiService() : PostsApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }
                    .asConverterFactory(MediaType.parse("application/json")!!)
            )
            .build()

        return retrofit.create(PostsApiService::class.java)
    }
}