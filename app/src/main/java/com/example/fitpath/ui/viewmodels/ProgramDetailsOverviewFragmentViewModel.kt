package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
class ProgramDetailsOverviewFragmentViewModel @Inject constructor (var dao: ProgramDao, var daoWorkout: WorkoutDao, var daoProgramWorkouts: ProgramWorkoutsDao) :ViewModel() {

    var liveDataProgramWorkoutsone = MutableLiveData<List<ProgramWorkouts>>()
    var liveDataProgramWorkoutstwo = MutableLiveData<List<ProgramWorkouts>>()
    var liveDataProgramWorkoutsthree = MutableLiveData<List<ProgramWorkouts>>()
    var liveDataProgramWorkoutsfour = MutableLiveData<List<ProgramWorkouts>>()
    var liveDataProgramWorkoutsfife = MutableLiveData<List<ProgramWorkouts>>()
    var liveDataProgramWorkoutssix = MutableLiveData<List<ProgramWorkouts>>()
    var liveDataProgramWorkoutsseven = MutableLiveData<List<ProgramWorkouts>>()

    init {
        liveDataProgramWorkoutsone = MutableLiveData<List<ProgramWorkouts>>()
        liveDataProgramWorkoutstwo = MutableLiveData<List<ProgramWorkouts>>()
        liveDataProgramWorkoutsthree = MutableLiveData<List<ProgramWorkouts>>()
        liveDataProgramWorkoutsfour = MutableLiveData<List<ProgramWorkouts>>()
        liveDataProgramWorkoutsfife = MutableLiveData<List<ProgramWorkouts>>()
        liveDataProgramWorkoutssix = MutableLiveData<List<ProgramWorkouts>>()
        liveDataProgramWorkoutsseven = MutableLiveData<List<ProgramWorkouts>>()
    }

    fun getProgramWorkoutsWithIdOne(id:Int,day:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            liveDataProgramWorkoutsone.value = daoProgramWorkouts.getProgramIDWorkouts(id,day)
        }
    }
    fun getProgramWorkoutsWithIdTwo(id:Int,day:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            liveDataProgramWorkoutstwo.value = daoProgramWorkouts.getProgramIDWorkouts(id,day)
        }
    }
    fun getProgramWorkoutsWithIdThree(id:Int,day:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            liveDataProgramWorkoutsthree.value = daoProgramWorkouts.getProgramIDWorkouts(id,day)
        }
    }
    fun getProgramWorkoutsWithIdFour(id:Int,day:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            liveDataProgramWorkoutsfour.value = daoProgramWorkouts.getProgramIDWorkouts(id,day)
        }
    }
    fun getProgramWorkoutsWithIdFife(id:Int,day:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            liveDataProgramWorkoutsfife.value = daoProgramWorkouts.getProgramIDWorkouts(id,day)
        }
    }
    fun getProgramWorkoutsWithIdSix(id:Int,day:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            liveDataProgramWorkoutssix.value = daoProgramWorkouts.getProgramIDWorkouts(id,day)
        }
    }
    fun getProgramWorkoutsWithIdSeven(id:Int,day:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            liveDataProgramWorkoutsseven.value = daoProgramWorkouts.getProgramIDWorkouts(id,day)
        }
    }


}