package com.falanper.jogodostimes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.falanper.jogodostimes.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(Constants.USERNAME) + " !!"

        binding.textViewPlayerName.text = username
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        binding.textViewScore.text = "Sua pontuação foi $correctAnswers de um total de $totalQuestions"

        binding.btnPlayAgain.setOnClickListener {
         val intent = Intent(this@ResultActivity, MainActivity::class.java)
             startActivity(intent)
            finish()
        }

        binding.btnFinish.setOnClickListener {
            finish()
        }
    }
}