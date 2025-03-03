package com.example.fitpath.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.ui.view.DailyExerciseFragmentDirections

class DailyExerciseAdapter(private val mContext:Context,private var ExerciseList:ArrayList<DailyExercise>):RecyclerView.Adapter<DailyExerciseAdapter.CardDesignObjectsDailyExercise>() {

    inner class CardDesignObjectsDailyExercise(design:View):RecyclerView.ViewHolder(design){

        var training_name:TextView
        var training_time:TextView
        var training_date:TextView
        var cardViewExercise:CardView

        init {
            training_name = design.findViewById(R.id.textViewDailyExerciseName)
            training_time = design.findViewById(R.id.textViewTrainingTime)
            training_date = design.findViewById(R.id.textViewDay)
            cardViewExercise = design.findViewById(R.id.cardViewExercise)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsDailyExercise {
        val design = LayoutInflater.from(mContext).inflate(R.layout.daily_exercise_card_design,parent,false)
        return CardDesignObjectsDailyExercise(design)
    }

    override fun getItemCount(): Int {
        return ExerciseList.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsDailyExercise, position: Int) {
        val exercise = ExerciseList.get(position)

        holder.training_name.text = exercise.title
        holder.training_time.text = exercise.time
        holder.training_date.text = exercise.date

        holder.cardViewExercise.setOnClickListener {
            val transition = DailyExerciseFragmentDirections.dailyExerciseFDailyExerciseFDetail(exercise)
            Navigation.findNavController(it).navigate(transition)
        }
    }

}