package com.example.fitpath.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.fitpath.R
import com.example.fitpath.data.model.Program
import com.example.fitpath.data.model.ProgramWorkouts
import com.example.fitpath.databinding.FragmentProgramDetailBinding
import com.example.fitpath.ui.viewmodels.ProgramDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProgramDetailFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailBinding
    private lateinit var spinnerAdapter:ArrayAdapter<String>
    private val viewmodel: ProgramDetailFragmentViewModel by activityViewModels()
    private lateinit var arrayWorkouts:ArrayList<String>
    private lateinit var lastProgramId: Program



    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_program_detail,container,false)
        design.programDetailObject = this
        viewmodel.allWorkouts()
        val bundle:ProgramDetailFragmentArgs by navArgs()
        val program = bundle.program
        observeLiveData(program)

        return design.root
    }
    fun createButtonFun(programTitle:String, day1:String, day2:String, day3:String, day4:String, day5:String, day6:String, day7:String){
        viewmodel.getLastId()
        val alertD2 = AlertDialog.Builder(requireContext())
        alertD2.setTitle("Information")
        alertD2.setMessage("Are you sure you want to record the program?")

        alertD2.setPositiveButton("Ok") { dialoginterface, i ->
            addProgramWorkouts()
            update(programTitle,day1,day2,day3,day4,day5,day6,day7)
            design.progressBarCreate.visibility = View.VISIBLE
            Handler().postDelayed({
                Navigation.findNavController(design.buttonCreateProgram).navigate(R.id.programDetailF_weeklyProgram)
            },3000)
        }
        alertD2.setNegativeButton("Cancel"){dialoginterface,i -> }
        alertD2.show()
    }
    private fun update(programTitle:String, day1:String, day2:String, day3:String, day4:String, day5:String, day6:String, day7:String){
        val updateProgram = Program(lastProgramId.program_id,programTitle,day1,day2,day3,day4,day5,day6,day7)
        viewmodel.updateProgram(updateProgram)
    }
    private fun addProgramWorkouts(){
        for (selectedWorkout in viewmodel.selectedWorkoutManager){
            for (workout in selectedWorkout){
                val programWorkouts = ProgramWorkouts(0, lastProgramId.program_id, workout, viewmodel.selectedWorkoutManager.indexOf(selectedWorkout)+1)
                viewmodel.addProgramWorkouts(programWorkouts)
            }
        }
    }
    fun addWorkout(spinnerIndex:Int){
        val spinner = when(spinnerIndex){
            0 -> design.spinnerDayOne
            1 -> design.spinnerDayTwo
            2 -> design.spinnerDayThree
            3 -> design.spinnerDayFour
            4 -> design.spinnerDayFive
            5 -> design.spinnerDaySix
            else -> {design.spinnerDaySeven}
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewmodel.selectedWorkoutManager[spinnerIndex].add(arrayWorkouts[position])}
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }}
    fun showWorkouts(number:Int){
        var workouts = ""
        val alertWorkoutsDetail = AlertDialog.Builder(requireContext())
        alertWorkoutsDetail.setTitle("Workouts")
        for (workout in viewmodel.selectedWorkoutManager[number-1]){
            workouts+=workout+"\n"
        }
        alertWorkoutsDetail.setMessage(workouts)
        alertWorkoutsDetail.setNegativeButton("Cancel"){ dialoginterface, i -> }
        alertWorkoutsDetail.show()
    }
    fun deleteWorkouts(number:Int){
        viewmodel.deleteSelectedWorkouts(number)
    }

    private fun observeLiveData(program:Program?){
        if (program != null){
            viewmodel.workoutManagerClear()
            design.buttonCreateProgram.text = "CHANGE"
            design.program = program
            lifecycleScope.launch {
                viewmodel.getProgramWorkoutWithID(program.program_id)
                viewmodel.observeAndAddSelectedWorkouts(viewmodel.workoutLists)
            }

        }else{
            viewmodel.createProgram(" "," "," "," "," "," "," "," ")
        }
        lastProgramId = Program(0,"","","","","","","","")
        viewmodel.livedataLastID.observe(viewLifecycleOwner) {LastID->
            lastProgramId=LastID
        }
        viewmodel.livedata.observe(viewLifecycleOwner,{listWorkout->
            spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,android.R.id.text1,listWorkout)
            design.spinneradapter = spinnerAdapter
            arrayWorkouts = listWorkout as ArrayList<String>
        })
    }
}
