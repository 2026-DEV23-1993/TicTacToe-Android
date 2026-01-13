package fr.bnpp.tictactoe.domain.models

sealed class TicTacToeGameState {
    object Win : TicTacToeGameState()
    object Draw : TicTacToeGameState()
    object InProgress : TicTacToeGameState()
}