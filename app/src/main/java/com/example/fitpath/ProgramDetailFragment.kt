package com.example.fitpath

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.room.util.foreignKeyCheck
import com.example.fitpath.classes.Program
import com.example.fitpath.classes.ProgramWorkouts
import com.example.fitpath.classes.Workout
import com.example.fitpath.databinding.FragmentProgramDetailBinding
import com.example.fitpath.models.ProgramDetailFragmentViewModel
import com.example.fitpath.models.WeeklyProgramFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProgramDetailFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailBinding
    private lateinit var spinnerAdapter:ArrayAdapter<String>
    private lateinit var viewmodel:ProgramDetailFragmentViewModel
    private lateinit var viewmodelWeeklyProgram:WeeklyProgramFragmentViewModel
    private lateinit var arrayWorkouts:ArrayList<String>
    private lateinit var selectedWorkoutsOne:ArrayList<String>
    private lateinit var selectedWorkoutsTwo:ArrayList<String>
    private lateinit var selectedWorkoutsThree:ArrayList<String>
    private lateinit var selectedWorkoutsFour:ArrayList<String>
    private lateinit var selectedWorkoutsFife:ArrayList<String>
    private lateinit var selectedWorkoutsSix:ArrayList<String>
    private lateinit var selectedWorkoutsSeven:ArrayList<String>
    private lateinit var lastProgramId:Program


    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_program_detail,container,false)
        design.programDetailObject = this
        viewmodel.allWorkouts()
        val bundle:ProgramDetailFragmentArgs by navArgs()
        val program = bundle.program
        if (program != null){
            design.buttonCreateProgram.text = "CHANGE"
            design.program = program
        }else{
            viewmodel.createProgram(" "," "," "," "," "," "," "," ")
        }
        lastProgramId = Program(0,"","","","","","","","")
        selectedWorkoutsOne = ArrayList<String>()
        selectedWorkoutsTwo = ArrayList<String>()
        selectedWorkoutsThree = ArrayList<String>()
        selectedWorkoutsFour = ArrayList<String>()
        selectedWorkoutsFife = ArrayList<String>()
        selectedWorkoutsSix = ArrayList<String>()
        selectedWorkoutsSeven = ArrayList<String>()
        design.dayNumber1 = "1"
        design.dayNumber2 = "2"
        design.dayNumber3 = "3"
        design.dayNumber4 = "4"
        design.dayNumber5 = "5"
        design.dayNumber6 = "6"
        design.dayNumber7 = "7"
        viewmodel.livedataLastID.observe(viewLifecycleOwner) {LID->
            lastProgramId=LID
        }
        viewmodel.livedata.observe(viewLifecycleOwner,{listWorkout->
            spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,android.R.id.text1,listWorkout)
            design.spinneradapter = spinnerAdapter
            arrayWorkouts = listWorkout as ArrayList<String>
        })
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:ProgramDetailFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
        val tempViewModel2:WeeklyProgramFragmentViewModel by viewModels()
        this.viewmodelWeeklyProgram = tempViewModel2

    }
    fun createButtonFun(program_title:String,day1:String,day2:String,day3:String,day4:String,day5:String,day6:String,day7:String){
        viewmodel.getLastId()
        val alertD2 = AlertDialog.Builder(requireContext())
        alertD2.setTitle("Information")
        alertD2.setMessage("Are you sure you want to record the program?")

        alertD2.setPositiveButton("Ok") { dialoginterface, i ->
            update_and_program_workouts(program_title,day1,day2,day3,day4,day5,day6,day7)
            design.progressBarCreate.visibility = View.VISIBLE
            Handler().postDelayed({
                Navigation.findNavController(design.buttonCreateProgram).navigate(R.id.programDetailF_weeklyProgram)

            },3000)
        }
        alertD2.setNegativeButton("Cancel"){dialoginterface,i -> }
        alertD2.show()
    }
    fun update(program_title:String,day1:String,day2:String,day3:String,day4:String,day5:String,day6:String,day7:String){
        val updateProgram = Program(lastProgramId.program_id,program_title,day1,day2,day3,day4,day5,day6,day7)
        viewmodel.updateProgram(updateProgram)
    }
    fun update_and_program_workouts(program_title:String,day1:String,day2:String,day3:String,day4:String,day5:String,day6:String,day7:String){
        addProgramWorkouts()
        update(program_title,day1,day2,day3,day4,day5,day6,day7)
    }
    fun addProgramWorkouts(){
        for (one in selectedWorkoutsOne) {
            val programWorkouts = ProgramWorkouts(0, lastProgramId.program_id, one, 1)
            Log.e("test",lastProgramId.program_id.toString())
            viewmodel.addProgramWorkouts(programWorkouts)
        }
        for (two in selectedWorkoutsTwo) {
            val programWorkouts2 = ProgramWorkouts(0, lastProgramId.program_id, two, 2)
            viewmodel.addProgramWorkouts(programWorkouts2)
        }
        for (three in selectedWorkoutsThree) {
            val programWorkouts3 = ProgramWorkouts(0, lastProgramId.program_id, three, 3)
            viewmodel.addProgramWorkouts(programWorkouts3)
        }
        for (four in selectedWorkoutsFour) {
            val programWorkouts4 = ProgramWorkouts(0, lastProgramId.program_id, four, 4)
            viewmodel.addProgramWorkouts(programWorkouts4)
        }
        for (fife in selectedWorkoutsFife) {
            val programWorkouts5 = ProgramWorkouts(0, lastProgramId.program_id, fife, 5)
            viewmodel.addProgramWorkouts(programWorkouts5)
        }
        for (six in selectedWorkoutsSix) {
            val programWorkouts6 = ProgramWorkouts(0, lastProgramId.program_id, six, 6)
            viewmodel.addProgramWorkouts(programWorkouts6)
        }
        for (seven in selectedWorkoutsSeven) {
            val programWorkouts7 = ProgramWorkouts(0, lastProgramId.program_id, seven, 7)
            viewmodel.addProgramWorkouts(programWorkouts7)
        }


    }
    fun addWorkoutOne(){
        design.spinnerDayOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkoutsOne.add(arrayWorkouts[position])}
            override fun onNothingSelected(parent: AdapterView<*>?) {} }}
    fun addWorkoutTwo(){
        design.spinnerDayTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkoutsTwo.add(arrayWorkouts[position]) }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }}
    fun addWorkoutThree(){
        design.spinnerDayThree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkoutsThree.add(arrayWorkouts[position]) }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }}
    fun addWorkoutFour(){
        design.spinnerDayFour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkoutsFour.add(arrayWorkouts[position]) }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }}
    fun addWorkoutFife(){
        design.spinnerDayFife.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkoutsFife.add(arrayWorkouts[position]) }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }}
    fun addWorkoutSix(){
        design.spinnerDaySix.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkoutsSix.add(arrayWorkouts[position]) }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }}
    fun addWorkoutSeven(){
        design.spinnerDaySeven.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkoutsSeven.add(arrayWorkouts[position]) }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }}
    fun showWorkouts(number:String){
        var workouts = ""
        val alertWorkoutsDetail = AlertDialog.Builder(requireContext())
        alertWorkoutsDetail.setTitle("Workouts")
        if (number == "1"){
            for (x in selectedWorkoutsOne){ workouts+="$x\n" }
        }
        if (number == "2"){
            for (x in selectedWorkoutsTwo){ workouts+="$x\n" }
        }
        if (number == "3"){
            for (x in selectedWorkoutsThree){ workouts+="$x\n" }
        }
        if (number == "4"){
            for (x in selectedWorkoutsFour){ workouts+="$x\n" }
        }
        if (number == "5"){
            for (x in selectedWorkoutsFife){ workouts+="$x\n" }
        }
        if (number == "6"){
            for (x in selectedWorkoutsSix){ workouts+="$x\n" }
        }
        if (number == "7"){
            for (x in selectedWorkoutsSeven){ workouts+="$x\n" }
        }
        alertWorkoutsDetail.setMessage(workouts)
        alertWorkoutsDetail.setNegativeButton("Cancel"){ dialoginterface, i -> }
        alertWorkoutsDetail.show()
    }
    fun deleteWorkouts(number:String){
        if (number == "1"){ selectedWorkoutsOne.clear() }
        if (number == "2"){ selectedWorkoutsTwo.clear() }
        if (number == "3"){ selectedWorkoutsThree.clear() }
        if (number == "4"){ selectedWorkoutsFour.clear() }
        if (number == "5"){ selectedWorkoutsFife.clear() }
        if (number == "6"){ selectedWorkoutsSix.clear() }
        if (number == "7"){ selectedWorkoutsSeven.clear() }
    }
}
