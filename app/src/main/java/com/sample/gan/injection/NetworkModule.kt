package com.sample.gan.injection

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create

private const val API_BASE_URL = "https://breakingbadapi.com/api/"

@Module
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(create(GsonBuilder().create()))
            .client(OkHttpClient.Builder().build())
            .build()
    }
}