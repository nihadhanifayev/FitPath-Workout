package com.example.fitpath.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyExerciseFragmentViewModel @Inject constructor (var dao: DailyExerciseDao):ViewModel() {
    var livedata = MutableLiveData<List<DailyExercise>>()

    init {
        livedata = MutableLiveData<List<DailyExercise>>()
    }
    fun getDailyExercises(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            livedata.value = dao.getDailyExercises()
        }
    }
}