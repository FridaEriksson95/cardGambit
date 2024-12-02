package com.example.cardgambit

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgambit.databinding.ActivityGameBoardBinding

//The Gameboard
open class GameBoard : AppCompatActivity() {

    protected lateinit var binding: ActivityGameBoardBinding
    protected lateinit var gamefrag : GameFragment

    //Variables to indicate players guess
    protected var p1Guess: Boolean = false
    protected var p2Guess: Boolean = false

    //Variables to indicate players choise
    protected var p1Choice: Boolean = false
    protected var p2Choice: Boolean = false

    //Cardvalue to compare for each round
    protected var previousCardValue = 0
    //Actual round
    protected var currentRound = 1
    //Total rounds for that game
    protected var totalRounds: Int = 0
    //Flag to indicate if player is Ai or Friend
    protected var isAI: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get functions and cards from fragment
        gamefrag = GameFragment()

        //Intens to check if player is AI and to get amount of rounds
        isAI = intent.getBooleanExtra("IsAI", false)
        totalRounds = intent.getIntExtra("ROUNDS", 1)

        //Starts the game
        setupGame()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*Sets clicklisteners for players button of "higher" or "lower". When button is pressed
        handleplayerguess calls with players number and choise*/
        binding.btnPlayer1Higher.setOnClickListener { handlePlayerGuess(1, true) }
        binding.btnPlayer1Lower.setOnClickListener { handlePlayerGuess(1, false) }
        binding.btnPlayer2Higher.setOnClickListener { handlePlayerGuess(2, true) }
        binding.btnPlayer2Lower.setOnClickListener { handlePlayerGuess(2, false) }
    }

    //Function to start up the game and display gamecard
    protected open fun setupGame() {
        val initialCard = gamefrag.getRandomCard()
        binding.ivBackImage.setImageResource(initialCard.first)
        gamefrag.previousCardValue = initialCard.second

        updateScores()
        updateRoundCount()
    }

    //Handle players guesses and set buttoncolors for their answers
    protected open fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        if (player == 1) {
            p1Choice = guessHigher
            p1Guess = true
            val selectedColor = Color.rgb(29,105,29)
            if (guessHigher){
                binding.btnPlayer1Higher.setBackgroundColor(selectedColor)
            }else {
            binding.btnPlayer1Lower.setBackgroundColor(selectedColor)
            }
            binding.btnPlayer1Higher.isEnabled = false
            binding.btnPlayer1Lower.isEnabled = false
        } else {
            p2Choice = guessHigher
            p2Guess = true
            val selectedColor = Color.rgb(29,105,29)
            if (guessHigher){
                binding.btnPlayer2Higher.setBackgroundColor(selectedColor)
            }else {
                binding.btnPlayer2Lower.setBackgroundColor(selectedColor)
            }
            binding.btnPlayer2Higher.isEnabled = false
            binding.btnPlayer2Lower.isEnabled = false
        }
        if (p1Guess && p2Guess) {
            evaluateGuesses()
        }
    }

    //Updates round textview based on amount of rounds choosen in startgame
    protected fun updateRoundCount() {
        binding.countRounds.text = "Round $currentRound of $totalRounds"
    }

    //Evaluates players guesses and updates their score based on if the answer was correct or not
    protected fun evaluateGuesses() {
        //Draw new card
            val newCard = gamefrag.getRandomCard()
            val newCardDrawable = newCard.first
            val newCardValue = newCard.second

        //Check guesses with previous cardvalue
            val p1Correct = gamefrag.isGuessCorrect(newCardValue, p1Choice)
            val p2Correct = gamefrag.isGuessCorrect(newCardValue, p2Choice)

        //Update scores
            gamefrag.updateScore(1, p1Correct)
            gamefrag.updateScore(2, p2Correct)

        //Show new card
            binding.ivBackImage.setImageResource(newCardDrawable)
            previousCardValue = newCardValue


            updateScores()
            resetTurn()

            currentRound++
            if (currentRound <= totalRounds) {
                updateRoundCount()
            } else {
                endGame()
            }
    }

    /*Function for when game is over, takes the total score and displays who wins in resultactivity
     Based on if player chose to play against friend or ai either Player 2 or AI displays if won.
     */
    protected open fun endGame() {
        //Delayhandler for 1 sek so player is able to see the answer before game ends
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val resultIntent = Intent(this, Result::class.java)
            resultIntent.putExtra("Player1Score", gamefrag.player1score)
            resultIntent.putExtra("Player2Score", gamefrag.player2score)

            //Display winner in result textview
            val winner = when {
                gamefrag.player1score > gamefrag.player2score -> "Player 1"
                gamefrag.player2score > gamefrag.player1score -> {
                    if (isAI) "AI" else "Player 2"
                }
                else -> "It's a draw!"
            }
        resultIntent.putExtra("Winner", winner)
        startActivity(resultIntent)
            finish()
        },1000)
    }

    //Resets the colors of players buttons on each round and make sure they can only press one button
    protected fun resetTurn() {
        //Delayhandler for 1 sek so player is able to see the answer before new round
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            p1Guess = false
            p2Guess = false

            binding.btnPlayer1Higher.isEnabled = true
            binding.btnPlayer1Lower.isEnabled = true
            binding.btnPlayer2Higher.isEnabled = true
            binding.btnPlayer2Lower.isEnabled = true

            val defaultColor = Color.rgb(139, 16, 16)
            binding.btnPlayer1Higher.setBackgroundColor(defaultColor)
            binding.btnPlayer1Lower.setBackgroundColor(defaultColor)
            binding.btnPlayer2Higher.setBackgroundColor(defaultColor)
            binding.btnPlayer2Lower.setBackgroundColor(defaultColor)

            if(isAI){
                binding.btnPlayer2AIHigher.setBackgroundColor(defaultColor)
                binding.btnPlayer2AILower.setBackgroundColor(defaultColor)
                binding.btnPlayer2AIHigher.isEnabled = true
                binding.btnPlayer2AILower.isEnabled = true
            }
        },1000)
    }

    //Updates the score textviews for each player
    protected fun updateScores() {
        binding.player1Score.text = "Player 1 Score: ${gamefrag.player1score}"
        binding.player2Score.text = "Player 2 Score: ${gamefrag.player2score}"
    }
}