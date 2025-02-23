package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpath.data.model.Program
import com.example.fitpath.data.model.ProgramWorkouts
import com.example.fitpath.data.dao.ProgramDao
import com.example.fitpath.data.dao.ProgramWorkoutsDao
import com.example.fitpath.data.dao.WorkoutDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramDetailFragmentViewModel @Inject constructor (var dao: ProgramDao, private var daoWorkout: WorkoutDao,
                                                          private var daoProgramWorkouts: ProgramWorkoutsDao
):ViewModel() {
    var livedata = MutableLiveData<List<String>>()
    var livedataLastID = MutableLiveData<Program>()
    var livedataLastProgramWorkouts = MutableLiveData<List<ProgramWorkouts>>()

    private var selectedWorkoutsOne = ArrayList<String>()
    private var selectedWorkoutsTwo = ArrayList<String>()
    private var selectedWorkoutsThree = ArrayList<String>()
    private var selectedWorkoutsFour = ArrayList<String>()
    private var selectedWorkoutsFive = ArrayList<String>()
    private var selectedWorkoutsSix = ArrayList<String>()
    private var selectedWorkoutsSeven = ArrayList<String>()
    var selectedWorkoutManager = ArrayList<ArrayList<String>>()
    private var workoutLists = ArrayList<ProgramWorkouts>()
    var status : Boolean = false

    init {
        livedata = MutableLiveData<List<String>>()
        livedataLastID = MutableLiveData<Program>()
        livedataLastProgramWorkouts = MutableLiveData<List<ProgramWorkouts>>()
        selectedWorkoutInit()
    }
    private fun selectedWorkoutInit(){
        selectedWorkoutManager.add(selectedWorkoutsOne)
        selectedWorkoutManager.add(selectedWorkoutsTwo)
        selectedWorkoutManager.add(selectedWorkoutsThree)
        selectedWorkoutManager.add(selectedWorkoutsFour)
        selectedWorkoutManager.add(selectedWorkoutsFive)
        selectedWorkoutManager.add(selectedWorkoutsSix)
        selectedWorkoutManager.add(selectedWorkoutsSeven)
    }
    private fun workoutManagerClear(){
        for (x in 0..6){
            selectedWorkoutManager[x].clear()
        }
    }
    fun observeAndAddSelectedWorkouts(workoutList:List<ProgramWorkouts>){
        workoutManagerClear()
        for (workout in workoutList){
            if (workout.programWorkoutDay==1){selectedWorkoutManager[0].add(workout.workoutName)}
            if (workout.programWorkoutDay==2){selectedWorkoutManager[1].add(workout.workoutName)}
            if (workout.programWorkoutDay==3){selectedWorkoutManager[2].add(workout.workoutName)}
            if (workout.programWorkoutDay==4){selectedWorkoutManager[3].add(workout.workoutName)}
            if (workout.programWorkoutDay==5){selectedWorkoutManager[4].add(workout.workoutName)}
            if (workout.programWorkoutDay==6){selectedWorkoutManager[5].add(workout.workoutName)}
            if (workout.programWorkoutDay==7){selectedWorkoutManager[6].add(workout.workoutName)}
        }
    }
    fun createProgram(programTitle:String, dayOne:String, dayTwo:String, dayThree:String, dayFour:String, dayFife:String, daySix:String, daySeven:String,){
        viewModelScope.launch {
            val program1 =
                Program(
                    0,
                    programTitle,
                    dayOne,
                    dayTwo,
                    dayThree,
                    dayFour,
                    dayFife,
                    daySix,
                    daySeven
                )
            dao.addProgram(program1)
        }
    }

    fun deleteSelectedWorkouts(number:Int){
        when(number){
            1 -> selectedWorkoutManager[0].clear()
            2 -> selectedWorkoutManager[1].clear()
            3 -> selectedWorkoutManager[2].clear()
            4 -> selectedWorkoutManager[3].clear()
            5 -> selectedWorkoutManager[4].clear()
            6 -> selectedWorkoutManager[5].clear()
            7 -> selectedWorkoutManager[6].clear()
        }
    }

    fun allWorkouts(){
        viewModelScope.launch {
            livedata.value = daoWorkout.getWorkoutsAll()
        }
    }
    fun addProgramWorkouts(program: ProgramWorkouts){
        viewModelScope.launch {
            daoProgramWorkouts.addProgramWorkout(program)
        }
    }
    fun getLastId(){
        viewModelScope.launch {
            livedataLastID.value = daoProgramWorkouts.getLastID()
        }
    }
    fun updateProgram(program: Program){
        viewModelScope.launch {
            dao.updateProgram(program)
        }
    }
    fun getProgramWorkoutWithID(id:Int){
        viewModelScope.launch{
            workoutLists = daoProgramWorkouts.getProgramIDWorkoutsWithPW(id) as ArrayList<ProgramWorkouts>
            livedataLastProgramWorkouts.postValue(workoutLists)
        }
    }
    fun checkList(){
        for (workout in workoutLists){
            if (selectedWorkoutManager[0].contains(workout.workoutName)){
                selectedWorkoutManager[0].remove(workout.workoutName)
            }
            if (selectedWorkoutManager[1].contains(workout.workoutName)){
                selectedWorkoutManager[1].remove(workout.workoutName)
            }
            if (selectedWorkoutManager[2].contains(workout.workoutName)){
                selectedWorkoutManager[2].remove(workout.workoutName)
            }
            if (selectedWorkoutManager[3].contains(workout.workoutName)){
                selectedWorkoutManager[3].remove(workout.workoutName)
            }
            if (selectedWorkoutManager[4].contains(workout.workoutName)){
                selectedWorkoutManager[4].remove(workout.workoutName)
            }
            if (selectedWorkoutManager[5].contains(workout.workoutName)){
                selectedWorkoutManager[5].remove(workout.workoutName)
            }
            if (selectedWorkoutManager[6].contains(workout.workoutName)){
                selectedWorkoutManager[6].remove(workout.workoutName)
            }
        }
    }

}