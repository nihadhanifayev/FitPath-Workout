package com.example.fitpath.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.classes.Program

class ProgramAdapter(private val mContext:Context,private var Programs:List<Program>):
    RecyclerView.Adapter<ProgramAdapter.CardDesignObjectsPrograms>() {

    inner class CardDesignObjectsPrograms(design:View):RecyclerView.ViewHolder(design){
        val program_name:TextView
        val cardView:CardView

        init {
            program_name = design.findViewById(R.id.textViewProgramTitle)
            cardView = design.findViewById(R.id.cardViewProgram)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsPrograms {
        val design = LayoutInflater.from(mContext).inflate(R.layout.program_card_design,parent,false)
        return CardDesignObjectsPrograms(design)
    }

    override fun getItemCount(): Int {
        return Programs.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsPrograms, position: Int) {
        val program = Programs.get(position)

        holder.program_name.text = program.program_name
    }

}