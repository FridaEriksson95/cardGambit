package com.example.cardgambit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityInstructionsBinding

class Instructions : AppCompatActivity() {

    private lateinit var binding : ActivityInstructionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityInstructionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvInstructions.text = "När du startar spelet kommer \n " +
                "ett random kort att dras. \n" +
                "Om du spelar mot en vän ska \n" +
                "ni båda gissa om kortet som \n" +
                "vänds upp efter blir \n" +
                "högre eller lägre än föregående \n" +
                "kort. Den som gissar rätt får \n" +
                "poäng, om ni båda gissar rätt, \n" +
                "får båda poäng. Mest rätt efter \n" +
                "antal valda rundor - vinner!"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnStartgame.setOnClickListener{
            val newIntent = Intent(this, StartGame::class.java)
            startActivity(newIntent)
        }
    }
}