package com.example.fitpath.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.WorkoutCategoryFragmentDirections
import com.example.fitpath.classes.Workout
import com.google.android.material.animation.AnimationUtils

class WorkoutSmallPictureAdapter(private var mContext:Context,private var workouts:List<Workout>):RecyclerView.Adapter<WorkoutSmallPictureAdapter.CardDesignObjectsSmallPic>() {

    inner class CardDesignObjectsSmallPic(design: View):RecyclerView.ViewHolder(design){
        val imageWorkout:ImageView
        val cardViewSMALLPIC:CardView
        init {
            imageWorkout = design.findViewById(R.id.imageViewWorkoutSmallPic)
            cardViewSMALLPIC = design.findViewById(R.id.cardViewWorkoutSmallPic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsSmallPic {
        val design = LayoutInflater.from(mContext).inflate(R.layout.workout_category_workouts_card_design,parent,false)
        return CardDesignObjectsSmallPic(design)
    }

    override fun getItemCount(): Int {
        return workouts.size
    }
    override fun onBindViewHolder(holder: CardDesignObjectsSmallPic, position: Int) {
        val workout = workouts.get(position)
        holder.cardViewSMALLPIC.startAnimation(android.view.animation.AnimationUtils.loadAnimation(holder.itemView.context,R.anim.workout_small_pic_anim))
        holder.imageWorkout.setImageResource(mContext.resources.getIdentifier(workout.workoutImage,"drawable",mContext.packageName))
    }

}