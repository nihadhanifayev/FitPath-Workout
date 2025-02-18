package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpath.data.model.WorkoutCategory
import com.example.fitpath.data.dao.WorkoutCategoryDao
import com.example.fitpath.data.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutCategoryFragmentViewModel @Inject constructor (var dao: WorkoutCategoryDao):ViewModel() {
    var livedata = MutableLiveData<List<WorkoutCategory>>()
    var listWorkout = ArrayList<Workout>()
    init {
        livedata = MutableLiveData<List<WorkoutCategory>>()
    }

    fun getCategories(){
        viewModelScope.launch {
            livedata.value = dao.getCategory()
        }
    }
    suspend fun getWorkouts(category:String):ArrayList<Workout>{
        listWorkout = dao.getWorkouts(category) as ArrayList<Workout>
        return listWorkout
    }

}