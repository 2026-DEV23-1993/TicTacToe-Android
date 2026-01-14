package fr.bnpp.tictactoe.viewmodels.mapper

import fr.bnpp.tictactoe.R
import fr.bnpp.tictactoe.domain.models.TicTacToeGameState
import fr.bnpp.tictactoe.domain.models.TicTacToeSymbol
import fr.bnpp.tictactoe.viewmodels.models.Cell
import fr.bnpp.tictactoe.viewmodels.models.Player
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiState

class TicTacToeViewModelMapper {

    fun updateBoard(currentState: TicTacToeUiState, position: Int): List<Cell> {
        return currentState.board.mapIndexed { index, cell ->
            if (index == position) {
                Cell(player = currentState.currentPlayer, isPlayable = false)
            } else {
                cell
            }
        }
    }

    fun handleGameState(
        updatedBoard: List<Cell>,
        currentPlayer: Player,
        currentGameState: TicTacToeGameState
    ): TicTacToeUiState {
        return when (currentGameState) {
            TicTacToeGameState.Win -> {
                TicTacToeUiState(
                    titleResId = R.string.game_state_winner_title,
                    board = updatedBoard.map { it.copy(isPlayable = false) },
                    currentPlayer = currentPlayer
                )
            }

            TicTacToeGameState.Draw -> {
                TicTacToeUiState(
                    titleResId = R.string.game_state_draw_title,
                    board = updatedBoard,
                    currentPlayer = currentPlayer
                )
            }

            TicTacToeGameState.InProgress -> {
                val nextPlayer = switchPlayer(currentPlayer)
                TicTacToeUiState(
                    titleResId = R.string.game_state_turn_title,
                    board = updatedBoard,
                    currentPlayer = nextPlayer
                )
            }
        }
    }

    private fun switchPlayer(currentPlayer: Player): Player {
        return if (currentPlayer == Player.X) Player.O else Player.X
    }

    fun mapToCurrentSymbolBoard(currentBoard: List<Cell?>): List<TicTacToeSymbol> {
        return currentBoard.map { cell ->
            when (cell?.player) {
                Player.X -> TicTacToeSymbol.X
                Player.O -> TicTacToeSymbol.O
                null -> TicTacToeSymbol.EMPTY
            }
        }
    }

    fun mapToCurrentSymbol(currentPlayer: Player): TicTacToeSymbol {
        return when (currentPlayer) {
            Player.X -> TicTacToeSymbol.X
            Player.O -> TicTacToeSymbol.O
        }
    }
}