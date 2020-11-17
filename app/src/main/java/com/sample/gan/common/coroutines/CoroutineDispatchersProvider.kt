package com.sample.gan.common.coroutines

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutineDispatchersProvider @Inject constructor() {
    val main: CoroutineContext by lazy { Dispatchers.Main }
    val io: CoroutineContext by lazy { Dispatchers.IO }
    val default: CoroutineContext by lazy { Dispatchers.Default }
}