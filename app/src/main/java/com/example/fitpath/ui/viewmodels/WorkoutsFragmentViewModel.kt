package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpath.data.model.Workout
import com.example.fitpath.data.dao.WorkoutDao
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
        viewModelScope.launch {
            livedata.value = dao.getWorkouts(category)
        }
    }

}