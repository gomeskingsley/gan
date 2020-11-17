package com.sample.gan.home.domain

import com.sample.gan.home.repository.SeriesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SeriesUseCaseTest {

    private val mockRepository = mockk<SeriesRepository>(relaxed = true)
    private val subject = SeriesUseCase(mockRepository)

    @Test
    fun `WHEN invoke is called THEN getSeriesData is called on the repository`() {
        runBlocking {
            subject.invoke()
        }

        coVerify(exactly = 1) {
            mockRepository.getSeriesData()
        }
    }
}