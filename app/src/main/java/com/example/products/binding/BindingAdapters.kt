package com.example.products.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.products.util.camelCase

@BindingAdapter("titleName")
fun bindTitleName(view: TextView, title: String?) {
    title?.let {
        view.text = camelCase(title)
    }
}