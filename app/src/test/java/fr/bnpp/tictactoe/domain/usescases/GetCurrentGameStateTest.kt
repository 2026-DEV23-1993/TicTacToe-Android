package fr.bnpp.tictactoe.domain.usescases

import fr.bnpp.tictactoe.domain.models.TicTacToeGameState
import fr.bnpp.tictactoe.domain.models.TicTacToeSymbol
import fr.bnpp.tictactoe.domain.usecases.GetCurrentGameState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCurrentGameStateTest {
    private lateinit var getCurrentGameState: GetCurrentGameState

    @Before
    fun setUp() {
        getCurrentGameState = GetCurrentGameState()
    }

    @Test
    fun `horizontal win for X returns Win state`() {
        val board = listOf(
            TicTacToeSymbol.X, TicTacToeSymbol.X, TicTacToeSymbol.X,
            TicTacToeSymbol.EMPTY, TicTacToeSymbol.O, TicTacToeSymbol.EMPTY,
            TicTacToeSymbol.EMPTY, TicTacToeSymbol.EMPTY, TicTacToeSymbol.O
        )
        val result = getCurrentGameState(board, TicTacToeSymbol.X)
        assertEquals(TicTacToeGameState.Win, result)
    }

    @Test
    fun `vertical win for O returns Win state`() {
        val board = listOf(
            TicTacToeSymbol.O, TicTacToeSymbol.X, TicTacToeSymbol.EMPTY,
            TicTacToeSymbol.O, TicTacToeSymbol.X, TicTacToeSymbol.EMPTY,
            TicTacToeSymbol.O, TicTacToeSymbol.EMPTY, TicTacToeSymbol.EMPTY
        )
        val result = getCurrentGameState(board, TicTacToeSymbol.O)
        assertEquals(TicTacToeGameState.Win, result)
    }

    @Test
    fun `diagonal win for X returns Win state`() {
        val board = listOf(
            TicTacToeSymbol.X, TicTacToeSymbol.O, TicTacToeSymbol.EMPTY,
            TicTacToeSymbol.EMPTY, TicTacToeSymbol.X, TicTacToeSymbol.O,
            TicTacToeSymbol.EMPTY, TicTacToeSymbol.EMPTY, TicTacToeSymbol.X
        )
        val result = getCurrentGameState(board, TicTacToeSymbol.X)
        assertEquals(TicTacToeGameState.Win, result)
    }

    @Test
    fun `full board with no winner returns Draw state`() {
        val board = listOf(
            TicTacToeSymbol.X, TicTacToeSymbol.O, TicTacToeSymbol.X,
            TicTacToeSymbol.X, TicTacToeSymbol.O, TicTacToeSymbol.O,
            TicTacToeSymbol.O, TicTacToeSymbol.X, TicTacToeSymbol.X
        )
        val result = getCurrentGameState(board, TicTacToeSymbol.X)
        assertEquals(TicTacToeGameState.Draw, result)
    }

    @Test
    fun `not filled board with no winner returns InProgress state`() {
        val board = listOf(
            TicTacToeSymbol.X, TicTacToeSymbol.O, TicTacToeSymbol.EMPTY,
            TicTacToeSymbol.EMPTY, TicTacToeSymbol.X, TicTacToeSymbol.EMPTY,
            TicTacToeSymbol.O, TicTacToeSymbol.EMPTY, TicTacToeSymbol.EMPTY
        )
        val result = getCurrentGameState(board, TicTacToeSymbol.X)
        assertEquals(TicTacToeGameState.InProgress, result)
    }

}