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
import com.example.cardgambit.databinding.ActivityGameBoardAiBinding
import java.lang.Math.random

class GameBoardAI : GameBoardFriend() {

    private lateinit var binding: ActivityGameBoardAiBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBoardAiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAIGame()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun setupAIGame() {
        super.setupGame()
        binding.btnPlayer2AIHigher.isEnabled = false
        binding.btnPlayer2AILower.isEnabled = false
    }

    override fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        if (player == 1) {
            super.handlePlayerGuess(player, guessHigher)
        }else {
            val aiGuessHigher = calculateAIMove()
            super.handlePlayerGuess(player, aiGuessHigher)
        }
    }
    private fun calculateAIMove(): Boolean {
        return (0..1).random() == 1
    }

    override fun endGame() {
        super.endGame()
    }
}