package com.example.fitpath.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.adapters.WorkoutsAdapter
import com.example.fitpath.classes.Workout
import com.example.fitpath.classes.WorkoutCategory
import com.example.fitpath.roomDB.dao.WorkoutCategoryDao
import com.example.fitpath.roomDB.dao.WorkoutDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsFragmentViewModel @Inject constructor (var dao: WorkoutDao):ViewModel() {

    var livedata = MutableLiveData<List<Workout>>()

    init {
        livedata = MutableLiveData<List<Workout>>()
    }

    fun getCategories(category:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            livedata.value = dao.getWorkouts(category)
        }
    }

}