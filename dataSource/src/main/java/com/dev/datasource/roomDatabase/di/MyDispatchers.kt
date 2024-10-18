package com.dev.datasource.roomDatabase.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val spoTvDispatcher: MyDispatchers)

enum class MyDispatchers {
    Default,
    IO,
}