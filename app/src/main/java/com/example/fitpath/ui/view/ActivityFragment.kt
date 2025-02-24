package com.example.fitpath.ui.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.ExerciseAdapter
import com.example.fitpath.ui.adapters.SetAdapter
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.databinding.FragmentActivityBinding
import com.example.fitpath.ui.viewmodels.ActivityTrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class ActivityFragment : Fragment() {
    private lateinit var design: FragmentActivityBinding
    private lateinit var adapter: ExerciseAdapter
    private lateinit var set_adapter: SetAdapter
    private val viewmodel : ActivityTrainingViewModel by activityViewModels<ActivityTrainingViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_activity,container,false)
        design.activitytrainingobject = this
        return design.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialTrainingContinues()
        setUpAdapter()
    }
    private fun setUpAdapter(){
        adapter = ExerciseAdapter(requireContext(),viewmodel.exercises)
        set_adapter = SetAdapter(requireContext(),viewmodel.setList)
        design.recyclerViewSets.setHasFixedSize(true)
        design.TrainingRV.setHasFixedSize(true)
        design.adapter = adapter
        design.adapterSet = set_adapter
    }

    private fun initialTrainingContinues(){
        if (!viewmodel.timerStatus){
            design.textViewExerciseText.text = viewmodel.exerciseTitle
            setUpTimerCollect()
            setUpTimerStartVisibility()
            if (viewmodel.minute == 0){
                design.minute = 0
            }
            if (viewmodel.hour == 0){
                design.hour = 0
            }
            if (viewmodel.exerciseStatus){
                setUpExerciseStartVisibility()
            }
            if (viewmodel.setStatus){
                setUpStartSetVisibility()
            }
        }else{
            design.second = 0
            design.minute = 0
            design.hour = 0
        }
    }

    private fun setUpTimerCollect(){
        viewLifecycleOwner.lifecycleScope.launch() {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    viewmodel.secondFlowImmutable,
                    viewmodel.minuteFlowImmutable,
                    viewmodel.hourFlowImmutable) { second,minute,hour -> Triple(second,minute,hour)}.collect{ (second,minute,hour) ->
                    design.second = second
                    design.minute = minute
                    design.hour = hour
                }
            }
        }
    }
    private fun setTrainingDayAndTime(){
        val calendar = Calendar.getInstance()
        val day1 = calendar.get(Calendar.DAY_OF_MONTH)
        val month1 = calendar.get(Calendar.MONTH)
        val year1 = calendar.get(Calendar.YEAR)
        viewmodel.date = "$day1/${month1+1}/$year1"
        val hour = design.textViewHour.text.toString()
        val minute = design.textViewMinute.text.toString()
        val second = design.textViewSecond.text.toString()
        viewmodel.time = "$hour:$minute:$second"
    }
    fun buttonTraining(){
        if (viewmodel.timerStatus){
            viewmodel.timer()
            setUpTimerCollect()
            viewmodel.timerStatus = false
            setUpTimerStartVisibility()
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val dailyExercise = DailyExercise(0," "," "," ")
                viewmodel.addDailyExercise(dailyExercise)
            }
        }else{
            viewmodel.timerStatus = true
            viewmodel.timerStop()
            setUpTimerStopVisibility()
            setTrainingDayAndTime()
            setUpAlertDialogFinishTraining()
            }
    }
    private fun setUpAlertDialogFinishTraining(){
        val design2 = LayoutInflater.from(requireContext()).inflate(R.layout.alert_exercise_name,null)
        val trainingName: EditText = design2.findViewById(R.id.textExerciseNameAlert)

        alertBuild(
            requireContext(),
            design2,
            getString(R.string.AlertDialogFinishTrainingTitle),
            getString(R.string.AlertDialogFinishTrainingMessage),
            getString(R.string.AlertDialogPositiveButtonText),
            getString(R.string.AlertDialogNegativeButtonText),
            onConfirm = {
                viewmodel.addExerciseViewModel(trainingName.text.toString())
                Navigation.findNavController(design.buttonTraining).navigate(R.id.activityFragment_dailyExerciseFragment)
            },
            onCancel = {
                viewmodel.deleteLastExercise()
            }
        )
    }

    fun addExercise(){
        if (!viewmodel.exerciseStatus){
            val design2 = LayoutInflater.from(requireContext()).inflate(R.layout.alert_exercise_name,null)
            val exerciseName: EditText = design2.findViewById(R.id.textExerciseNameAlert)

            alertBuild(
                requireContext(),
                design2,
                getString(R.string.AlertDialogAddExerciseTitle),
                getString(R.string.AlertDialogAddExerciseMessage),
                getString(R.string.AlertDialogPositiveButtonText),
                getString(R.string.AlertDialogNegativeButtonText),
                onConfirm = {
                    viewmodel.exerciseStatus = true
                    viewmodel.exerciseTitle = exerciseName.text.toString()
                    design.textViewExerciseText.text = viewmodel.exerciseTitle
                    setUpExerciseStartVisibility()
                    viewmodel.setList.clear()
                },
                onCancel = {}
            )
        }else{
            if (viewmodel.setStatus){
                alertBuild(
                    requireContext(),
                    null,
                    getString(R.string.AlertDialogTitleSetStatusTrue),
                    getString(R.string.AlertDialogMessageSetStatusTrue),
                    null,
                    null,
                    null,null
                    )
            }else{
                design.buttonAddExercise.setText(R.string.ButtonAddExerciseText)
                alertBuild(
                    requireContext(),
                    null,
                    getString(R.string.AlertDialogTitleSetStatusFalse),
                    getString(R.string.AlertDialogMessageSetStatusFalse),
                    getString(R.string.AlertDialogPositiveButtonText),
                    getString(R.string.AlertDialogNegativeButtonText),
                    onConfirm = {
                        viewmodel.finishExercise()
                        setUpExerciseStopVisibility()
                    },
                    onCancel = {}
                )
            }
        }
    }
    fun addSet(){
        if (!viewmodel.setStatus){
            design.set = viewmodel.set.toString()
            viewmodel.addSetStart()
            setUpStartSetVisibility()
            viewmodel.setStatus = true
        }else{
            addRep()
            addWeight()
            viewmodel.setStatus = false
            viewmodel.addSetStop()
            setUpStopSetVisibility()
        }
    }
    private fun addRep(){
        viewmodel.rep = design.editTextRep.text.toString()
        viewmodel.addRepViewModel()
    }
    private fun addWeight(){
        viewmodel.weight = design.editTextWeight.text.toString()
        viewmodel.addWeightViewModel()
    }

    private fun setUpStartSetVisibility(){
        design.buttonAddSet.setText(R.string.ButtonSetTextStart)
        design.buttonAddSet.setBackgroundColor(Color.YELLOW)
        design.cardViewExercise.visibility = View.VISIBLE
    }
    private fun setUpStopSetVisibility(){
        design.buttonAddSet.setBackgroundColor(Color.CYAN)
        design.cardViewExercise.visibility = View.INVISIBLE
        design.editTextRep.setText("")
        design.editTextWeight.setText("")
        design.buttonAddSet.setText(R.string.ButtonSetTextStop)
    }
    private fun setUpTimerStartVisibility(){
        design.buttonAddExercise.visibility = View.VISIBLE
        design.buttonTraining.setBackgroundColor(Color.RED)
        design.buttonTraining.setText(R.string.StopTrainingTitle)
    }
    private fun setUpTimerStopVisibility(){
        design.buttonAddExercise.visibility = View.INVISIBLE
        design.textViewExerciseText.text = ""
        design.textViewExerciseText.visibility = View.INVISIBLE
        design.buttonTraining.setBackgroundColor(resources.getColor(R.color.PrimaryFife))
        design.buttonTraining.setText(R.string.StartTrainingTitle)
    }
    private fun setUpExerciseStartVisibility(){
        design.buttonAddExercise.setText(R.string.ConfirmExerciseTitle)
        design.buttonAddExercise.setBackgroundColor(Color.GREEN)
        design.textViewExerciseText.visibility = View.VISIBLE
        design.buttonAddSet.visibility = View.VISIBLE
    }
    private fun setUpExerciseStopVisibility(){
        design.textViewExerciseText.visibility = View.INVISIBLE
        design.buttonAddExercise.setBackgroundColor(Color.BLUE)
        design.buttonAddSet.visibility = View.INVISIBLE
    }

    private fun alertBuild(context: Context, view: View?=null, setTitle:String, setMessage:String, setPositiveButtonText:String?=null, setNegativeButtonText:String?=null, onConfirm:(() -> Unit)?=null, onCancel:(() -> Unit)?=null){
        AlertDialog.Builder(context).apply {
            setTitle(setTitle)
            setMessage(setMessage)
            view?.let {setView(view)}
            setPositiveButton(setPositiveButtonText) { _, _ ->
                onConfirm?.invoke()
            }
            setNegativeButton(setNegativeButtonText) { _, _ ->
                onCancel?.invoke()
            }
            show()
        }
    }
}