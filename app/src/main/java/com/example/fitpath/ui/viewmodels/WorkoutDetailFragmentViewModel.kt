package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.data.model.Workout
import com.example.fitpath.data.dao.WorkoutDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailFragmentViewModel @Inject constructor (var dao: WorkoutDao):ViewModel() {

    var workoutLiveData=MutableLiveData<List<Workout>>()

    init {
        workoutLiveData=MutableLiveData<List<Workout>>()
    }

    fun getCategories(category:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            workoutLiveData.value = dao.getWorkouts(category)
        }
    }
}