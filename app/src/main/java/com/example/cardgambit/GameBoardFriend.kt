package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityGameBoardFriendBinding
import java.lang.Math.random
import kotlin.random.Random

class GameBoardFriend : AppCompatActivity() {

    private lateinit var binding: ActivityGameBoardFriendBinding
    val gamefrag = GameFragment()
    lateinit var random:Random
    lateinit var player1_score : EditText
    lateinit var player2_score : EditText

    lateinit var iv_backImage : ImageView
    var player1Score = 0
    var player2Score = 0
    var previousCardValue = gamefrag.cardsMap.keys.random().let {gamefrag.cardsMap[it]!! }

    // ----------------------------onCreate----------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBoardFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)


        random = Random
        iv_backImage = findViewById(R.id.iv_backImage)
        iv_backImage.setImageResource(R.drawable.backsidecard)
        val cardBack = random.nextInt(gamefrag.cardsMap.size)
        player1_score = findViewById(R.id.player1_score)
        player2_score = findViewById(R.id.player2_score)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Buttons
        binding.btnPlayer1Higher.setOnClickListener {
            val newCard = gamefrag.cardsMap.keys.random()
            val newCardValue = gamefrag.cardsMap[newCard]!!

            if (newCardValue > previousCardValue) {
                player1Score++
                binding.btnPlayer1Higher.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer1Higher.setBackgroundColor(Color.RED)
            }

            previousCardValue = newCardValue
            binding.ivBackImage.setImageResource(newCard)
        }

        binding.btnPlayer1Lower.setOnClickListener {
            val newCard = gamefrag.cardsMap.keys.random()
            val newCardValue = gamefrag.cardsMap[newCard]!!

            if (newCardValue < previousCardValue) {
                player1Score++
                binding.btnPlayer1Lower.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer1Lower.setBackgroundColor(Color.RED)
            }

            previousCardValue = newCardValue
            binding.ivBackImage.setImageResource(newCard)
        }
        // Buttons
        binding.btnPlayer2Higher.setOnClickListener {
            val newCard = gamefrag.cardsMap.keys.random()
            val newCardValue = gamefrag.cardsMap[newCard]!!

            if (newCardValue > previousCardValue) {
                player2Score++
                binding.btnPlayer2Higher.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer2Higher.setBackgroundColor(Color.RED)
            }

            previousCardValue = newCardValue
            binding.ivBackImage.setImageResource(newCard)
        }

        binding.btnPlayer2Lower.setOnClickListener {
            val newCard = gamefrag.cardsMap.keys.random()
            val newCardValue = gamefrag.cardsMap[newCard]!!

            if (newCardValue < previousCardValue) {
                player2Score++
                binding.btnPlayer2Lower.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer2Lower.setBackgroundColor(Color.RED)
            }

            previousCardValue = newCardValue
            binding.ivBackImage.setImageResource(newCard)
        }
        }
}


// Fragments och slumpa kort!