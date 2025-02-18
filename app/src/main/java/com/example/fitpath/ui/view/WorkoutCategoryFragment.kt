package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.WorkoutCategoryAdapter
import com.example.fitpath.data.model.WorkoutCategory
import com.example.fitpath.databinding.FragmentWorkoutCategoryBinding
import com.example.fitpath.ui.viewmodels.WorkoutCategoryFragmentViewModel
import com.example.fitpath.ui.viewmodels.ActivityTrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


@AndroidEntryPoint
class WorkoutCategoryFragment : Fragment() {
    private lateinit var design:FragmentWorkoutCategoryBinding
    private lateinit var adapter: WorkoutCategoryAdapter
    private lateinit var categories:ArrayList<WorkoutCategory>
    private val viewmodel: WorkoutCategoryFragmentViewModel by activityViewModels()
    private val viewmodelActivityTraining: ActivityTrainingViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_workout_category,container,false)
        design.workoutCategoryObject = this

        categories = ArrayList<WorkoutCategory>()
        viewmodel.getCategories()
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            categories = list as ArrayList<WorkoutCategory>
            adapter = WorkoutCategoryAdapter(viewLifecycleOwner,requireContext(),categories,viewmodel)
            design.WorkoutCategoryRv.setHasFixedSize(true)
            design.adapter = adapter
        })

        design.status = viewmodelActivityTraining.timerStatus
        if (!viewmodelActivityTraining.timerStatus){
            setUpTimerCollect()
        }
        return design.root
    }
    fun goTrainingFragment(){
        Navigation.findNavController(design.buttonTrainingFragment).navigate(R.id.workoutCategory_ActivityFragment)
    }
    private fun setUpTimerCollect(){
        viewLifecycleOwner.lifecycleScope.launch() {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    viewmodelActivityTraining.secondFlowImmutable,
                    viewmodelActivityTraining.minuteFlowImmutable,
                    viewmodelActivityTraining.hourFlowImmutable) { second,minute,hour -> Triple(second,minute,hour)}.collect{ (second,minute,hour) ->

                    design.second = second
                    design.minute = minute
                    design.hour = hour
                }
            }
        }
    }
}