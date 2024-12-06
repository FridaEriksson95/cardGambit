package com.example.cardgambit

import androidx.fragment.app.Fragment

//GameFragment that handles the cards in the game
class GameFragment : Fragment() {

    //map of the playing cards
    val cardsMap = mapOf(
        R.drawable.one to 1,
        R.drawable.two to 2,
        R.drawable.three to 3,
        R.drawable.four to 4,
        R.drawable.five to 5,
        R.drawable.six to 6,
        R.drawable.seven to 7,
        R.drawable.eight to 8,
        R.drawable.nine to 9,
        R.drawable.ten to 10,
        R.drawable.eleven to 11,
        R.drawable.twelve to 12,
        R.drawable.thirteen to 13
    )

    private var previousCard : Int? = null
    var previousCardValue: Int = getRandomCardValue()
    var player1score = 0
    var player2score = 0

    //Randomize a card during each round, checks so same card don't display again directly in new round
    fun getRandomCard(): Pair<Int, Int> {
        var randomCard :Int
        do {
            randomCard = cardsMap.keys.random()
        } while (randomCard == previousCard)

        previousCard = randomCard
        return Pair(randomCard, cardsMap[randomCard]!!)
    }

    //Checks the value of the card
    private fun getRandomCardValue(): Int {
        return cardsMap.values.random()
    }

    //Function to check if player guessed correct based on cardvalue and guess
    fun isGuessCorrect(newCardValue: Int, guessHigher: Boolean): Boolean {
        return if (guessHigher) {
            newCardValue > previousCardValue
        } else {
            newCardValue < previousCardValue
//            return when {
//                newCardValue == previousCardValue -> false
//                guessHigher -> newCardValue > previousCardValue
//                else -> newCardValue < previousCardValue
            }
        }

    //Function to update the scores
    fun updateScore(player1Correct : Boolean, player2Correct: Boolean) {
        if (player1Correct) player1score++
        if (player2Correct) player2score++
        }
    }