package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.DailyExerciseAdapter
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.databinding.FragmentDailyExerciseBinding
import com.example.fitpath.ui.viewmodels.DailyExerciseFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyExerciseFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseBinding
    private lateinit var DailyExercises:ArrayList<DailyExercise>
    private lateinit var adapter: DailyExerciseAdapter
    private lateinit var viewmodel: DailyExerciseFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_exercise,container,false)
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            DailyExercises = list as ArrayList<DailyExercise>
            adapter = DailyExerciseAdapter(requireContext(),DailyExercises)
            design.DailyExerciseRv.setHasFixedSize(true)
            design.adapter = adapter
        })
        viewmodel.getDailyExercises()
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DailyExerciseFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }

}