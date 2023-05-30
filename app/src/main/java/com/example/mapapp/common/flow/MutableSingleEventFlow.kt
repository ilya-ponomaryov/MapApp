package com.example.mapapp.common.flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> MutableSingleEventFlow(): MutableSharedFlow<T> =
    MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)