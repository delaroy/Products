package com.example.products.domain.common


abstract class SuspendUseCase<in Param, out Output> {

    abstract suspend operator fun invoke(param: Param): Output
}