package fr.bnpp.tictactoe.viewmodels.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S: ViewState, E: ViewEvent>(val initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    abstract fun onEvent(event: E)

    protected fun updateState(update: (S) -> S) {
        _state.update(update)
    }
}

interface ViewState

interface ViewEvent