package com.example.products.domain.product

import com.example.products.data.common.Resource
import com.example.products.data.network.dto.ProductResponse
import com.example.products.domain.common.SuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductFetchUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : SuspendUseCase<Unit, Flow<Resource<List<ProductResponse>>>>() {
    override suspend fun invoke(param: Unit): Flow<Resource<List<ProductResponse>>> =
        productRepository.fetchProducts()
}

