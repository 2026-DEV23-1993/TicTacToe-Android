package fr.bnpp.tictactoe.viewmodels.models

import androidx.compose.ui.graphics.Color
import fr.bnpp.tictactoe.viewmodels.utils.ViewState

data class TicTacToeUiState(
    val title: String,
    val board: List<Cell>,
    val currentPlayer: Player
) : ViewState {
    companion object {
        fun init(): TicTacToeUiState = TicTacToeUiState(
            title = "Turn: ${Player.X.label}",
            board = List(9) { Cell() },
            currentPlayer = Player.X
        )
    }
}

sealed class Player(val label: String, val color: Color) {
    object X : Player(label = "X", color = Color.Red)
    object O : Player(label = "O", color = Color.Blue)
}

data class Cell(
    val player: Player? = null,
    var isPlayable: Boolean = true
)