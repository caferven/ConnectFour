package com.example.connectfour

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.connectfour.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity(){

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val personalGithubUri = "https://github.com/caferven"
        val challengeGithubUri = "https://github.com/mouredev/Monthly-App-Challenge-2022"

        binding.appInfo.text = "First monthly app created by caferven"
        binding.otherInfo.text = "Challenge by Brais Moure (MoureDev)"
        binding.btnChallenge.text = "Take the challenge"
        binding.creditsInfo.movementMethod = LinkMovementMethod.getInstance()

        binding.btnGithub.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(personalGithubUri)))
        }

        binding.btnChallenge.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(challengeGithubUri)))
        }
    }
}