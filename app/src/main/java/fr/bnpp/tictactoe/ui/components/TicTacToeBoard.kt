package fr.bnpp.tictactoe.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.bnpp.tictactoe.ui.theme.Dimens
import fr.bnpp.tictactoe.viewmodels.models.Cell
import fr.bnpp.tictactoe.viewmodels.models.TicTacToeUiEvent

@Composable
fun TicTacToeBoard(
    board: List<Cell>,
    onPlay: (TicTacToeUiEvent) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = Dimens.boardHorizontalPadding),
        columns = GridCells.Fixed(3)
    ) {
        items(board.size) { index ->
            Box(
                modifier = Modifier
                    .size(Dimens.boardCellSize)
                    .background(Color.LightGray)
                    .border(BorderStroke(Dimens.borderStroke, Color.Black))
                    .clickable(enabled = board[index].isPlayable) {
                        onPlay(TicTacToeUiEvent.Play(index))
                    }, contentAlignment = Alignment.Center
            ) {
                TicTacToeCell(player = board[index].player)
            }
        }
    }
}