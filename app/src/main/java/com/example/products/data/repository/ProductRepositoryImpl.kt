package com.example.products.data.repository

import com.example.products.data.common.Resource
import com.example.products.data.common.safeApiCall
import com.example.products.data.datastore.DataStoreManager
import com.example.products.data.network.api.ApiService
import com.example.products.data.network.dto.ProductResponse
import com.example.products.di.IoDispatcher
import com.example.products.domain.product.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreManager: DataStoreManager,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ProductRepository {

    override suspend fun fetchProducts(): Flow<Resource<List<ProductResponse>>> =
        withContext(dispatcher) {
            return@withContext flow<Resource<List<ProductResponse>>> {
                emit(safeApiCall { apiService.fetchProducts() })
            }.flowOn(Dispatchers.IO)
        }

}
