package com.example.products.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.products.data.common.Resource

typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>
typealias MutableLiveEventResource<T> = MutableLiveData<Event<Resource<T>>>
typealias LiveEventResource<T> = LiveData<Event<Resource<T>>>
typealias MutableLiveResource<T> = MutableLiveData<Resource<T>>
typealias LiveResource<T> = LiveData<Resource<T>>
