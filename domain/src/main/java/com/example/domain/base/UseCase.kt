package com.example.domain.base

abstract class UseCase<in P, R> {
    operator fun invoke(parameters: P): R {
        return execute(parameters)
    }

    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R
}
