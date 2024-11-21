package com.example.cardgambit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityGameBoardFriendBinding

class GameBoardFriend : AppCompatActivity() {

    private lateinit var binding : ActivityGameBoardFriendBinding

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
        binding.btnPlayer1Lower.setOnClickListener{

        }
        binding.btnPlayer2Lower.setOnClickListener{

        }
        binding.btnPlayer1Higher.setOnClickListener{

        }
        binding.btnPlayer2Higher.setOnClickListener{

        }
    }
}


// Fragments och slumpa kort!