package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityGameBoardFriendBinding
import java.lang.Math.random

class GameBoardFriend : AppCompatActivity() {

    private lateinit var binding: ActivityGameBoardFriendBinding
    private lateinit var gamefrag : GameFragment

    private var p1Guess: Boolean = false
    private var p2Guess: Boolean = false

    private var p1Choice: Boolean = false
    private var p2Choice: Boolean = false

    var previousCardValue = 0
    private var currentRound = 1
    private var totalRounds: Int = 0


    // ----------------------------onCreate----------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBoardFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gamefrag = GameFragment()
        binding.player1Score.text = "Player 1 Score: 0"
        binding.player2Score.text = "Player 2 Score: 0"

        totalRounds = intent.getIntExtra("ROUNDS", 1)
        setupGame()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnPlayer1Higher.setOnClickListener { handlePlayerGuess(1, true) }
        binding.btnPlayer1Lower.setOnClickListener { handlePlayerGuess(1, false) }
        binding.btnPlayer2Higher.setOnClickListener { handlePlayerGuess(2, true) }
        binding.btnPlayer2Lower.setOnClickListener { handlePlayerGuess(2, false) }
    }
    private fun setupGame() {
        val initialCard = gamefrag.getRandomCard()
        binding.ivBackImage.setImageResource(initialCard.first)
//        previousCardValue = initialCard.second

        updateScores()
        updateRoundCount()
    }

    private fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        if (player == 1) {
            p1Choice = guessHigher
            p1Guess = true
            val selectedColor = Color.rgb(29,105,29)
            if (guessHigher){
                binding.btnPlayer1Higher.setBackgroundColor(selectedColor)
            }else {
            binding.btnPlayer1Lower.setBackgroundColor(selectedColor)
            }
            binding.btnPlayer1Higher.isEnabled = false
            binding.btnPlayer1Lower.isEnabled = false
        } else {
            p2Choice = guessHigher
            p2Guess = true
            val selectedColor = Color.rgb(29,105,29)
            if (guessHigher){
                binding.btnPlayer2Higher.setBackgroundColor(selectedColor)
            }else {
                binding.btnPlayer2Lower.setBackgroundColor(selectedColor)
            }
            binding.btnPlayer2Higher.isEnabled = false
            binding.btnPlayer2Lower.isEnabled = false
        }
        if (p1Guess && p2Guess) {
            evaluateGuesses()
        }
    }
    private fun updateRoundCount() {
        binding.countRounds.text = "Round $currentRound of $totalRounds"
    }
        private fun evaluateGuesses() {
            val newCard = gamefrag.getRandomCard()
            val newCardDrawable = newCard.first
            val newCardValue = newCard.second

            val p1Correct = gamefrag.isGuessCorrect(newCardValue, p1Choice)
            val p2Correct = gamefrag.isGuessCorrect(newCardValue, p2Choice)

            gamefrag.updateScore(1, p1Correct)
            gamefrag.updateScore(2, p2Correct)


            binding.ivBackImage.setImageResource(newCardDrawable)
            previousCardValue = newCardValue

            resetTurn()
            updateScores()

            currentRound++
            if (currentRound <= totalRounds) {
                updateRoundCount()
            } else {
                endGame()
            }
        }
    private fun endGame() {
        val resultIntent = Intent(this, Result::class.java)
        resultIntent.putExtra("Player1Score", gamefrag.player1score)
        resultIntent.putExtra("Player2Score", gamefrag.player2score)
        startActivity(resultIntent)

        finish()
    }

    private fun resetTurn() {
        p1Guess = false
        p2Guess = false

        binding.btnPlayer1Higher.isEnabled = true
        binding.btnPlayer1Lower.isEnabled = true
        binding.btnPlayer2Higher.isEnabled = true
        binding.btnPlayer2Lower.isEnabled = true

        val defaultColor = Color.rgb(139,16,16)
        binding.btnPlayer1Higher.setBackgroundColor(defaultColor)
        binding.btnPlayer1Lower.setBackgroundColor(defaultColor)
        binding.btnPlayer2Higher.setBackgroundColor(defaultColor)
        binding.btnPlayer2Lower.setBackgroundColor(defaultColor)
    }


    private fun updateScores() {
        binding.player1Score.text = "Player 1 Score: ${gamefrag.player1score}"
        binding.player2Score.text = "Player 2 Score: ${gamefrag.player2score}"
    }
}

