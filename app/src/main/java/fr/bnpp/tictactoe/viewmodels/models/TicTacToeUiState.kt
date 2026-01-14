package fr.bnpp.tictactoe.viewmodels.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import fr.bnpp.tictactoe.R
import fr.bnpp.tictactoe.viewmodels.utils.ViewState

data class TicTacToeUiState(
    @StringRes val titleResId: Int,
    val board: List<Cell>,
    val currentPlayer: Player
) : ViewState {
    companion object {
        fun init(): TicTacToeUiState = TicTacToeUiState(
            titleResId = R.string.game_state_turn_title,
            board = List(9) { Cell() },
            currentPlayer = Player.X
        )
    }
}

sealed class Player(val label: String, val color: Color) {
    object X : Player(label = "X", color = Color.Blue)
    object O : Player(label = "O", color = Color.Red)
}

data class Cell(
    val player: Player? = null,
    var isPlayable: Boolean = true
)