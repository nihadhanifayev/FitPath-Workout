package com.example.fitpath.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.data.model.Exercise
import com.example.fitpath.R
import java.util.Locale

class ExerciseAdapter(private val mContext:Context,private var Exercises:List<Exercise>):
    RecyclerView.Adapter<ExerciseAdapter.CardDesignObjectsExercises>() {

    inner class CardDesignObjectsExercises(design:View):RecyclerView.ViewHolder(design){

        var cardViewExercise:CardView
        var exerciseTitle:TextView
        var imageDetail:ImageView

        init {
            cardViewExercise = design.findViewById(R.id.cardViewExercise)
            exerciseTitle = design.findViewById(R.id.textViewExerciseTitle)
            imageDetail = design.findViewById(R.id.imageViewExerciseDetail)
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
        val exercise = Exercises[position]
        val exerciseRepSplit = exercise.exerciseRep.split(" ")
        val exerciseSetSplit = exercise.exerciseSet.split(" ")
        val exerciseWeightSplit = exercise.exerciseWeight.split(" ")
        var exerciseRepText = ""
        var exerciseWeightText = ""
        var exerciseSetText = ""
        holder.exerciseTitle.text = exercise.exerciseName
        holder.imageDetail.setOnClickListener {
            exerciseRepText = ""
            exerciseWeightText = ""
            exerciseSetText = ""
            val alertExerciseDetail = AlertDialog.Builder(mContext)
            val layout = LayoutInflater.from(mContext).inflate(R.layout.exercise_detail_alert,null)

            val sets: TextView = layout.findViewById(R.id.textViewExerciseSets)
            val reps:TextView = layout.findViewById(R.id.textViewExerciseReps)
            val weights:TextView = layout.findViewById(R.id.textViewExerciseWeights)
            for (rep in exerciseRepSplit){
                exerciseRepText+="$rep\n\n"
            }
            for (weight in exerciseWeightSplit){
                exerciseWeightText+="$weight\n\n"
            }
            for (set in exerciseSetSplit){
                if (set == "") {
                    continue
                }
                exerciseSetText+="${set.toInt()-1}\n\n"
            }
            sets.text = exerciseSetText
            reps.text = exerciseRepText
            weights.text = exerciseWeightText
            alertExerciseDetail.setTitle(exercise.exerciseName.uppercase(Locale.ROOT))
            alertExerciseDetail.setNegativeButton("Close"){dialoginterface,i -> }
            alertExerciseDetail.setView(layout)
            alertExerciseDetail.show()
        }
    }

}