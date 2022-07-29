package com.example.connectfour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectfour.databinding.ActivityStatsBinding

class StatsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}