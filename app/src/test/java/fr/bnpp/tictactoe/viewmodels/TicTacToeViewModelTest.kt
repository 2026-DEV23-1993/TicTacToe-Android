package fr.bnpp.tictactoe.viewmodels

import fr.bnpp.tictactoe.R
import fr.bnpp.tictactoe.viewmodels.models.Player
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiEvent
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TicTacToeViewModelTest {

    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setUp() {
        viewModel = TicTacToeViewModel()
    }

    @Test
    fun `initial state is correct`() {
        val expectedState = TicTacToeUiState.init()
        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `play event updates state correctly for first move`() {
        val position = 5
        viewModel.onEvent(TicTacToeUiEvent.Play(position))

        val newState = viewModel.state.value
        assertEquals(Player.X, newState.board[position].player)
        assertFalse(newState.board[position].isPlayable)
        assertEquals(Player.O, newState.currentPlayer)
    }

    @Test
    fun `play events leading to a win should result in a win state`() {
        viewModel.onEvent(TicTacToeUiEvent.Play(0)) // X
        viewModel.onEvent(TicTacToeUiEvent.Play(3)) // O
        viewModel.onEvent(TicTacToeUiEvent.Play(1)) // X
        viewModel.onEvent(TicTacToeUiEvent.Play(4)) // O
        viewModel.onEvent(TicTacToeUiEvent.Play(2)) // X wins

        val finalState = viewModel.state.value
        assertTrue(finalState.board.all { !it.isPlayable })
        assertEquals(R.string.game_state_winner_title, finalState.titleResId)
    }

    @Test
    fun `restart event should reset the game state`() {
        viewModel.onEvent(TicTacToeUiEvent.Play(0))
        viewModel.onEvent(TicTacToeUiEvent.Play(1))

        viewModel.onEvent(TicTacToeUiEvent.Restart)

        assertEquals(TicTacToeUiState.init(), viewModel.state.value)
    }
}