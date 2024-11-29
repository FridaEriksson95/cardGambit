package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityGameBoardFriendBinding
import java.lang.Math.random

open class GameBoardFriend : AppCompatActivity() {

    protected lateinit var binding: ActivityGameBoardFriendBinding
    protected lateinit var gamefrag : GameFragment

    protected var p1Guess: Boolean = false
    protected var p2Guess: Boolean = false

    protected var p1Choice: Boolean = false
    protected var p2Choice: Boolean = false

    var previousCardValue = 0
    protected var currentRound = 1
    protected var totalRounds: Int = 0
    protected var isAI: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBoardFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gamefrag = GameFragment()

        isAI = intent.getBooleanExtra("isAI", false)
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

    protected open fun setupGame() {
        val initialCard = gamefrag.getRandomCard()
        binding.ivBackImage.setImageResource(initialCard.first)

        updateScores()
        updateRoundCount()
    }

    protected open fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
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

    protected fun updateRoundCount() {
        binding.countRounds.text = "Round $currentRound of $totalRounds"
    }

    protected fun evaluateGuesses() {
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

            val handler = Handler(Looper.getMainLooper())
            currentRound++
            if (currentRound <= totalRounds) {
                updateRoundCount()
            } else {
                handler.postDelayed({ // valde handler för att ha bättre rendering med fragment
                    endGame()         // byten efter rundorna är klara
                }, 2000)
            }
        }

    protected open fun endGame() {
        val resultIntent = Intent(this, Result::class.java)
        resultIntent.putExtra("Player1Score", gamefrag.player1score)
        resultIntent.putExtra("Player2Score", gamefrag.player2score)

        val winner = when {
            gamefrag.player1score > gamefrag.player2score -> "Player 1"
            gamefrag.player2score > gamefrag.player1score -> {
                if (isAI) "AI" else "Player 2"
            }
            else -> "It's a draw!"
        }
        resultIntent.putExtra("Winner", winner)
        startActivity(resultIntent)

        finish()
    }

    protected fun resetTurn() {
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

    protected fun updateScores() {
        binding.player1Score.text = "Player 1 Score: ${gamefrag.player1score}"
        binding.player2Score.text = "Player 2 Score: ${gamefrag.player2score}"
    }
}