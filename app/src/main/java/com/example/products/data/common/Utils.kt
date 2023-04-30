package com.example.products.data.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException



suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

    // Returning api response
    // wrapped in Resource class
    return withContext(Dispatchers.IO) {
        val response: Response<T>?
        try {

            // Here we are calling api lambda
            // function that will return response
            // wrapped in Retrofit's Response class
            response = apiToBeCalled()

            if (response.isSuccessful) {
                // In case of success response we
                // are returning Resource.Success object
                // by passing our data in it.
                Resource.success(data = response.body())
            } else {
                Resource.error(message = "An error occurred")
            }

        } catch (e: HttpException) {
            // Returning HttpException's message
            // wrapped in Resource.Error
            Resource.error(message = e.message ?: "An error occurred")
        } catch (e: IOException) {
            // Returning no internet message
            // wrapped in Resource.Error
            Resource.error("Please check your network connection")
        } catch (e: Exception) {
            // Returning 'Something went wrong' in case
            // of unknown error wrapped in Resource.Error
            Resource.error(message = "An error occurred")
        }
    }
}

fun <T> DataStore<Preferences>.get(
    key: Preferences.Key<T>,
    defaultValue: T
): T = runBlocking {
    data.first()[key] ?: defaultValue
}

fun <T> DataStore<Preferences>.set(
    key: Preferences.Key<T>,
    value: T?
) = runBlocking<Unit> {
    edit {
        if (value == null) {
            it.remove(key)
        } else {
            it[key] = value
        }
    }
}