package com.example.cardgambit

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityResultBinding

class Result : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnRestartgame.setOnClickListener{
            val newIntent = Intent(this, StartGame::class.java)
            startActivity(newIntent)
        }

        binding.btnQuit.setOnClickListener{
            val newIntent = Intent(this, FrontPage::class.java)
            startActivity(newIntent)
        }
    }
}