package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.WorkoutCategoryAdapter
import com.example.fitpath.classes.WorkoutCategory
import com.example.fitpath.databinding.FragmentWorkoutCategoryBinding
import com.example.fitpath.models.WorkoutCategoryFragmentViewModel
import com.example.fitpath.roomDB.dao.WorkoutCategoryDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


@AndroidEntryPoint
class WorkoutCategoryFragment : Fragment() {
    private lateinit var design:FragmentWorkoutCategoryBinding
    private lateinit var adapter:WorkoutCategoryAdapter
    private lateinit var Categories:ArrayList<WorkoutCategory>
    private lateinit var viewmodel:WorkoutCategoryFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_workout_category,container,false)
        Categories = ArrayList<WorkoutCategory>()
        viewmodel.getCategories()
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            Categories = list as ArrayList<WorkoutCategory>
            adapter = WorkoutCategoryAdapter(requireContext(),Categories)
            design.WorkoutCategoryRv.setHasFixedSize(true)
            design.adapter = adapter
        })
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:WorkoutCategoryFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }

}