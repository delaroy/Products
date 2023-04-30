package com.example.products.data.network.api

import com.example.products.data.network.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/products.json")
    suspend fun fetchProducts(): Response<List<ProductResponse>>
}