package fr.bnpp.tictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.bnpp.tictactoe.R
import fr.bnpp.tictactoe.ui.components.TicTacToeBoard
import fr.bnpp.tictactoe.ui.theme.Dimens
import fr.bnpp.tictactoe.viewmodels.TicTacToeViewModel
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiEvent

@Composable
fun TicTacToeScreen(modifier: Modifier, viewModel: TicTacToeViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(state.titleResId, state.currentPlayer.label),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(Dimens.spacingMedium))

        TicTacToeBoard(
            board = state.board,
            onPlay = viewModel::onEvent
        )

        Spacer(Modifier.height(Dimens.spacingMedium))

        Button(onClick = { viewModel.onEvent(TicTacToeUiEvent.Restart) }) {
            Text(stringResource(R.string.restart_button_title))
        }
    }
}
