package com.howardTech.mathgameapplicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class ResultActivity : AppCompatActivity() {


    lateinit var result : TextView
    lateinit var playAgain : Button
    lateinit var exit : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result = findViewById(R.id.result)
        playAgain = findViewById(R.id.playAgain)
        exit = findViewById(R.id.exit)

        val score = intent.getIntExtra("score",0) //to get the score from the previous activity
        result.text = "Your score : $score "

        playAgain.setOnClickListener {

            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        exit.setOnClickListener {

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}