package com.example.connectfour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectfour.databinding.ActivityConnectFourBinding

class ConnectFourActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConnectFourBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectFourBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}