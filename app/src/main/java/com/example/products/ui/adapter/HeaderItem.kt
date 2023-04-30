package com.example.products.ui.adapter

import com.example.products.data.network.dto.ProductResponse


class HeaderItem(brand: String) : ListItem() {
    private val brand: String

    init {
        this.brand = brand
    }

    fun getHeader(): String {
        return brand
    }

    override val type: Int
        get() = TYPE_HEADER
}