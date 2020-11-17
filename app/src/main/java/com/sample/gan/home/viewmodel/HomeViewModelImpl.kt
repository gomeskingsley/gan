package com.sample.gan.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.gan.common.coroutines.CoroutineDispatchersProvider
import com.sample.gan.home.domain.SeriesUseCase
import com.sample.gan.home.viewmodel.HomeViewModel.Event
import com.sample.gan.models.response.CharacterItemResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModelImpl @Inject constructor(
    private val useCase: SeriesUseCase,
    private val coroutineDispatcher: CoroutineDispatchersProvider
) : ViewModel(), HomeViewModel {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event> = _event

    private val _items = MutableLiveData<List<CharacterItemResponse>>()
    override val items: LiveData<List<CharacterItemResponse>> = _items

    override fun fetchSeriesData() {
        viewModelScope.launch(coroutineDispatcher.io) {
            try {
                val response = useCase()
                withContext(coroutineDispatcher.main) {
                    _items.value = response
                }
            } catch (e: Exception) {
                _event.value = Event.NetworkError
            }
        }
    }
}