package fr.bnpp.tictactoe.viewmodels

import fr.bnpp.tictactoe.domain.usecases.GetCurrentGameState
import fr.bnpp.tictactoe.viewmodels.mapper.TicTacToeViewModelMapper
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiEvent
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiState
import fr.bnpp.tictactoe.viewmodels.utils.BaseViewModel

class TicTacToeViewModel(
    private val mapper: TicTacToeViewModelMapper = TicTacToeViewModelMapper(),
    private val getCurrentGameState: GetCurrentGameState = GetCurrentGameState()
) : BaseViewModel<TicTacToeUiState, TicTacToeUiEvent>(TicTacToeUiState.init()) {

    override fun onEvent(event: TicTacToeUiEvent) {
        when (event) {
            is TicTacToeUiEvent.Play -> play(event.position)
            TicTacToeUiEvent.Restart -> restartGame()
        }
    }

    private fun play(position: Int) {
        val currentState = state.value
        val updatedBoard = mapper.updateBoard(currentState = currentState, position = position)
        val currentGameState = getCurrentGameState(
            currentSymbolBoard = mapper.mapToCurrentSymbolBoard(updatedBoard),
            currentSymbol = mapper.mapToCurrentSymbol(currentState.currentPlayer)
        )
        updateState {
            mapper.handleGameState(
                updatedBoard = updatedBoard,
                currentPlayer = currentState.currentPlayer,
                currentGameState = currentGameState
            )
        }
    }

    private fun restartGame() {
        updateState { TicTacToeUiState.init() }
    }
}