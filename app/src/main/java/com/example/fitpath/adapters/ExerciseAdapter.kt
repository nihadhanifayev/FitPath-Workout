package com.example.fitpath.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.classes.Exercise
import com.example.fitpath.R

class ExerciseAdapter(private val mContext:Context,private var Exercises:List<Exercise>):
    RecyclerView.Adapter<ExerciseAdapter.CardDesignObjectsExercises>() {

    inner class CardDesignObjectsExercises(design:View):RecyclerView.ViewHolder(design){

        var cardViewExercise:CardView
        var exercise_title:TextView
        var exercise_set:TextView
        var exercise_rep:TextView
        var exercise_weight:TextView
        var exercise_rest:TextView

        init {
            cardViewExercise = design.findViewById(R.id.cardViewExercise)
            exercise_title = design.findViewById(R.id.textViewExerciseTitle)
            exercise_set = design.findViewById(R.id.textViewExerciseSet)
            exercise_rep = design.findViewById(R.id.textViewExerciseRep)
            exercise_weight = design.findViewById(R.id.textViewExerciseWeight)
            exercise_rest = design.findViewById(R.id.textViewExerciseRest)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsExercises {
        val design = LayoutInflater.from(mContext).inflate(R.layout.exercise_card_design,parent,false)
        return CardDesignObjectsExercises(design)
    }

    override fun getItemCount(): Int {
        return Exercises.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsExercises, position: Int) {
        var exercise =Exercises.get(position)

        holder.exercise_title.text = exercise.exercise_name
        holder.exercise_set.text = "Sets : "+exercise.set
        holder.exercise_rep.text = "Reps : "+exercise.rep
        holder.exercise_weight.text = "Weights(kg) : "+exercise.weight
        holder.exercise_rest.text = "Rests(min) : "+exercise.rest
    }

}