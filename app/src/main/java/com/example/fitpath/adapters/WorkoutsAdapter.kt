package com.example.fitpath.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.fitpath.R
import com.example.fitpath.WorkoutsFragmentDirections
import com.example.fitpath.classes.Workout

class WorkoutsAdapter(private var mContext:Context,private var Workouts:List<Workout>):
    RecyclerView.Adapter<WorkoutsAdapter.CardDesignObjectsWorkout>() {

    inner class CardDesignObjectsWorkout(design:View):RecyclerView.ViewHolder(design){
        var workoutTitle:TextView
        var workoutInfo:TextView
        var workoutImage:ImageView
        var cardViewWorkout:CardView

        init {
            workoutTitle = design.findViewById(R.id.textViewWorkoutCardTitle)
            workoutInfo = design.findViewById(R.id.textViewWorkoutCardInfo)
            workoutImage = design.findViewById(R.id.imageViewWorkoutCardImage)
            cardViewWorkout = design.findViewById(R.id.cardViewWorkout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsWorkout {
        val design = LayoutInflater.from(mContext).inflate(R.layout.workouts_card_design,parent,false)
        return CardDesignObjectsWorkout(design)
    }

    override fun getItemCount(): Int {
        return Workouts.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsWorkout, position: Int) {
        val workout = Workouts.get(position)
        holder.workoutTitle.text = workout.workout
        holder.workoutInfo.text = workout.workoutInfo
        holder.workoutImage.setImageResource(mContext.resources.getIdentifier(workout.workoutImage,"drawable",mContext.packageName))
        holder.cardViewWorkout.startAnimation(android.view.animation.AnimationUtils.loadAnimation(holder.itemView.context,R.anim.workout_small_pic_anim))
        holder.cardViewWorkout.setOnClickListener {
            val send_exercise = WorkoutsFragmentDirections.workoutsFragmentWorkoutDetailFragment(workout)
            Navigation.findNavController(it).navigate(send_exercise)
        }
    }

}