package com.sample.gan.home.repository

import com.sample.gan.home.data.network.APIService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SeriesRepositoryTest {

    private val mockApiService = mockk<APIService>()
    private val mockMapper = mockk<SeriesMapper>(relaxed = true)

    private val subject = SeriesRepository(mockApiService, mockMapper)

    @Test
    fun `WHEN getSeriesData is called THEN api service data is mapped`() {
        coEvery { mockApiService.getSeriesData() } returns emptyList()

        runBlocking {
            subject.getSeriesData()
        }

        coVerify(exactly = 1) {
            mockApiService.getSeriesData()
            mockMapper.from(emptyList())
        }
    }
}