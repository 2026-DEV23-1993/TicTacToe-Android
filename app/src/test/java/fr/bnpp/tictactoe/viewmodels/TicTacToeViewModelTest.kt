package fr.bnpp.tictactoe.viewmodels

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TicTacToeViewModelTest {

    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setUp() {
        viewModel = TicTacToeViewModel()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}