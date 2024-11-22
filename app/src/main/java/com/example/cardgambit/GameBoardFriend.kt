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
    lateinit var random:Random

    lateinit var player1_score : TextView
    lateinit var player2_score : TextView

    lateinit var iv_backImage : ImageView
    var player1Score = 0
    var player2Score = 0
    var previousCardValue = 0

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

//        random = Random
//        iv_backImage = findViewById(R.id.iv_backImage)
//        iv_backImage.setImageResource(R.drawable.backsidecard)
//        val cardBack = random.nextInt(gamefrag.cardsMap.size)
//        player1_score = findViewById(R.id.player1_score)
//        player2_score = findViewById(R.id.player2_score)


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
        previousCardValue = initialCard.second

        updateScores()
    }

    private fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        val newCard = gamefrag.getRandomCard()
        val newCardDrawable = newCard.first
        val newCardValue = newCard.second

        val isCorrect = gamefrag.isGuessCorrect(newCardValue, guessHigher)
        gamefrag.updateScore(player, isCorrect)

        updateButtonColors(player, guessHigher, isCorrect)
        binding.ivBackImage.setImageResource(newCardDrawable)

        previousCardValue = newCardValue
        updateScores()
    }

    private fun updateButtonColors(player: Int, guessHigher: Boolean, isCorrect: Boolean) {
        val correctColor = Color.GREEN
        val wrongColor = Color.RED

        if(player == 1) {
            if (guessHigher) {
                binding.btnPlayer1Higher.setBackgroundColor(if (isCorrect) correctColor else wrongColor)
            } else {
                binding.btnPlayer1Lower.setBackgroundColor(if (isCorrect) correctColor else wrongColor)
            }
        } else {
            if (guessHigher) {
                binding.btnPlayer2Higher.setBackgroundColor(if (isCorrect) correctColor else wrongColor)
            } else {
                binding.btnPlayer2Lower.setBackgroundColor(if (isCorrect) correctColor else wrongColor)
            }
        }
    }
    private fun updateScores() {
        binding.player1Score.text = "Player 1 Score: ${gamefrag.player1score}"
        binding.player2Score.text = "Player 2 Score: ${gamefrag.player2score}"
    }
}



// Fragments och slumpa kort!