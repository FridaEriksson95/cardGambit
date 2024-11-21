package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityGameBoardFriendBinding

class GameBoardFriend : AppCompatActivity() {

    private lateinit var binding: ActivityGameBoardFriendBinding
    val selectedAnswer = arrayOf(0, 1, 2, 3)

    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBoardFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = arrayOf(arrayOf(binding.btnPlayer1Lower, binding.btnPlayer1Higher),
            arrayOf(binding.btnPlayer2Lower, binding.btnPlayer2Higher))

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnPlayer1Lower.setOnClickListener {
                binding.btnPlayer1Lower.setBackgroundColor(Color.rgb(29, 105, 29))
        }
        binding.btnPlayer1Higher.setOnClickListener {
                binding.btnPlayer1Higher.setBackgroundColor(Color.rgb(29, 105, 29))
        }
        binding.btnPlayer2Lower.setOnClickListener {
                binding.btnPlayer2Lower.setBackgroundColor(Color.rgb(29, 105, 29))
            }
        binding.btnPlayer2Higher.setOnClickListener {
                binding.btnPlayer2Higher.setBackgroundColor(Color.rgb(29, 105, 29))
            }
        }
    }

// Fragments och slumpa kort!