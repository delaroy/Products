package com.example.products.di

import com.example.products.data.datastore.DataStoreManager
import com.example.products.data.datastore.DataStoreManagerImpl
import com.example.products.data.repository.ProductRepositoryImpl
import com.example.products.domain.product.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindDatastoreManager(impl: DataStoreManagerImpl): DataStoreManager

}
