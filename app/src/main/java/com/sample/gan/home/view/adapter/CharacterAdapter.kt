package com.sample.gan.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sample.gan.databinding.ItemCharacterBinding
import com.sample.gan.home.viewmodel.itemviewmodel.CharacterItemViewHolder
import com.sample.gan.home.viewmodel.itemviewmodel.ItemClickCallback
import com.sample.gan.models.response.CharacterItem
import java.util.*
import javax.inject.Inject

class CharacterAdapter @Inject constructor(private val callback: ItemClickCallback) :
    RecyclerView.Adapter<CharacterItemViewHolder>(), Filterable {

    private var items: List<CharacterItem> = arrayListOf()
    private var filteredItems: List<CharacterItem> = arrayListOf()

    fun setItems(newsList: List<CharacterItem>) {
        this.items = newsList.toList()
        filteredItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterItemViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bind(filteredItems[position])
    }

    override fun getItemCount() = filteredItems.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                filteredItems = if (constraint == null || constraint.isEmpty()) {
                    items
                } else {
                    items.filter {
                        it.name.toLowerCase(Locale.getDefault())
                            .contains(constraint.toString().toLowerCase(Locale.getDefault()))
                    }.sortedByDescending { it.appearance.size }
                }

                val results = FilterResults()
                results.values = filteredItems
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    filteredItems = if (it.values == null) {
                        arrayListOf()
                    } else {
                        it.values as List<CharacterItem>
                    }
                }
                notifyDataSetChanged()
            }
        }
    }
}