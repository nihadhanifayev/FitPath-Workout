package com.example.fitpath.ui.view

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.ExerciseAdapter
import com.example.fitpath.ui.adapters.SetAdapter
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.data.model.Exercise
import com.example.fitpath.data.model.Set
import com.example.fitpath.databinding.FragmentActivityBinding
import com.example.fitpath.ui.viewmodels.ActivityTrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
@AndroidEntryPoint
class ActivityFragment : Fragment() {
    private lateinit var design: FragmentActivityBinding
    private lateinit var adapter: ExerciseAdapter
    private lateinit var set_adapter: SetAdapter
    private lateinit var exercises:ArrayList<Exercise>
    private lateinit var setList:ArrayList<Set>
    private lateinit var timer: CountDownTimer
    private lateinit var lastID: DailyExercise
    private lateinit var viewmodel: ActivityTrainingViewModel
    private var rep : String = ""
    private var final_rep : String = ""
    private var weight : String = ""
    private var final_weight : String = ""
    private var set = 0
    private var final_set = ""
    private var exercise_Title = ""
    private var second = 0
    private var minute = 0
    private var hour = 0
    private var timer_status = false
    private var exercise_status = false
    private var set_status = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_activity,container,false)
        design.activitytrainingobject = this
        design.cardViewExercise.visibility = View.INVISIBLE
        design.buttonAddExercise.visibility = View.INVISIBLE
        design.buttonAddSet.visibility = View.INVISIBLE
        design.textViewExerciseText.visibility = View.INVISIBLE
        design.buttonAddExercise.setBackgroundColor(Color.BLUE)
        design.buttonAddSet.setBackgroundColor(Color.CYAN)
        exercises = ArrayList<Exercise>()
        setList = ArrayList<Set>()
        adapter = ExerciseAdapter(requireContext(),exercises)
        set_adapter = SetAdapter(requireContext(),setList)
        design.recyclerViewSets.setHasFixedSize(true)
        design.TrainingRV.setHasFixedSize(true)
        design.adapter = adapter
        design.adapterSet = set_adapter
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

        return design.root
    }

    fun buttonTraining(){
        if (timer_status==false){
            timer_status = true
            design.buttonAddExercise.visibility = View.VISIBLE
            design.buttonTraining.setBackgroundColor(Color.RED)
            design.buttonTraining.setText("Stop Training")
            timer.start()
            val dailyExercise = DailyExercise(0," "," "," ")
            viewmodel.addDailyExercise(dailyExercise)
        }else{
            timer_status = false
            design.buttonAddExercise.visibility = View.INVISIBLE
            design.buttonAddExercise.visibility = View.INVISIBLE
            design.textViewExerciseText.text = ""
            design.textViewExerciseText.visibility = View.INVISIBLE
            design.buttonTraining.setBackgroundColor(resources.getColor(R.color.PrimaryFife))
            design.buttonTraining.setText("Start Training")
            timer.cancel()
            viewmodel.getLastID()
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
            val alertExerciseName = AlertDialog.Builder(requireContext())
            val design2 = LayoutInflater.from(requireContext()).inflate(R.layout.alert_exercise_name,null)
            val exercise_name = design2.findViewById(R.id.textExerciseNameAlert) as EditText
            alertExerciseName.setTitle("Add Exercise Name")
            alertExerciseName.setMessage("İf you cancel, your training records will be deleted.")
            alertExerciseName.setView(design2)

            alertExerciseName.setPositiveButton("Ok"){dialoginterface,i ->
                viewmodel.livedataLastExercise.observe(viewLifecycleOwner) { LastExercise ->
                    val editedExercise = DailyExercise(
                        LastExercise.exercise_daily_id,
                        exercise_name.text.toString(),
                        time,
                        date
                    )
                    viewmodel.updateDailyExercise(editedExercise)
                    Navigation.findNavController(design.buttonTraining)
                        .navigate(R.id.activityFragment_dailyExerciseFragment)
                }
            }
            alertExerciseName.setNegativeButton("Cancel"){dialoginterface,i ->
                viewmodel.livedataLastExercise.observe(viewLifecycleOwner) { LastExercise ->
                    viewmodel.deleteDailyExercise(LastExercise)
                }
            }
            alertExerciseName.show()
            second = 0
            minute = 0
            hour = 0
        }
    }
    fun addExercise(){
        if (!exercise_status){
            val alertExerciseName = AlertDialog.Builder(requireContext())
            val design2 = LayoutInflater.from(requireContext()).inflate(R.layout.alert_exercise_name,null)
            val exercise_name = design2.findViewById(R.id.textExerciseNameAlert) as EditText
            alertExerciseName.setTitle("Add Exercise Name")
            alertExerciseName.setView(design2)
            alertExerciseName.setPositiveButton("Ok"){dialoginterface,i ->
                exercise_status = true
                design.buttonAddExercise.setText("CONFİRM EXERCISE")
                design.buttonAddExercise.setBackgroundColor(Color.GREEN)
                exercise_Title = exercise_name.text.toString()
                design.textViewExerciseText.text = exercise_Title
                design.textViewExerciseText.visibility = View.VISIBLE
                design.buttonAddSet.visibility = View.VISIBLE
                setList.clear()
            }
            alertExerciseName.setNegativeButton("Cancel"){dialoginterface,i ->

            }
            alertExerciseName.show()
        }else{
            if (set_status){
                val alertD = AlertDialog.Builder(requireContext())
                alertD.setMessage("complete the exercise first")
                alertD.setTitle("İnfo")
                alertD.show()
            }else{
                design.buttonAddExercise.setText("ADD EXERCISE")
                val alertD = AlertDialog.Builder(requireContext())
                alertD.setMessage("Are you sure you want to record the exercise?")
                alertD.setTitle("Add Exercise")
                viewmodel.getLastID()
                alertD.setPositiveButton("Ok"){dialoginterface,i ->
                    viewmodel.livedataLastExercise.observe(viewLifecycleOwner) { LastExercise ->
                        lastID = LastExercise
                        design.textViewExerciseText.visibility = View.INVISIBLE
                        design.buttonAddExercise.setBackgroundColor(Color.BLUE)
                        design.buttonAddSet.visibility = View.INVISIBLE
                        exercise_status = false
                        set_status = false
                    }
                    val exercise = Exercise(
                        0,
                        lastID.exercise_daily_id,
                        exercise_Title,final_set,final_rep,final_weight)
                    exercises.add(exercise)
                    viewmodel.addExercise(exercise)
                    design.textViewExerciseText.setText(R.string.Empty)
                    set=0
                    rep=""
                    weight=""
                    final_weight = ""
                    final_rep = ""
                    final_set = ""

                }
                alertD.setNegativeButton("Cancel"){dialoginterface,i -> }
                alertD.show()
            }

        }
    }
    fun addSet(){
        if (!set_status){
            set++
            final_set+="${set} "
            design.set = set.toString()
            set_status = true
            design.buttonAddSet.setText("CONFIRM SET")
            design.buttonAddSet.setBackgroundColor(Color.YELLOW)
            design.cardViewExercise.visibility = View.VISIBLE
        }else{
            addRep()
            addWeight()
            set_status = false
            design.buttonAddSet.setText("ADD SET")
            val set = Set(set.toString(),rep,weight)
            setList.add(set)
            design.buttonAddSet.setBackgroundColor(Color.CYAN)
            design.cardViewExercise.visibility = View.INVISIBLE
            design.editTextRep.setText("")
            design.editTextWeight.setText("")
        }
    }
    fun addRep(){
        rep = design.editTextRep.text.toString()
        final_rep+="$rep "
        }
    fun addWeight(){
        weight = design.editTextWeight.text.toString()
        final_weight+="$weight "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ActivityTrainingViewModel by viewModels()
        this.viewmodel = tempViewModel
    }
}