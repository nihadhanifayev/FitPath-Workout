package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fitpath.data.model.ProgramWorkouts
import com.example.fitpath.data.dao.ProgramDao
import com.example.fitpath.data.dao.ProgramWorkoutsDao
import com.example.fitpath.data.dao.WorkoutDao
import com.example.fitpath.data.model.ChildItem
import com.example.fitpath.data.model.ParentItem
import com.example.fitpath.data.model.Program
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgramDetailsOverviewFragmentViewModel @Inject constructor (var dao: ProgramDao, var daoWorkout: WorkoutDao, var daoProgramWorkouts: ProgramWorkoutsDao) :ViewModel() {
    var childList1=ArrayList<ChildItem>()
    var childList2=ArrayList<ChildItem>()
    var childList3=ArrayList<ChildItem>()
    var childList4=ArrayList<ChildItem>()
    var childList5=ArrayList<ChildItem>()
    var childList6=ArrayList<ChildItem>()
    var childList7=ArrayList<ChildItem>()
    var childListManager=ArrayList<ArrayList<ChildItem>>()
    var programArray=ArrayList<ParentItem>()

    var list = ArrayList<ProgramWorkouts>()
    init {
        childListManagerCollection()
    }
    suspend fun getProgramWorkoutsWithId(id:Int,day: Int){
        list = daoProgramWorkouts.getProgramIDWorkouts(id,day) as ArrayList<ProgramWorkouts>
    }
    private fun childListManagerCollection(){
        childListManager.add(childList1)
        childListManager.add(childList2)
        childListManager.add(childList3)
        childListManager.add(childList4)
        childListManager.add(childList5)
        childListManager.add(childList6)
        childListManager.add(childList7)
    }
    fun parentItemCollection(program:Program){
        val parentItem1 = ParentItem(program.dayOne,childList1,false)
        val parentItem2 = ParentItem(program.dayTwo,childList2,false)
        val parentItem3 = ParentItem(program.dayThree,childList3,false)
        val parentItem4 = ParentItem(program.dayFour,childList4,false)
        val parentItem5 = ParentItem(program.dayFife,childList5,false)
        val parentItem6 = ParentItem(program.daySix,childList6,false)
        val parentItem7 = ParentItem(program.daySeven,childList7,false)
        programArray.add(parentItem1)
        programArray.add(parentItem2)
        programArray.add(parentItem3)
        programArray.add(parentItem4)
        programArray.add(parentItem5)
        programArray.add(parentItem6)
        programArray.add(parentItem7)
    }


}