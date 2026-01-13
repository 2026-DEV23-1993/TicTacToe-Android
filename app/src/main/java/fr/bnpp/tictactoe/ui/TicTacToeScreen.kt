package fr.bnpp.tictactoe.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TicTacToeScreen(modifier: Modifier) {
    Box(modifier) {
        Text(text = "Hello, Tic-Tac-Toe!")
    }
}