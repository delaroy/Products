package com.example.products.domain.common

abstract class UseCase<in Param, out Output> {
    abstract fun invoke(param: Param): Output
}