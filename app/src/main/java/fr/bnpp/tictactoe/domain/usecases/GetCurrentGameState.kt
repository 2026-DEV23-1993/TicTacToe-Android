package fr.bnpp.tictactoe.domain.usecases

import fr.bnpp.tictactoe.domain.models.TicTacToeGameState
import fr.bnpp.tictactoe.domain.models.TicTacToeSymbol

class GetCurrentGameState {

    operator fun invoke(currentSymbolBoard: List<TicTacToeSymbol>, currentSymbol: TicTacToeSymbol): TicTacToeGameState {
        return when {
            isWinner(currentSymbolBoard, currentSymbol) -> TicTacToeGameState.Win
            isDraw(currentSymbolBoard) -> TicTacToeGameState.Draw
            else -> TicTacToeGameState.InProgress
        }
    }

    private fun isWinner(board: List<TicTacToeSymbol?>, ticTacToeSymbol: TicTacToeSymbol): Boolean {
        return winPatterns.any { pattern ->
            pattern.all { board[it] == ticTacToeSymbol }
        }
    }

    private fun isDraw(board: List<TicTacToeSymbol?>): Boolean = board.all { it != TicTacToeSymbol.EMPTY}

    private companion object Companion {
        val winPatterns = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // columns
            listOf(0, 4, 8), listOf(2, 4, 6)                  // diagonals
        )
    }
}