package com.pmatuki.wowpapers.view.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class MviContainer<STATE, EVENT>(
    private val scope: CoroutineScope,
    initialState: STATE
) {
    private val _state: MutableStateFlow<STATE> =
        MutableStateFlow(initialState)

    val state: StateFlow<STATE> = _state

    private val _event = MutableSharedFlow<EVENT>()

    val event: Flow<EVENT>
        get() = _event.asSharedFlow()

    fun intent(transform: suspend MviContainer<STATE, EVENT>.() -> Unit) {
        scope.launch(SINGLE_THREAD) {
            transform()
        }
    }

    suspend fun reduce(reducer: STATE.() -> STATE) {
        withContext(SINGLE_THREAD) {
            _state.value = _state.value.reducer()
        }
    }

    suspend fun sendEvent(event: EVENT) {
        _event.emit(event)
    }

    companion object {
        // To ensure state is not changed concurrently
        @OptIn(ObsoleteCoroutinesApi::class)
        private val SINGLE_THREAD = newSingleThreadContext("mvi_dispatcher")
    }
}
