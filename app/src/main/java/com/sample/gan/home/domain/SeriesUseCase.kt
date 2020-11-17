package com.sample.gan.home.domain

import com.sample.gan.home.repository.SeriesRepository
import javax.inject.Inject

class SeriesUseCase @Inject constructor(private val repository: SeriesRepository) {

    suspend operator fun invoke() = repository.getSeriesData()
}