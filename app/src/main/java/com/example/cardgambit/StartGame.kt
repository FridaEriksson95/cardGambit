package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityStartGameBinding

//Class where player get to choose who to play against and choose amount of rounds
class StartGame : AppCompatActivity() {

    private lateinit var binding : ActivityStartGameBinding
    //Variables to check is friend or AI is choosen
    private var gameMode : String? = null
    var isAI = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //If against AI is clicked it highlights in green and radiobutton displays
        binding.btnAI.setOnClickListener{
            gameMode = "AI"
            isAI = true
            binding.btnAI.setBackgroundColor(Color.rgb(139,16,16))
            binding.btnFriend.setBackgroundColor(Color.rgb(139,16,16))

            binding.btnAI.setBackgroundColor(Color.rgb(29, 105, 29))

            binding.radioGroup.visibility = View.VISIBLE
            binding.btnLetsPlay.visibility = View.VISIBLE
        }
        //If against a friend is clicked it highlights in green and radiobuttons display
        binding.btnFriend.setOnClickListener{
            gameMode = "Friend"
            binding.btnAI.setBackgroundColor(Color.rgb(139,16,16))
            binding.btnFriend.setBackgroundColor(Color.rgb(139,16,16))

            binding.btnFriend.setBackgroundColor(Color.rgb(29, 105, 29))

            binding.radioGroup.visibility = View.VISIBLE
            binding.btnLetsPlay.visibility = View.VISIBLE
        }
        //When playermode and amount of rounds is chosed, lets play button will start the choosen options
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
                        val friendIntent = Intent(this, GameBoard::class.java)
                        friendIntent.putExtra("ROUNDS", rounds)
                        friendIntent.putExtra("IsAI", isAI)
                        startActivity(friendIntent)
                    }else if (gameMode == "AI") {
                    val aiIntent = Intent(this, GameBoardAI::class.java)
                    aiIntent.putExtra("ROUNDS", rounds)
                        aiIntent.putExtra("IsAI", isAI)
                    startActivity(aiIntent)
                } else {
                    Toast.makeText(this, "Choose gamemode (AI or Friend)", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}