package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.DailyExerciseAdapter
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.databinding.FragmentDailyExerciseBinding
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.database.roomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DailyExerciseFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseBinding
    private lateinit var db:roomDatabase
    private lateinit var dailyE_Dao:DailyExerciseDao
    private lateinit var DailyExercises:ArrayList<DailyExercise>
    private lateinit var adapter:DailyExerciseAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentDailyExerciseBinding.inflate(inflater,container,false)

        db = roomDatabase.dataBaseAccess(requireContext())!!
        dailyE_Dao = db.getDailyExerciseDao()

        val job = CoroutineScope(Dispatchers.Main).launch {
            DailyExercises = dailyE_Dao.getDailyExercises() as ArrayList<DailyExercise>
            adapter = DailyExerciseAdapter(requireContext(),DailyExercises)
            design.DailyExerciseRv.setHasFixedSize(true)
            design.DailyExerciseRv.layoutManager = LinearLayoutManager(requireContext())
            design.DailyExerciseRv.adapter = adapter
        }



        return design.root

    }

}