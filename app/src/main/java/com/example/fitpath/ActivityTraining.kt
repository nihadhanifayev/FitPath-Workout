package com.example.fitpath

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.ExerciseAdapter
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.classes.Exercise
import com.example.fitpath.databinding.ActivityTrainingBinding
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.dao.ExerciseDao
import com.example.fitpath.roomDB.database.roomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Calendar
import java.util.Date
import kotlin.math.min

class ActivityTraining : AppCompatActivity() {
    private lateinit var design:ActivityTrainingBinding
    private lateinit var adapter:ExerciseAdapter
    private lateinit var exercises:ArrayList<Exercise>
    private lateinit var timer:CountDownTimer
    private lateinit var db:roomDatabase
    private lateinit var dailyE_Dao:DailyExerciseDao
    private lateinit var exercise_Dao:ExerciseDao
    private lateinit var LastExercise:DailyExercise
    private var second = 0
    private var minute = 0
    private var hour = 0
    private var timer_status = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(design.root)
        LastExercise = DailyExercise(0, "","","")
        db = roomDatabase.dataBaseAccess(this)!!
        dailyE_Dao = db.getDailyExerciseDao()
        exercise_Dao = db.getExerciseDao()
        exercises = ArrayList<Exercise>()
        adapter = ExerciseAdapter(this,exercises)
        design.TrainingRV.setHasFixedSize(true)
        design.TrainingRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
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
                //add daily exercise
                val job = CoroutineScope(Dispatchers.Main).launch {
                    val dailyExercise = DailyExercise(0," "," "," ")
                    dailyE_Dao.addDailyExercise(dailyExercise)
                }
            }else{
                timer_status = false
                design.buttonTraining.setBackgroundColor(resources.getColor(R.color.PrimaryFife))
                design.buttonTraining.setText("Start Training")
                timer.cancel()
                //update daily exercise
                val calendar = Calendar.getInstance()
                val day1 = calendar.get(Calendar.DAY_OF_MONTH)
                val month1 = calendar.get(Calendar.MONTH)
                val year1 = calendar.get(Calendar.YEAR)
                val date = "$day1/${month1+1}/$year1"
                val Hour = design.textViewHour.text
                val Minute = design.textViewMinute.text
                val Second = design.textViewSecond.text
                val time = Hour.toString()+Minute.toString()+Second.toString()
                val alertExerciseName = AlertDialog.Builder(this)
                val design2 = LayoutInflater.from(this).inflate(R.layout.alert_exercise_name,null)
                val exercise_name = design2.findViewById(R.id.textExerciseNameAlert) as EditText
                alertExerciseName.setTitle("Add Exercise Name")
                alertExerciseName.setMessage("İf you cancel, your training records will be deleted.")
                alertExerciseName.setView(design2)
                alertExerciseName.setPositiveButton("Ok"){dialoginterface,i ->
                    val job2 = CoroutineScope(Dispatchers.Main).launch {
                        LastExercise = dailyE_Dao.getLastID()
                        val editedExercise = DailyExercise(LastExercise.exercise_daily_id,exercise_name.text.toString(),time,date)
                        dailyE_Dao.updateDailyExercise(editedExercise)
                        startActivity(Intent(this@ActivityTraining,MainActivity::class.java))
                        finish()
                    }
                }
                alertExerciseName.setNegativeButton("Cancel"){dialoginterface,i ->
                    val job3 = CoroutineScope(Dispatchers.Main).launch {
                        LastExercise = dailyE_Dao.getLastID()
                        dailyE_Dao.deleteDailyExercise(LastExercise)
                        startActivity(Intent(this@ActivityTraining,MainActivity::class.java))
                    }
                }
                alertExerciseName.show()
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
                val job5 = CoroutineScope(Dispatchers.Main).launch {
                    LastExercise = dailyE_Dao.getLastID()
                    val exercise = Exercise(
                        0,
                        LastExercise.exercise_daily_id,
                        exercise_name.text.toString().toUpperCase(),
                        sets.text.toString(),
                        reps.text.toString(),
                        weights.text.toString(),
                        rests.text.toString())
                    exercises.add(exercise)
                    exercise_Dao.addExercise(exercise)

                }

                
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
        val job4 = CoroutineScope(Dispatchers.Main).launch {
            LastExercise = dailyE_Dao.getLastID()
            dailyE_Dao.deleteDailyExercise(LastExercise)
        }
    }
}