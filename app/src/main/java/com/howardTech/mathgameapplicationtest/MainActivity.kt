package com.howardTech.mathgameapplicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition : Button
    lateinit var subtraction : Button
    lateinit var multiplication : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.buttonAdd)
        subtraction = findViewById(R.id.buttonSub)
        multiplication = findViewById(R.id.buttonMult)

        addition.setOnClickListener {

            val intentAdd = Intent(this@MainActivity, GameAdditionActivity::class.java)
            startActivity(intentAdd)
        }

        subtraction.setOnClickListener {

            val intentSub = Intent(this@MainActivity, GameSubtractionActivity::class.java)
            startActivity(intentSub)
        }

        multiplication.setOnClickListener {

            val intentMult = Intent(this@MainActivity, GameMultiplicationActivity::class.java)
            startActivity(intentMult)

        }
    }
}