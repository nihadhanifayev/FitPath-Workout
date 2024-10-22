package com.example.fitpath

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.ExerciseAdapter
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.classes.Exercise
import com.example.fitpath.databinding.ActivityTrainingBinding
import com.example.fitpath.models.ActivityTrainingViewModel
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.dao.ExerciseDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Calendar
import java.util.Date
import kotlin.math.min

@AndroidEntryPoint
class ActivityTraining : AppCompatActivity() {
    private lateinit var design:ActivityTrainingBinding
    private lateinit var adapter:ExerciseAdapter
    private lateinit var exercises:ArrayList<Exercise>
    private lateinit var timer:CountDownTimer
    private lateinit var lastID:DailyExercise
    private val viewmodel:ActivityTrainingViewModel by viewModels()
    private var second = 0
    private var minute = 0
    private var hour = 0
    private var timer_status = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_training)
        design.activitytrainingobject = this
        exercises = ArrayList<Exercise>()
        viewmodel.getLastID()
        adapter = ExerciseAdapter(this,exercises)
        design.TrainingRV.setHasFixedSize(true)
        design.adapter = adapter
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
    }
    fun fabAddExercise(){
        val alertD = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.add_exercise_alert_design,null)
        val sets = layout.findViewById(R.id.textInputSets) as EditText
        val reps = layout.findViewById(R.id.textInputReps) as EditText
        val weights = layout.findViewById(R.id.textInputWeights) as EditText
        val rests = layout.findViewById(R.id.textInputRests) as EditText
        val exercise_name = layout.findViewById(R.id.textInputExerciseName) as EditText
        alertD.setTitle("Add Exercise")
        alertD.setView(layout)
        viewmodel.getLastID()
        alertD.setPositiveButton("Ok"){dialoginterface,i ->
            viewmodel.livedataLastExercise.observe(this@ActivityTraining,{LastExercise->
                lastID = LastExercise
            })
            val exercise = Exercise(
                0,
                lastID.exercise_daily_id,
                exercise_name.text.toString().toUpperCase(),
                sets.text.toString(),
                reps.text.toString(),
                weights.text.toString(),
                rests.text.toString())
            exercises.add(exercise)
            viewmodel.addExercise(exercise)
        }
        alertD.setNegativeButton("Cancel"){dialoginterface,i -> }
        alertD.show()
    }
    fun buttonTraining(){
        if (timer_status==false){
            timer_status = true
            design.buttonTraining.setBackgroundColor(Color.RED)
            design.buttonTraining.setText("Stop Training")
            timer.start()
            //add daily exercise
            val dailyExercise = DailyExercise(0," "," "," ")
            viewmodel.addDailyExercise(dailyExercise)
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
            //viewmodel.getLastID()
            alertExerciseName.setPositiveButton("Ok"){dialoginterface,i ->
                viewmodel.livedataLastExercise.observe(this@ActivityTraining,{LastExercise->
                    val editedExercise = DailyExercise(LastExercise.exercise_daily_id,exercise_name.text.toString(),time,date)
                    viewmodel.updateDailyExercise(editedExercise)
                    startActivity(Intent(this@ActivityTraining,MainActivity::class.java))
                    finish()
                })
            }
            alertExerciseName.setNegativeButton("Cancel"){dialoginterface,i ->
                //viewmodel.getLastID()
                viewmodel.livedataLastExercise.observe(this@ActivityTraining,{LastExercise->
                    viewmodel.deleteDailyExercise(LastExercise)
                    startActivity(Intent(this@ActivityTraining,MainActivity::class.java))
                })
            }
            alertExerciseName.show()
            second = 0
            minute = 0
            hour = 0
        }
    }
    override fun onBackPressed() {

        val alertD2 = AlertDialog.Builder(this)
        alertD2.setTitle("Warning")
        alertD2.setMessage("İf you go back your notes will be lost\nFinish the exercise if you want to save your notes")
        //viewmodel.getLastID()
        alertD2.setPositiveButton("Go Back") { dialoginterface, i ->
            timer.cancel()
            viewmodel.livedataLastExercise.observe(this@ActivityTraining,{LastExercise->
                viewmodel.deleteDailyExercise(LastExercise)
            })
            super.onBackPressed()
        }
        alertD2.setNegativeButton("Cancel"){dialoginterface,i -> }
        alertD2.show()

    }
}