package com.wanchana.guessnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var editTextNumber: EditText
    lateinit var  infoView : TextView
    lateinit var  resetButton: ImageButton
    lateinit var  checkButton: ImageButton
    lateinit var score: TextView
    lateinit var attempted: TextView

    var randomed: Int = nextInt(0,1000)
    var attempted_number: Int = 0
    var scored: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        infoView = findViewById(R.id.infoView)
        resetButton = findViewById(R.id.resetButton)
        checkButton = findViewById(R.id.checkButton)
        score = findViewById(R.id.score)
        attempted = findViewById(R.id.attempted)
        score.text = "Not yet start"
        infoView.text = "Guess number between 0 and 1000!"

        checkButton.setOnClickListener {
            var number: Int = -1
            attempted_number+=1
            try{
                number = editTextNumber.text.toString().toInt()
            }catch (nfe: NumberFormatException){ }
            score.text = scored.toString()
            if ((number > 1000) or (number < 0)){
                Toast.makeText(this,"Please provide number between 0-1000!",Toast.LENGTH_LONG).show()
                editTextNumber.text.clear()
            }
            else if (number < randomed){
                infoView.text = "Wrong, your guess is too low!"
                editTextNumber.text.clear()
            }else if (number > randomed){
                infoView.text = "Wrong, your guess is too high!"
                editTextNumber.text.clear()
            }else{
                infoView.text = "YOU WON! In $attempted_number times\n The number is $randomed! \n Next challenge await 0 - 1000 Enter it."
                Toast.makeText(this,"Congrats YOU WON!",Toast.LENGTH_SHORT).show()
                editTextNumber.text.clear()
                scored += 1
                score.text = scored.toString()
                attempted_number = 0
                randomed = nextInt(0,1000)
            }
            attempted.text = attempted_number.toString()
        }
        resetButton.setOnClickListener{
            randomed = nextInt(1,1000)
            score.text = "Not yet start"
            infoView.text = "Guess number between 0 and 1000!"
            editTextNumber.text.clear()
            scored = 0
            attempted_number = 0
            attempted.text = attempted_number.toString()
        }

    }
}