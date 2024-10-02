package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.WorkoutCategoryAdapter
import com.example.fitpath.classes.WorkoutCategory
import com.example.fitpath.databinding.FragmentWorkoutCategoryBinding
import com.example.fitpath.roomDB.dao.WorkoutCategoryDao
import com.example.fitpath.roomDB.database.roomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class WorkoutCategoryFragment : Fragment() {
    private lateinit var design:FragmentWorkoutCategoryBinding
    private lateinit var adapter:WorkoutCategoryAdapter
    private lateinit var Categories:ArrayList<WorkoutCategory>
    private lateinit var db:roomDatabase
    private lateinit var wkCDao:WorkoutCategoryDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentWorkoutCategoryBinding.inflate(inflater,container,false)

        Categories = ArrayList<WorkoutCategory>()
        db = roomDatabase.dataBaseAccess(requireContext())!!
        wkCDao = db.getWorkoutCategoryDao()

        val job = CoroutineScope(Dispatchers.Main).launch {
            Categories = wkCDao.getCategory() as ArrayList<WorkoutCategory>
            adapter = WorkoutCategoryAdapter(requireContext(),Categories)
            design.WorkoutCategoryRv.setHasFixedSize(true)
            design.WorkoutCategoryRv.layoutManager = LinearLayoutManager(requireContext())
            design.WorkoutCategoryRv.adapter = adapter
        }

        return design.root
    }

}