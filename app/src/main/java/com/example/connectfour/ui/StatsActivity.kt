package com.example.connectfour.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.connectfour.databinding.ActivityStatsBinding

class StatsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding

    companion object {
        var redStatsWins = 0
        var purpleStatsWins = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.redGameWins.text = "Wins: $redStatsWins"
        binding.purpleGameWins.text = "Wins: $purpleStatsWins"
    }
}