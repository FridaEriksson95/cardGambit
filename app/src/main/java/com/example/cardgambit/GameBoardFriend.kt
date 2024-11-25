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
import kotlin.random.Random

class GameBoardFriend : AppCompatActivity() {

    private lateinit var binding: ActivityGameBoardFriendBinding
    private lateinit var gamefrag : GameFragment

    var previousCardValue = 0

    private var p1Guess = false
    private var p2Guess = false

    private var p1Choice: Boolean? = null
    private var p2Choice: Boolean? = null

    // ----------------------------onCreate----------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBoardFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gamefrag = GameFragment()
        binding.player1Score.text = "Player 1 Score: 0"
        binding.player2Score.text = "Player 2 Score: 0"

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
    }

    private fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        if (player == 1) {
            p1Choice = guessHigher
            p1Guess = true
        } else if (player == 2) {
            p2Choice = guessHigher
            p2Guess = true
        }
        if (p1Guess && p2Guess) {
            val newCard = gamefrag.getRandomCard()
            val newCardDrawable = newCard.first
            val newCardValue = newCard.second

            val isP1Correct = gamefrag.isGuessCorrect(newCardValue, p1Choice!!)
            val isP2Correct = gamefrag.isGuessCorrect(newCardValue, p2Choice!!)

            gamefrag.updateScore(1, isP1Correct)
            gamefrag.updateScore(2, isP2Correct)

            binding.ivBackImage.setImageResource(newCardDrawable)
            previousCardValue = newCardValue
            updateScores()
            p1Guess = false
            p2Guess = false
            p1Choice = null
            p2Choice = null

        }
    }

    private fun updateScores() {
        binding.player1Score.text = "Player 1 Score: ${gamefrag.player1score}"
        binding.player2Score.text = "Player 2 Score: ${gamefrag.player2score}"
    }
}



// Fragments och slumpa kort!