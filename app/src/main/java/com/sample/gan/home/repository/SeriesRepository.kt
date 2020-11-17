package com.sample.gan.home.repository

import com.sample.gan.home.data.network.APIService
import javax.inject.Inject

class SeriesRepository @Inject constructor(private val apiService: APIService) {

    suspend fun getSeriesData() = apiService.getSeriesData()
}