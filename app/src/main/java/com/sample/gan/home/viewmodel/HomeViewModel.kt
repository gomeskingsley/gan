package com.sample.gan.home.viewmodel

import androidx.lifecycle.LiveData
import com.sample.gan.models.response.CharacterItemResponse

interface HomeViewModel {
    sealed class Event {
        object NetworkError : Event()
    }

    val event: LiveData<Event>
    val items: LiveData<List<CharacterItemResponse>>

    fun fetchSeriesData()
}