package com.example.fitpath.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.classes.Program
import com.example.fitpath.roomDB.dao.ProgramDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramDetailFragmentViewModel @Inject constructor (var dao: ProgramDao):ViewModel() {
    var livedata = MutableLiveData<List<Program>>()

    init {
        livedata = MutableLiveData<List<Program>>()
    }
    fun createProgram(program_title:String,dayOne:String,dayTwo:String,dayThree:String,dayFour:String,dayFife:String,daySix:String,daySeven:String,){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val program_name = program_title
            val day1 = dayOne
            val day2 = dayTwo
            val day3 = dayThree
            val day4 = dayFour
            val day5 = dayFife
            val day6 = daySix
            val day7 = daySeven
            val program1 = Program(0,program_name,day1,day2,day3,day4,day5,day6,day7)
            dao.addProgram(program1)
        }
    }
}