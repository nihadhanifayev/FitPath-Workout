package com.example.fitpath

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.ExerciseAdapter
import com.example.fitpath.classes.Exercise
import com.example.fitpath.databinding.ActivityTrainingBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.min

class ActivityTraining : AppCompatActivity() {
    private lateinit var design:ActivityTrainingBinding
    private lateinit var adapter:ExerciseAdapter
    private lateinit var exercises:ArrayList<Exercise>
    private lateinit var timer:CountDownTimer
    private var second = 0
    private var minute = 0
    private var hour = 0
    private var timer_status = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(design.root)

        exercises = ArrayList<Exercise>()
        adapter = ExerciseAdapter(this,exercises)
        design.TrainingRV.setHasFixedSize(true)
        design.TrainingRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        design.TrainingRV.adapter = adapter

        timer = object:CountDownTimer(14400000,1000){
            override fun onTick(millisUntilFinished: Long) {
                if (second<10){
                    design.textViewSecond.setText(": 0$second")
                } else if(second>=10){
                    design.textViewSecond.setText(": $second")
                }
                if (minute<10){
                    design.textViewMinute.setText(": 0$minute")
                }else if(minute>=10){
                    design.textViewMinute.setText(": $minute")
                }
                if (second<=59){
                    second+=1
                }else if (second == 60){
                    second = 0
                    minute += 1
                }
                if (minute==60){
                    minute = 0
                    hour+=1
                    design.textViewHour.setText("0$hour")
                }
            }
            override fun onFinish() {}
        }

        design.buttonTraining.setOnClickListener {
            if (timer_status==false){
                timer_status = true
                design.buttonTraining.setBackgroundColor(Color.RED)
                design.buttonTraining.setText("Stop Training")
                timer.start()
            }else{
                timer_status = false
                design.buttonTraining.setBackgroundColor(resources.getColor(R.color.PrimaryFife))
                design.buttonTraining.setText("Start Training")
                timer.cancel()
                second = 0
                minute = 0
                hour = 0
            }

        }

        design.fabAddExercise.setOnClickListener {
            val alertD = AlertDialog.Builder(this)
            val layout = LayoutInflater.from(this).inflate(R.layout.add_exercise_alert_design,null)
            val sets = layout.findViewById(R.id.textInputSets) as EditText
            val reps = layout.findViewById(R.id.textInputReps) as EditText
            val weights = layout.findViewById(R.id.textInputWeights) as EditText
            val rests = layout.findViewById(R.id.textInputRests) as EditText
            val exercise_name = layout.findViewById(R.id.textInputExerciseName) as EditText
            alertD.setTitle("Add Exercise")
            alertD.setView(layout)

            alertD.setPositiveButton("Ok"){dialoginterface,i ->
                val exercise = Exercise(
                    0,
                    0,
                    exercise_name.text.toString().toUpperCase(),
                    sets.text.toString(),
                    reps.text.toString(),
                    weights.text.toString(),
                    rests.text.toString())

                
            }
            alertD.setNegativeButton("Cancel"){dialoginterface,i -> }
            alertD.show()
        }

    }
    override fun onBackPressed() {
        val alertD2 = AlertDialog.Builder(this)
        alertD2.setTitle("Warning")
        alertD2.setMessage("İf you go back your notes will be lost\nFinish the exercise if you want to save your notes")
        alertD2.setPositiveButton("Go Back") { dialoginterface, i ->
            timer.cancel()
            super.onBackPressed()
        }
        alertD2.setNegativeButton("Cancel"){dialoginterface,i -> }
        alertD2.show()
    }
}