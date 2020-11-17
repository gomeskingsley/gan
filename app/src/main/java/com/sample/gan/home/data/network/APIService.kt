package com.sample.gan.home.data.network

import com.sample.gan.models.response.CharacterItemResponse
import retrofit2.http.GET

interface APIService {
    @GET("characters")
    suspend fun getSeriesData(): List<CharacterItemResponse>
}