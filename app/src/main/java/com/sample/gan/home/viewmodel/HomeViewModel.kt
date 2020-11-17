package com.sample.gan.home.viewmodel

import androidx.lifecycle.LiveData
import com.sample.gan.home.viewmodel.itemviewmodel.ItemClickCallback
import com.sample.gan.models.response.CharacterItem

interface HomeViewModel : ItemClickCallback {
    sealed class Event {
        object NetworkError : Event()
    }

    val event: LiveData<Event>
    val items: LiveData<List<CharacterItem>>

    fun fetchSeriesData()
}