package com.sample.gan.details.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sample.gan.R
import com.sample.gan.databinding.ActivityDetailsBinding
import com.sample.gan.models.response.CharacterItem

const val INTENT_EXTRA_KEY = "intent.character_item"

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
            .also {
                if (intent.hasExtra(INTENT_EXTRA_KEY)) {
                    it.item = intent.getParcelableExtra(INTENT_EXTRA_KEY) as CharacterItem
                }
            }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "Character Details"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }
}