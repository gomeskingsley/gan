package com.sample.gan.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.gan.common.coroutines.CoroutineDispatchersProvider
import com.sample.gan.home.domain.SeriesUseCase
import com.sample.gan.home.viewmodel.HomeViewModel.Event
import com.sample.gan.models.response.CharacterItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class HomeViewModelImplTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val mockUseCase = mockk<SeriesUseCase>()
    private val mockCoroutineDispatcher = mockk<CoroutineDispatchersProvider>(relaxed = true)

    private lateinit var subject: HomeViewModelImpl

    private fun setUpSubject() {
        every { mockCoroutineDispatcher.main } returns Dispatchers.Unconfined
        subject = HomeViewModelImpl(mockUseCase, mockCoroutineDispatcher)
    }

    @Test
    fun `GIVEN successful api response WHEN init is called THEN items are emitted`() {
        val items = listOf<CharacterItem>(mockk())
        coEvery { mockUseCase() } returns items

        setUpSubject()

        assertThat(subject.items.value).isEqualTo(items)
    }

    @Test
    fun `GIVEN un-successful api response WHEN init is called THEN NetworkError event is sent`() {
        coEvery { mockUseCase() } throws Exception("")

        setUpSubject()

        assertThat(subject.event.value).isEqualTo(Event.NetworkError)
    }

    @Test
    fun `WHEN onItemClicked on is called THEN NavigateToDetails event is sent`() {
        val mockCharacterItem = mockk<CharacterItem>()
        setUpSubject()

        subject.onItemClicked(mockCharacterItem)

        assertThat(subject.event.value).isEqualTo(Event.NavigateToDetails(mockCharacterItem))
    }
}