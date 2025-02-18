package com.example.fitpath.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.data.model.Workout
import com.example.fitpath.data.model.WorkoutCategory
import com.example.fitpath.ui.view.WorkoutCategoryFragmentDirections
import com.example.fitpath.ui.viewmodels.WorkoutCategoryFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutCategoryAdapter(private val viewLifeCycleOwner:LifecycleOwner,private val mContext:Context, private var Categories:List<WorkoutCategory>, private var viewmodel: WorkoutCategoryFragmentViewModel):
    RecyclerView.Adapter<WorkoutCategoryAdapter.CardDesignObjectsCategory>() {

    inner class CardDesignObjectsCategory(design:View):RecyclerView.ViewHolder(design){
        var category_name:TextView
        var cardViewCategory:CardView
        var rv:RecyclerView

        init {
            category_name = design.findViewById(R.id.textViewCategoryName)
            cardViewCategory = design.findViewById(R.id.cardViewCategory)
            rv = design.findViewById(R.id.smallPicRV)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsCategory {
        val design = LayoutInflater.from(mContext).inflate(R.layout.workout_category_card_design,parent,false)
        return CardDesignObjectsCategory(design)
    }

    override fun getItemCount(): Int {
        return Categories.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsCategory, position: Int) {
        val category = Categories.get(position)
        var selectedCategories = ArrayList<Workout>()
        val job = CoroutineScope(Dispatchers.Main).launch {
            selectedCategories = viewmodel.getWorkouts(category.category)
            val adapter = WorkoutSmallPictureAdapter(mContext, selectedCategories)
            holder.rv.setHasFixedSize(true)
            holder.rv.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            holder.rv.adapter = adapter
        }
        holder.category_name.text = category.category
        holder.cardViewCategory.setOnClickListener {
            val send_category = WorkoutCategoryFragmentDirections.workoutCategoryFragmentWorkoutsFragment(category.category)
            Navigation.findNavController(it).navigate(send_category)
        }

    }

}