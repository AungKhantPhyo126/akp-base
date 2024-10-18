package com.dev.datasource.network.handler

import android.accounts.NetworkErrorException
import arrow.core.Either
import com.dev.common.GlobalStateFlow
import com.dev.common.exception.DataException
import com.dev.datasource.model.response.BaseResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.SerializationException
import org.json.JSONObject
import retrofit2.Response
import java.net.UnknownHostException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

typealias ApiCall<T> = suspend () -> Response<BaseResponse<T>>

const val ERROR_MESSAGE_GENERAL = "Something went wrong. Please try again."
const val ERROR_JSON_CONVERSION = "Error json conversion. Please try again."
const val ERROR_TITLE_GENERAL = "Error"

suspend fun <T, R> handleCall(
    apiCall: ApiCall<T>,
    mapper: suspend (T, String?, Int?) -> R,
    onDataNull: (BaseResponse<T>?) -> Either<DataException, R> = {
        Either.Left(
            DataException.Api(
                title = ERROR_TITLE_GENERAL,
                message = it?.message ?: ERROR_MESSAGE_GENERAL,
                errorCode = it?.errorCode ?: -1
            )
        )
    }
): Either<DataException, R> = try {
    apiCall().let {
        val body = it.body()
        try {
            when (body?.errorCode) {
                0, 119, 405 -> {
                    if (body.data != null) {
                        Either.Right(mapper(body.data, body.message.orEmpty(), body.errorCode))
                    } else {
                        onDataNull(body)
                    }
                }

                else -> {
                    errorResponseHandler(it, body?.errorCode, body?.message)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorResponseHandler(it, body?.errorCode, body?.message)
        }
    }
} catch (e: Exception) {
    e.printStackTrace()
    e.convertEither()
}

suspend fun <T, R> errorResponseHandler(
    it: Response<T>,
    errorCode: Int?,
    message: String?
): Either<DataException, R> {

    val jsonObject: JSONObject? = try {
        suspendCancellableCoroutine { cont ->
            runCatching {
                cont.resume(it.errorBody()?.string())
            }.recover { throwable ->
                cont.resumeWithException(throwable)
            }
        }?.let { JSONObject(it) }
    } catch (e: Exception) {
        null
    }

    val errorMessage = try {
        jsonObject?.getString("message")
    } catch (e: Exception) {
        null
    }

    return when(it.code()) {
        205 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 205))
        }

        209 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 209))
        }

        222 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 222))
        }

        225 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 225))
        }

        226 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 226))
        }

        227 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 227))
        }

        403 -> {
            GlobalStateFlow.emit(
                GlobalStateFlow.GlobalState.Error403)
            Either.Left(DataException.Api(message = ""))
        }

        503 -> {
            val (maintenanceOn, title, description) = if (jsonObject != null) {
                Triple(
                    jsonObject.getBoolean("MaintenanceOn"),
                    jsonObject.getString("Title"),
                    jsonObject.getString("Description")
                )
            } else Triple(true, "", "")

            GlobalStateFlow.emit(
                GlobalStateFlow.GlobalState.Maintenance(
                    maintenanceOn = maintenanceOn,
                    title = title.orEmpty(),
                    description = description.orEmpty()
                )
            )
            Either.Left(DataException.Api(message = ""))
        }

        401 -> {
            Either.Left(DataException.Api(message = ""))
        }

        409 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 409))
        }

        419 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 419))
        }

        418 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL))
        }

        431 -> {
            Either.Left(DataException.Api(message = errorMessage ?: ERROR_MESSAGE_GENERAL, errorCode = 431))
        }

        else -> {
            Either.Left(
                DataException.Api(
                    message = message ?: errorMessage ?: ERROR_MESSAGE_GENERAL,
                    title = ERROR_TITLE_GENERAL,
                    errorCode = errorCode ?: it.code()
                )
            )
        }
    }
}

private fun Exception.convertEither(): Either<DataException, Nothing> {
    return when (this) {
        is NetworkErrorException, is UnknownHostException -> Either.Left(DataException.Internet)
        is SerializationException -> {
            Either.Left(
                DataException.Api(
                    message = this.message ?: ERROR_JSON_CONVERSION,
                    title = ERROR_TITLE_GENERAL,
                    errorCode = -1
                )
            )
        }

        else -> Either.Left(
            DataException.Api(
                message = this.message ?: ERROR_MESSAGE_GENERAL,
                title = ERROR_TITLE_GENERAL,
                errorCode = -1
            )
        )
    }
}