package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.data.model.Program
import com.example.fitpath.data.model.ProgramWorkouts
import com.example.fitpath.data.dao.ProgramDao
import com.example.fitpath.data.dao.ProgramWorkoutsDao
import com.example.fitpath.data.dao.WorkoutDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramDetailFragmentViewModel @Inject constructor (var dao: ProgramDao, private var daoWorkout: WorkoutDao,
                                                          private var daoProgramWorkouts: ProgramWorkoutsDao
):ViewModel() {
    var livedata = MutableLiveData<List<String>>()
    var livedataLastID = MutableLiveData<Program>()
    var livedataLastProgramWorkouts = MutableLiveData<List<ProgramWorkouts>>()

    init {
        livedata = MutableLiveData<List<String>>()
        livedataLastID = MutableLiveData<Program>()
        livedataLastProgramWorkouts = MutableLiveData<List<ProgramWorkouts>>()
    }

    fun createProgram(program_title:String,dayOne:String,dayTwo:String,dayThree:String,dayFour:String,dayFife:String,daySix:String,daySeven:String,){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val program_name = program_title
            val day1 = dayOne
            val day2 = dayTwo
            val day3 = dayThree
            val day4 = dayFour
            val day5 = dayFife
            val day6 = daySix
            val day7 = daySeven
            val program1 = Program(0,program_name,day1,day2,day3,day4,day5,day6,day7)
            dao.addProgram(program1)
        }
    }
    fun allWorkouts(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            livedata.value = daoWorkout.getWorkoutsAll()
        }
    }
    fun addProgramWorkouts(program: ProgramWorkouts){
        val job = CoroutineScope(Dispatchers.Main).launch {
            daoProgramWorkouts.addProgramWorkout(program)
        }
    }
    fun getLastId(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            livedataLastID.value = daoProgramWorkouts.getLastID()
        }
    }
    fun updateProgram(program: Program){
        val job = CoroutineScope(Dispatchers.Main).launch {
            dao.updateProgram(program)
        }
    }
    fun getProgramWorkoutWithID(id:Int){
        val job = CoroutineScope(Dispatchers.Main).launch{
            livedataLastProgramWorkouts.value = daoProgramWorkouts.getProgramIDWorkoutsWithPW(id)
        }
    }

}