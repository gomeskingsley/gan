package com.sample.gan.common.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("load_image")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url).into(view)
    }

    @JvmStatic
    @BindingAdapter("appearances")
    fun appearances(view: TextView, appearances: List<Int>) {
        val joinToString = appearances.joinToString(separator = ", ") { it.toString() }
        view.text = "Season: $joinToString"
    }

    @JvmStatic
    @BindingAdapter("occupations")
    fun occupations(view: TextView, appearances: List<String>) {
        val joinToString = appearances.joinToString(separator = ", ") { it }
        view.text = "Occupation: $joinToString"
    }
}