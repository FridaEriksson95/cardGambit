package com.example.cardgambit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class GameFragment : Fragment() {

    val cardsMap = mapOf(
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
        R.drawable.thirteen to 13,
        R.drawable.one to 1
    )

   // val randomCard = cardsMap.keys.random() // Slumpa fram en drawable
   // val cardValue = cardsMap[randomCard]   // Hämta det numeriska värdet


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
}