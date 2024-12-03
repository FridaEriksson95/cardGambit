package com.example.cardgambit

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityResultBinding

//Result class that keeps track of winner in amount of rounds and sets functions to restart and quit buttons
class Result : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Sets the value for key "Winner"
        val winner = intent.getStringExtra("Winner") ?: "No winner"

        // To display winner in textview at the end of the game
        val winnerTextView : TextView = binding.tvResult
        winnerTextView.text = "$winner"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Restart button clicklistener, so game restarts when pressed
        binding.btnRestartgame.setOnClickListener{
            val newIntent = Intent(this, StartGame::class.java)
            startActivity(newIntent)
        }

        //Quit button clicklistener, so game ends when pressed
        binding.btnQuit.setOnClickListener{
            val newIntent = Intent(this, FrontPage::class.java)
            startActivity(newIntent)
        }
    }
}