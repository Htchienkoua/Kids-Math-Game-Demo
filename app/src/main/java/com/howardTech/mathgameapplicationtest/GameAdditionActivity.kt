package com.howardTech.mathgameapplicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class GameAdditionActivity : AppCompatActivity() {

    private lateinit var textScore : TextView
    private lateinit var textLife : TextView
    private lateinit var textTime : TextView

    private lateinit var textQuestion : TextView
    private lateinit var editTextAnswer : EditText

    private lateinit var buttonOk : Button
    private lateinit var buttonNext : Button

    //to set the initial value of the answer to 0 so we can change it later after the operation
    private var correctAnswer = 0

    //also to set the initial value for the userScore
    private var userScore = 0

    //the initial value for the userLife
    private var userLife = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_addition)

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion   = findViewById(R.id.TextViewQuestion)

        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.ButtonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()   //to continue the gameActivity at the start of the OnCreate method



        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()

            //if the user clicks Ok without writing an answer to avoid the app from crashing
            if(input == ""){
                Toast.makeText(applicationContext,"Please write an answer or click the next button!", Toast.LENGTH_LONG).show()
            }
            else{
                val userAnswer = input.toInt()

                if(userAnswer == correctAnswer){
                    userScore = userScore + 10   //to add 10 points to the userSCore
                    textQuestion.text = "Congratulations , your answer is correct!" //to congratulate the user
                    textScore.text = userScore.toString() //to display the updated score to the user interface

                }else{
                    userLife--  //to decrement the userLife by 1 i.e 'userLife = userLife - 1'  is same as 'userLife--'
                    textQuestion.text = "Sorry , your answer is wrong and you have lost 1 life!"
                    textLife.text = userLife.toString() //to update the decreased life points to the userInterface

                }
            }
        }

        buttonNext.setOnClickListener {


        }
    }

    fun gameContinue(){

        //to generate two random integer numbers for the operation input fields while setting boundaries
        val number1 : Int = Random.nextInt(0,100)
        val number2 : Int = Random.nextInt(0,100)

        textQuestion.text = "$number1 + $number2"

        correctAnswer = number1 + number2
    }
}