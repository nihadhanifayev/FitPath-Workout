package com.example.fitpath.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.classes.Exercise
import com.example.fitpath.R

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
        val exercise = Exercises.get(position)
        val exercise_rep_split = exercise.exercise_rep.split(" ")
        val exercise_set_split = exercise.exercise_set.split(" ")
        val exercise_weight_split = exercise.exercise_weight.split(" ")
        var exercise_rep_text = ""
        var exercise_weight_text = ""
        var exercise_set_text = ""
        holder.exercise_title.text = exercise.exercise_name
        holder.imagedetail.setOnClickListener {
            val alertExerciseDetail = AlertDialog.Builder(mContext)
            val layout = LayoutInflater.from(mContext).inflate(R.layout.exercise_detail_alert,null)

            val sets: TextView = layout.findViewById(R.id.textViewExerciseSets)
            val reps:TextView = layout.findViewById(R.id.textViewExerciseReps)
            val weights:TextView = layout.findViewById(R.id.textViewExerciseWeights)
            for (rep in exercise_rep_split){
                exercise_rep_text+="$rep\n\n"
            }
            for (weight in exercise_weight_split){
                exercise_weight_text+="$weight\n\n"
            }
            for (set in exercise_set_split){
                exercise_set_text+="$set\n\n"
            }
            sets.text = "${exercise_set_text}"
            reps.text = "${exercise_rep_text}"
            weights.text = "${exercise_weight_text}"
            alertExerciseDetail.setTitle(exercise.exercise_name)
            alertExerciseDetail.setNegativeButton("Close"){dialoginterface,i -> }
            alertExerciseDetail.setView(layout)
            alertExerciseDetail.show()
        }
    }

}