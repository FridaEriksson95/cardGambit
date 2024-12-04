package com.example.cardgambit

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View

//Gameboard for if player is against AI, inheritance functionality from Gameboard
class GameBoardAI : GameBoard() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Makes sure AIs buttons are displayed and player 2(friends) buttons are gone
        binding.btnPlayer2AIHigher.visibility = View.VISIBLE
        binding.btnPlayer2AILower.visibility = View.VISIBLE

        binding.btnPlayer2Lower.visibility = View.GONE
        binding.btnPlayer2Higher.visibility = View.GONE
    }

    /*Function for handleplayerGuess when against AI, sets color for AIs guesses and make sure player 1 make first move
    then delay for AIs answer for 0.5sek
     */
    override fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        if (player == 1) {
            super.handlePlayerGuess(player, guessHigher)

            val selectedColor = Color.rgb(29, 105, 29)
            if (guessHigher) {
                binding.btnPlayer1Higher.setBackgroundColor(selectedColor)
            } else {
                binding.btnPlayer1Lower.setBackgroundColor(selectedColor)
            }
            if (p1Guess) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val aiChoice = calculateAIMove()
                    handleAIGuess(aiChoice)
                },500)
            }
        }
    }

    //Calculates a random move for AI
    private fun calculateAIMove(): Boolean {
        return (0..1).random() == 1
    }

    //Handles guesses when playing against AI
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