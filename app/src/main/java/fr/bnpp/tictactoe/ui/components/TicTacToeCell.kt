package fr.bnpp.tictactoe.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import fr.bnpp.tictactoe.viewmodels.models.Player

@Composable
fun TicTacToeCell(player: Player?) {
    player?.let {
        Text(
            text = player.label,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = player.color
        )
    }
}