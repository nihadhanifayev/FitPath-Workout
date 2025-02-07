package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.WorkoutsAdapter
import com.example.fitpath.data.model.Workout
import com.example.fitpath.databinding.FragmentWorkoutsBinding
import com.example.fitpath.ui.viewmodels.WorkoutsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class WorkoutsFragment : Fragment() {
    private lateinit var design:FragmentWorkoutsBinding
    private lateinit var viewmodel: WorkoutsFragmentViewModel
    private lateinit var adapter: WorkoutsAdapter
    private lateinit var workouts:ArrayList<Workout>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_workouts,container,false)
        val bundle:WorkoutsFragmentArgs by navArgs()
        val category = bundle.category
        workouts = ArrayList<Workout>()
        viewmodel.getCategories(category)
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            workouts = list as ArrayList<Workout>
            adapter = WorkoutsAdapter(requireContext(),workouts)
            design.WorkoutRV.setHasFixedSize(true)
            design.workoutsAdapter = adapter
        })

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: WorkoutsFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }


}