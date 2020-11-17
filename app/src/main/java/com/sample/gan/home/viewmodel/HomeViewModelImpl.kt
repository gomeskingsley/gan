package com.sample.gan.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.gan.common.coroutines.CoroutineDispatchersProvider
import com.sample.gan.home.domain.SeriesUseCase
import com.sample.gan.home.viewmodel.HomeViewModel.Event
import com.sample.gan.models.response.CharacterItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModelImpl @Inject constructor(
    private val useCase: SeriesUseCase,
    private val coroutineDispatcher: CoroutineDispatchersProvider
) : ViewModel(), HomeViewModel {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event> = _event

    private val _items = MutableLiveData<List<CharacterItem>>()
    override val items: LiveData<List<CharacterItem>> = _items

    init {
        fetchSeriesData()
    }

    override fun fetchSeriesData() {
        viewModelScope.launch(coroutineDispatcher.main) {
            try {
                val response = useCase()
                _items.value = response
            } catch (e: Exception) {
                _event.value = Event.NetworkError
            }
        }
    }

    override fun onItemClicked(item: CharacterItem) {
        _event.value = (Event.NavigateToDetails(item))
    }
}