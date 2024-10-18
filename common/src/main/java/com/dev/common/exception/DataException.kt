package com.dev.common.exception

sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    data object Internet : DataException("Unable to connect. Please check connection.")

    data object SessionExpired : DataException()

    data class Api(
        override val message: String,
        val title: String = "",
        val data: String = "",
        val errorCode: Int = -1
    ) : DataException(message)
}
