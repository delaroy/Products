package com.example.products.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.products.data.common.Resource
import com.example.products.data.datastore.DataStoreManager
import com.example.products.data.network.dto.ProductResponse
import com.example.products.domain.product.ProductFetchUseCase
import com.example.products.util.Event
import com.example.products.util.LiveEventResource
import com.example.products.util.MutableLiveEventResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductFetchViewModel @Inject constructor(
    private val productFetchUseCase: ProductFetchUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    private val _requestProducts = MutableLiveEventResource<List<ProductResponse>>()
    val requestProducts: LiveEventResource<List<ProductResponse>> = _requestProducts

    fun requestProduct() {
        _requestProducts.value = Event(Resource.loading())
        viewModelScope.launch {
            val initiateProductResult = productFetchUseCase(param = Unit)
            initiateProductResult.collect { values ->
                if (values.isSuccess()) {
                    _requestProducts.value = Event(Resource.success(values.data))
                } else {
                    _requestProducts.value = Event(Resource.error(message = values.message))
                }
            }
        }
    }
}
