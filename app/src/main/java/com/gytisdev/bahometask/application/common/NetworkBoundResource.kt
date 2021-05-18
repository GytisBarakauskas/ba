package com.gytisdev.bahometask.application

import com.gytisdev.bahometask.application.common.Outcome
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType, TransformType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline transform: (ResultType) -> TransformType,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Outcome.Loading(transform(data)))

        try {
            saveFetchResult(fetch())
            query().map { transform(it) }.map { Outcome.Success(it) }
        } catch (throwable: Throwable) {
            query().map { transform(it) }.map { Outcome.Error(throwable, it) }
        }
    } else {
        query().map { transform(it) }.map { Outcome.Success(it) }
    }
    emitAll(flow)
}