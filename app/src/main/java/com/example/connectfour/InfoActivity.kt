package com.example.connectfour

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectfour.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity(){

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personalGithubUri = "https://github.com/caferven"
        val challengeGithubUri = "https://github.com/mouredev/Monthly-App-Challenge-2022"

        binding.appInfo.setText("First monthly app created by caferven")
        binding.otherInfo.setText("Challenge by Brais Moure (MoureDev)")
        binding.btnChallenge.setText("Take the challenge")

        binding.btnGithub.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(personalGithubUri)))
        }

        binding.btnChallenge.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(challengeGithubUri)))
        }
    }
}