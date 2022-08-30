package com.example.connectfour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.connectfour.NewGameActivity.Companion.purpleNumWins
import com.example.connectfour.NewGameActivity.Companion.redNumWins
import com.example.connectfour.databinding.ActivityStatsBinding

class StatsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.redGameWins.text = "Wins: $redNumWins"
        binding.purpleGameWins.text = "Wins: $purpleNumWins"
    }
}