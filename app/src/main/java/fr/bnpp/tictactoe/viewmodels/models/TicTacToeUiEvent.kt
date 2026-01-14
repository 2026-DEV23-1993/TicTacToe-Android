package fr.bnpp.tictactoe.viewmodels.models

import fr.bnpp.tictactoe.viewmodels.utils.ViewEvent

sealed class TicTacToeUiEvent : ViewEvent {
    data class Play(val position: Int) : TicTacToeUiEvent()
    object Restart : TicTacToeUiEvent()
}