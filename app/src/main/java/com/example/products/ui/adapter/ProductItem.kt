package com.example.products.ui.adapter

import com.example.products.data.network.dto.ProductResponse


class ProductItem(productResponse: ProductResponse) : ListItem() {
    private val productResp: ProductResponse

    init {
        this.productResp = productResponse
    }

    fun getProduct(): ProductResponse {
        return productResp
    }

    override val type: Int
        get() = TYPE_PRODUCT

}