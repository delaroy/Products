package com.example.products.ui.adapter


abstract class ListItem {
    abstract val type: Int

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_PRODUCT = 1
    }
}