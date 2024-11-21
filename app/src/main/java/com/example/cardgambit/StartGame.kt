package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.os.Bundle
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
        binding.btnAI.setBackgroundColor(Color.rgb(29, 105, 29))

            binding.radioGroup.visibility = View.VISIBLE
            binding.btnLetsPlay.visibility = View.VISIBLE
        }
        binding.btnFriend.setOnClickListener{
            binding.btnFriend.setBackgroundColor(Color.rgb(29, 105, 29))

            binding.radioGroup.visibility = View.VISIBLE
            binding.btnLetsPlay.visibility = View.VISIBLE
        }
        binding.btnLetsPlay.setOnClickListener {
            val selectedOptionId = binding.radioGroup.checkedRadioButtonId

            if(selectedOptionId == -1) {
                Toast.makeText(this, "Choose amount of rounds", Toast.LENGTH_SHORT).show()
            }else{
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val rounds = selectedRadioButton.text.toString()

                Toast.makeText(this, "You choose $rounds rounds", Toast.LENGTH_SHORT).show()
                val newIntent = Intent(this, GameBoardFriend::class.java)
                startActivity(newIntent)
            }
        }
    }
}