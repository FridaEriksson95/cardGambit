package com.example.cardgambit


import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.cardgambit.databinding.ActivityGameBoardFriendBinding

class GameBoardAI : GameBoardFriend() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnPlayer2AIHigher.visibility = View.VISIBLE
        binding.btnPlayer2AILower.visibility = View.VISIBLE

        binding.btnPlayer2Lower.visibility = View.GONE
        binding.btnPlayer2Higher.visibility = View.GONE
    }


class GameBoardAI : GameBoardFriend() {


    override fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        if (player == 1) {
            // Player 1 makes their choice manually
            super.handlePlayerGuess(player, guessHigher)


            binding.btnPlayer1Higher.setBackgroundColor(Color.TRANSPARENT)
            binding.btnPlayer1Lower.setBackgroundColor(Color.TRANSPARENT)

            val selectedColor = Color.rgb(29, 105, 29)
            if (guessHigher) {
                binding.btnPlayer1Higher.setBackgroundColor(selectedColor)
            } else {
                binding.btnPlayer1Lower.setBackgroundColor(selectedColor)
            }
            if (p1Guess) {
                val aiChoice = calculateAIMove()
                handleAIGuess(aiChoice)

            // Trigger AI's move automatically
            if (p1Guess) {
                val aiChoice = calculateAIMove()
                super.handlePlayerGuess(2, aiChoice)

            }
        }
    }

    private fun calculateAIMove(): Boolean {
        // Simple AI logic: Random higher or lower
        return (0..1).random() == 1
    }


    private fun handleAIGuess(guessHigher: Boolean) {
        p2Choice = guessHigher
        p2Guess = true

        binding.btnPlayer2AIHigher.setBackgroundColor(Color.TRANSPARENT)
        binding.btnPlayer2AILower.setBackgroundColor(Color.TRANSPARENT)

        val selectedColor = Color.rgb(29, 105, 29)
        if (guessHigher) {
            binding.btnPlayer2AIHigher.setBackgroundColor(selectedColor)
        } else {
            binding.btnPlayer2AILower.setBackgroundColor(selectedColor)
        }

        binding.btnPlayer2AIHigher.isEnabled = false
        binding.btnPlayer2AILower.isEnabled = false

        if (p1Guess && p2Guess) {
            evaluateGuesses()
        }
    }
}

}

