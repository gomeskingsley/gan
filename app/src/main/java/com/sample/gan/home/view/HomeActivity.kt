package com.sample.gan.home.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.sample.gan.R
import com.sample.gan.databinding.ActivityHomeBinding
import com.sample.gan.details.view.DetailsActivity
import com.sample.gan.details.view.INTENT_EXTRA_KEY
import com.sample.gan.home.view.adapter.CharacterAdapter
import com.sample.gan.home.viewmodel.HomeViewModel
import com.sample.gan.home.viewmodel.HomeViewModel.Event
import com.sample.gan.home.viewmodel.HomeViewModelImpl
import com.sample.gan.injection.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(this, factory)[HomeViewModelImpl::class.java]
        adapter = CharacterAdapter(homeViewModel)

        val binding = setContentView<ActivityHomeBinding>(this, R.layout.activity_home).also {
            this.series_recycler_view.adapter = adapter
        }

        binding.searchView.doOnTextChanged { text, _, _, _ ->
            adapter.filter.filter(text)
        }

        with(homeViewModel) {
            items.observe(this@HomeActivity, {
                binding.searchView.isVisible = true
                adapter.setItems(it)
            })
            event.observe(this@HomeActivity, { handleViewModelEvent(it) })
        }
    }

    private fun handleViewModelEvent(event: Event) {
        when (event) {
            Event.NetworkError -> Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT)
                .show()
            is Event.NavigateToDetails -> {
                val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
                intent.putExtra(INTENT_EXTRA_KEY, event.data)
                startActivity(intent)
            }
        }
    }
}