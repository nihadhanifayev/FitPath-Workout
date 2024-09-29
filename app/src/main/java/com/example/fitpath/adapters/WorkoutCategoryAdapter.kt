package com.example.fitpath.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.classes.WorkoutCategory

class WorkoutCategoryAdapter(private val mContext:Context,private var Categories:List<WorkoutCategory>):
    RecyclerView.Adapter<WorkoutCategoryAdapter.CardDesignObjectsCategory>() {

    inner class CardDesignObjectsCategory(design:View):RecyclerView.ViewHolder(design){
        var category_name:TextView
        var cardViewCategory:CardView

        init {
            category_name = design.findViewById(R.id.textViewCategoryName)
            cardViewCategory = design.findViewById(R.id.cardViewCategory)
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
        var categori = Categories.get(position)

        holder.category_name.text = categori.category
    }

}