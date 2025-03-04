package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.data.model.Exercise
import com.example.fitpath.data.dao.DailyExerciseDao
import com.example.fitpath.data.dao.ExerciseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyExerciseDetailFragmentViewModel @Inject constructor (var dao: DailyExerciseDao, var daoExercise: ExerciseDao): ViewModel() {
    var livedata = MutableLiveData<List<Exercise>>()

    init {
        livedata = MutableLiveData<List<Exercise>>()
    }
    fun getExercise(id:Int){
        viewModelScope.launch {
            livedata.value = daoExercise.getExercises(id)
        }
    }
    fun deleteDailyExercise(exercise: DailyExercise){
        viewModelScope.launch {
            dao.deleteDailyExercise(exercise)
        }
    }
}