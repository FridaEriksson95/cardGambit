package com.example.cardgambit


class GameBoardAI : GameBoardFriend() {

    override fun handlePlayerGuess(player: Int, guessHigher: Boolean) {
        if (player == 1) {
            // Player 1 makes their choice manually
            super.handlePlayerGuess(player, guessHigher)

            // Trigger AI's move automatically
            if (p1Guess) {
                val aiChoice = calculateAIMove()
                super.handlePlayerGuess(2, aiChoice)
            }
        }
    }

    private fun calculateAIMove(): Boolean {
        // Simple AI logic: Random higher or lower
        return (0..1).random() == 1
    }
}
