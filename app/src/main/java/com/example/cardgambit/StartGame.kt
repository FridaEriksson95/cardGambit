package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityStartGameBinding

class StartGame : AppCompatActivity() {

    private lateinit var binding : ActivityStartGameBinding
    private var gameMode : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAI.setOnClickListener{
            gameMode = "AI"
            binding.btnAI.setBackgroundColor(Color.rgb(139,16,16))
            binding.btnFriend.setBackgroundColor(Color.rgb(139,16,16))

            binding.btnAI.setBackgroundColor(Color.rgb(29, 105, 29))

            binding.radioGroup.visibility = View.VISIBLE
            binding.btnLetsPlay.visibility = View.VISIBLE
        }
        binding.btnFriend.setOnClickListener{
            gameMode = "Friend"
            binding.btnAI.setBackgroundColor(Color.rgb(139,16,16))
            binding.btnFriend.setBackgroundColor(Color.rgb(139,16,16))

            binding.btnFriend.setBackgroundColor(Color.rgb(29, 105, 29))

            binding.radioGroup.visibility = View.VISIBLE
            binding.btnLetsPlay.visibility = View.VISIBLE
        }
        binding.btnLetsPlay.setOnClickListener {
            val selectedOptionId = binding.radioGroup.checkedRadioButtonId

            if(selectedOptionId == -1) {
                Toast.makeText(this, "Choose amount of rounds", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val roundsText = selectedRadioButton.text.toString()

                val rounds = roundsText.filter { it.isDigit() }.toIntOrNull()

                if(rounds != null) {
                    if (gameMode == "Friend") {
                        val friendIntent = Intent(this, GameBoardFriend::class.java)
                        friendIntent.putExtra("ROUNDS", rounds)
                        startActivity(friendIntent)
                    }else if (gameMode == "AI") {
                    val aiIntent = Intent(this, GameBoardAI::class.java)
                    aiIntent.putExtra("ROUNDS", rounds)
                    startActivity(aiIntent)
                } else {
                    Toast.makeText(this, "Choose game mode (Computer or Friend)", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}