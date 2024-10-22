package com.example.fitpath.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.classes.WorkoutCategory
import com.example.fitpath.roomDB.dao.WorkoutCategoryDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutCategoryFragmentViewModel @Inject constructor (var dao:WorkoutCategoryDao):ViewModel() {
    var livedata = MutableLiveData<List<WorkoutCategory>>()

    init {
        livedata = MutableLiveData<List<WorkoutCategory>>()
    }

    fun getCategories(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            livedata.value = dao.getCategory()
        }
    }

}