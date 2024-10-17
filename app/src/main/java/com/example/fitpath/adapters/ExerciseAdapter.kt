package com.example.fitpath.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.text.TextRunShaper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.classes.Exercise
import com.example.fitpath.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseAdapter(private val mContext:Context,private var Exercises:List<Exercise>):
    RecyclerView.Adapter<ExerciseAdapter.CardDesignObjectsExercises>() {

    inner class CardDesignObjectsExercises(design:View):RecyclerView.ViewHolder(design){

        var cardViewExercise:CardView
        var exercise_title:TextView
        var imagedetail:ImageView

        init {
            cardViewExercise = design.findViewById(R.id.cardViewExercise)
            exercise_title = design.findViewById(R.id.textViewExerciseTitle)
            imagedetail = design.findViewById(R.id.imageViewExerciseDetail)
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
        val exercise =Exercises.get(position)

        holder.exercise_title.text = exercise.exercise_name
        holder.imagedetail.setOnClickListener {
            val alertExerciseDetail = AlertDialog.Builder(mContext)
            val layout = LayoutInflater.from(mContext).inflate(R.layout.exercise_detail_alert,null)

            val sets: TextView = layout.findViewById(R.id.textViewExerciseSets)
            val reps:TextView = layout.findViewById(R.id.textViewExerciseReps)
            val weights:TextView = layout.findViewById(R.id.textViewExerciseWeights)
            val rest:TextView = layout.findViewById(R.id.textViewExerciseRests)

            sets.text = "Set : "+exercise.exercise_set
            reps.text = "Reps : "+exercise.exercise_rep
            weights.text = "Weights : "+exercise.exercise_weight
            rest.text = "Rests : "+exercise.exercise_rest
            alertExerciseDetail.setTitle(exercise.exercise_name)
            alertExerciseDetail.setNegativeButton("Close"){dialoginterface,i -> }
            alertExerciseDetail.setView(layout)
            alertExerciseDetail.show()
        }
    }

}