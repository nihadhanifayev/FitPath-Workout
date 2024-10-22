package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.DailyExerciseAdapter
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.databinding.FragmentDailyExerciseBinding
import com.example.fitpath.models.DailyExerciseFragmentViewModel
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyExerciseFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseBinding
    private lateinit var DailyExercises:ArrayList<DailyExercise>
    private lateinit var adapter:DailyExerciseAdapter
    private lateinit var viewmodel:DailyExerciseFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_daily_exercise,container,false)
        viewmodel.getDailyExercises()
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            DailyExercises = list as ArrayList<DailyExercise>
            adapter = DailyExerciseAdapter(requireContext(),DailyExercises)
            design.DailyExerciseRv.setHasFixedSize(true)
            design.adapter = adapter
        })
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:DailyExerciseFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }

}