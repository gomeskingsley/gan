package com.sample.gan.home.viewmodel.itemviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.sample.gan.databinding.ItemCharacterBinding
import com.sample.gan.models.response.CharacterItem

class CharacterItemViewHolder(
    private val binding: ItemCharacterBinding,
    private val callback: ItemClickCallback?
) : RecyclerView.ViewHolder(binding.root) {

    private val _item = MutableLiveData<CharacterItem>()
    val item: LiveData<CharacterItem> = _item

    fun bind(item: CharacterItem) {
        binding.viewModel = this
        _item.value = item
        binding.executePendingBindings()
    }

    fun onItemClick() {
        item.value?.let {
            callback?.onItemClicked(it)
        }
    }
}

interface ItemClickCallback {
    fun onItemClicked(item: CharacterItem)
}