package com.howardTech.mathgameapplicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
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

    lateinit var timer : CountDownTimer
    private val startTimerInMillis : Long = 60000 //the amount of seconds of 60 seconds in milliseconds (by default 1s = 1000ms)
    var timeLeftInMillis : Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_addition)

        supportActionBar!!.title = "Addition" //to update the title of the action bar to the text in quotes

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
                //to pause the timer whenever the user wants to check the answer, whether it is correct or false
                //before the if - else check for the answer
                pauseTimer()


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

            //to pause and reset the timer whenever the next button is checked to
            pauseTimer()
            resetTimer()

            gameContinue()

            editTextAnswer.setText("") //to clear the answer of the editeText answer field when updating the question


            if(userLife == 0){

                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameAdditionActivity, ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()

            }else{
                gameContinue()

            }
        }
    }

    private fun gameContinue(){

        //to generate two random integer numbers for the operation input fields while setting boundaries
        val number1 : Int = Random.nextInt(0,100)
        val number2 : Int = Random.nextInt(0,100)

        textQuestion.text = "$number1 + $number2"

        correctAnswer = number1 + number2

        startTimer() //to start the time when the game is ongoing
    }

    private fun startTimer(){
        timer = object : CountDownTimer( timeLeftInMillis, 1000) //the two parameters are the time to countdown and the interval for the countdown of 1s
        {
            //created from implementing the members of the 'object' keyword type for the abstract class

            //what the timer will do when the countdown is reducing progressively
            override fun onTick(millisUntilFinished: Long) {

                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            //what the timer will do when the countdown is finish
            override fun onFinish() {

                pauseTimer()
                resetTimer()
                updateText()

                userLife --
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, your time is up !"
            }

        }.start()
    }

    private fun updateText(){

        val remainingTime : Int = (timeLeftInMillis / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d",remainingTime) //the format for the remaining time

    }

    private fun pauseTimer(){

        timer.cancel()
    }

    private fun resetTimer(){

        timeLeftInMillis = startTimerInMillis //to set back the timer to 60 secs
        updateText()
    }
}