package com.sample.gan.home.repository

import com.sample.gan.home.data.network.APIService
import javax.inject.Inject

class SeriesRepository @Inject constructor(
    private val apiService: APIService,
    private val mapper: SeriesMapper
) {

    suspend fun getSeriesData() = mapper.from(apiService.getSeriesData())
}