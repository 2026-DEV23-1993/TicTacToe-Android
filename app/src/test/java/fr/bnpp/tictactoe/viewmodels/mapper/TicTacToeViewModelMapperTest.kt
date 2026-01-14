package fr.bnpp.tictactoe.viewmodels.mapper

import fr.bnpp.tictactoe.R
import fr.bnpp.tictactoe.domain.models.TicTacToeGameState
import fr.bnpp.tictactoe.domain.models.TicTacToeSymbol
import fr.bnpp.tictactoe.viewmodels.models.Cell
import fr.bnpp.tictactoe.viewmodels.models.Player
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TicTacToeViewModelMapperTest {

    private lateinit var mapper: TicTacToeViewModelMapper

    @Before
    fun setUp() {
        mapper = TicTacToeViewModelMapper()
    }

    @Test
    fun `first play updates board and state correctly`() {
        val initialState = TicTacToeUiState.init()
        val position = 0

        val updatedBoard = mapper.updateBoard(initialState, position)
        val newState = mapper.handleGameState(updatedBoard, initialState.currentPlayer, TicTacToeGameState.InProgress)

        assertEquals(Player.X, newState.board[position].player)
        assertFalse(newState.board[position].isPlayable)
        assertEquals(Player.O, newState.currentPlayer)
        assertEquals(R.string.game_state_turn_title, newState.titleResId)

    }

    @Test
    fun `updateBoard should only modify the played cell`() {
        val initialBoard = List(9) { index -> if (index == 0) Cell(player = Player.O) else Cell() }
        val initialState = TicTacToeUiState(
            board = initialBoard,
            currentPlayer = Player.X,
            titleResId = R.string.game_state_turn_title
        )
        val positionToPlay = 4

        val updatedBoard = mapper.updateBoard(initialState, positionToPlay)

        assertEquals(Player.X, updatedBoard[positionToPlay].player)
        assertFalse(updatedBoard[positionToPlay].isPlayable)

        for (i in updatedBoard.indices) {
            if (i != positionToPlay) {
                assertEquals(initialBoard[i], updatedBoard[i])
            }
        }
    }

    @Test
    fun `handleGameState with Win for X returns correct state`() {
        val board = List(9) { Cell() }
        val currentPlayer = Player.X
        val gameState = TicTacToeGameState.Win

        val newState = mapper.handleGameState(board, currentPlayer, gameState)

        assertEquals(R.string.game_state_winner_title, newState.titleResId)
        assertTrue(newState.board.all { !it.isPlayable })
    }

    @Test
    fun `handleGameState with Win for O returns correct state`() {
        val board = List(9) { Cell() }
        val currentPlayer = Player.O
        val gameState = TicTacToeGameState.Win

        val newState = mapper.handleGameState(board, currentPlayer, gameState)

        assertEquals(R.string.game_state_winner_title, newState.titleResId)
        assertTrue(newState.board.all { !it.isPlayable })
    }

    @Test
    fun `handleGameState with Draw returns correct state`() {
        val board = List(9) { Cell() }
        val currentPlayer = Player.X
        val gameState = TicTacToeGameState.Draw

        val newState = mapper.handleGameState(board, currentPlayer, gameState)

        assertEquals(R.string.game_state_draw_title, newState.titleResId)
        assertEquals(board, newState.board)
    }

    @Test
    fun `handleGameState with InProgress from X returns correct state`() {
        val board = List(9) { Cell() }
        val currentPlayer = Player.X
        val gameState = TicTacToeGameState.InProgress

        val newState = mapper.handleGameState(board, currentPlayer, gameState)

        assertEquals(R.string.game_state_turn_title, newState.titleResId)
        assertEquals(Player.O, newState.currentPlayer)
    }

    @Test
    fun `handleGameState with InProgress from O returns correct state`() {
        val board = List(9) { Cell() }
        val currentPlayer = Player.O
        val gameState = TicTacToeGameState.InProgress

        val newState = mapper.handleGameState(board, currentPlayer, gameState)

        assertEquals(R.string.game_state_turn_title, newState.titleResId)
        assertEquals(Player.X, newState.currentPlayer)
    }

    @Test
    fun `mapToCurrentSymbol should map Player X to Symbol X`() {
        assertEquals(TicTacToeSymbol.X, mapper.mapToCurrentSymbol(Player.X))
    }

    @Test
    fun `mapToCurrentSymbol should map Player O to Symbol O`() {
        assertEquals(TicTacToeSymbol.O, mapper.mapToCurrentSymbol(Player.O))
    }

    @Test
    fun `mapToCurrentSymbolBoard should map a mixed board correctly`() {
        val board = listOf(
            Cell(player = Player.X), Cell(player = Player.O), null,
            Cell(player = null), Cell(player = Player.X), Cell(player = Player.O),
            Cell(player = Player.O), null, Cell(player = Player.X)
        )

        val expected = listOf(
            TicTacToeSymbol.X, TicTacToeSymbol.O, TicTacToeSymbol.EMPTY,
            TicTacToeSymbol.EMPTY, TicTacToeSymbol.X, TicTacToeSymbol.O,
            TicTacToeSymbol.O, TicTacToeSymbol.EMPTY, TicTacToeSymbol.X
        )

        val result = mapper.mapToCurrentSymbolBoard(board)
        assertEquals(expected, result)
    }

    @Test
    fun `mapToCurrentSymbolBoard should map an empty board to all EMPTY`() {
        val board = List<Cell?>(9) { null }
        val expected = List(9) { TicTacToeSymbol.EMPTY }
        val result = mapper.mapToCurrentSymbolBoard(board)
        assertEquals(expected, result)
    }
}
