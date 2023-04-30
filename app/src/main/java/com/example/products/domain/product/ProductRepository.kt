package com.example.products.domain.product

import com.example.products.data.common.Resource
import com.example.products.data.network.dto.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun fetchProducts(): Flow<Resource<List<ProductResponse>>>
}
