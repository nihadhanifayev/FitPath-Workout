package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpath.data.model.Program
import com.example.fitpath.data.dao.ProgramDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyProgramFragmentViewModel @Inject constructor (var dao: ProgramDao):ViewModel() {
    var livedata = MutableLiveData<List<Program>>()
    init {
        livedata = MutableLiveData<List<Program>>()
    }

    fun getPrograms(){
        viewModelScope.launch {
            livedata.value = dao.getPrograms()
        }
    }
    fun deleteProgram(program: Program){
        viewModelScope.launch {
            dao.deleteProgram(program)
            getPrograms()
        }
    }
}