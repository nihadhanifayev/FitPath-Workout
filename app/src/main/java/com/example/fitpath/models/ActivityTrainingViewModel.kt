package com.example.fitpath.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.classes.Exercise
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.dao.ExerciseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityTrainingViewModel @Inject constructor (var dao: DailyExerciseDao, var daoExercise: ExerciseDao):ViewModel() {

    var livedataLastExercise = MutableLiveData<DailyExercise>()
    var livedataExercises = MutableLiveData<List<Exercise>>()

    init {
        livedataLastExercise = MutableLiveData<DailyExercise>()
        livedataExercises = MutableLiveData<List<Exercise>>()
    }
    fun getExercises(dailyExercise_LastID:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            livedataExercises.value = daoExercise.getExercises(dailyExercise_LastID)
        }
    }
    fun getLastID(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val lastID = dao.getLastID()
            livedataLastExercise.value = lastID
        }
    }
    fun addExercise(exercise:Exercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            daoExercise.addExercise(exercise)
        }
    }
    fun addDailyExercise(dailyExercise:DailyExercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            dao.addDailyExercise(dailyExercise)
        }
    }
    fun updateDailyExercise(dailyExercise: DailyExercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            dao.updateDailyExercise(dailyExercise)
        }
    }
    fun deleteDailyExercise(dailyExercise: DailyExercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            dao.deleteDailyExercise(dailyExercise)
        }
    }

}