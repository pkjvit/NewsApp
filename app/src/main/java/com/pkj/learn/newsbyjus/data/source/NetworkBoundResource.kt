package com.pkj.learn.newsbyjus.data.source

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.pkj.learn.newsbyjus.data.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception

/**
 * A generic class that can provide a resource backed by both
 * the SQLite database and the network.
 *
 * [ResultType] represents the type for database.
 * [RequestType] represents the type for network.
 */

@FlowPreview
@ExperimentalCoroutinesApi
abstract class NetworkBoundResource<ResultType, RequestType> {

    fun asFlow() = flow<Result<Any?>> {
        emit(Result.Loading())

        val dbValue = loadFromDb().first()
        if (shouldFetch(dbValue)) {
            emit(Result.Success(dbValue))
            try {
                val apiResponse = fetchFromNetwork()
                when {
                    apiResponse.isSuccessful && apiResponse.body() != null -> {
                        apiResponse.body()?.let { saveNetworkResult(it) }
                        emitAll(loadFromDb().map { Result.Success(it) })
                    }
                    else -> {
                        emit(Result.Error(apiResponse.message()))
                    }
                }
            } catch (e: Exception) {
                emit(Result.Error("Unable to fetch from network"))
            }
        } else {
            emitAll(loadFromDb().map { Result.Success(it) })
        }
    }

    @WorkerThread
    protected open fun processResponse(response: Response<RequestType>) = response

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(response: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Response<RequestType>
}