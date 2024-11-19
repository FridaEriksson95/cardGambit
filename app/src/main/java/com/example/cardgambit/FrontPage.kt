package com.example.cardgambit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityFrontPageBinding

class FrontPage : AppCompatActivity() {

    private lateinit var binding : ActivityFrontPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityFrontPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnStartgame.setOnClickListener{
            val newIntent = Intent(this, StartGame::class.java)
            startActivity(newIntent)
        }
        binding.btnInstructions.setOnClickListener{
            val newIntent = Intent(this, Instructions::class.java)
            startActivity(newIntent)
        }
    }
}