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
    val options = arrayOf(arrayOf(binding.btnPlayer1Lower, binding.btnPlayer1Higher),
        arrayOf(binding.btnPlayer2Lower, binding.btnPlayer2Higher))

    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityGameBoardFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnPlayer1Lower.setOnClickListener {
            if (currentIndex == 0) {
                binding.btnPlayer1Lower.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer1Lower.setBackgroundColor(Color.RED)
            }
        }
        binding.btnPlayer1Higher.setOnClickListener {
            if (currentIndex == 1) {
                binding.btnPlayer1Higher.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer1Higher.setBackgroundColor(Color.RED)
            }
        }
        binding.btnPlayer2Lower.setOnClickListener {
            if (currentIndex == 2) {
                binding.btnPlayer2Lower.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer2Lower.setBackgroundColor(Color.RED)
            }
        }
        binding.btnPlayer2Higher.setOnClickListener {
            if (currentIndex == 3) {
                binding.btnPlayer2Higher.setBackgroundColor(Color.GREEN)
            } else {
                binding.btnPlayer2Higher.setBackgroundColor(Color.RED)
            }
        }
    }
}






// Fragments och slumpa kort!