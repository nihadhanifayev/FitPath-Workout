package com.example.fitpath.ui.adapters

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.WeeklyProgramFragmentDirections
import com.example.fitpath.data.model.Program
import com.example.fitpath.ui.viewmodels.WeeklyProgramFragmentViewModel

class ProgramAdapter(private val mContext:Context, private var Programs:List<Program>, private var viewmodel: WeeklyProgramFragmentViewModel):
    RecyclerView.Adapter<ProgramAdapter.CardDesignObjectsPrograms>() {

    inner class CardDesignObjectsPrograms(design:View):RecyclerView.ViewHolder(design){
        val program_name:TextView
        val cardView:CardView
        val more_image:ImageView
        val progressBar:ProgressBar

        init {
            program_name = design.findViewById(R.id.textViewProgramTitle)
            cardView = design.findViewById(R.id.cardViewProgram)
            more_image = design.findViewById(R.id.imageViewMore)
            progressBar = design.findViewById(R.id.progressBarProgramChange)
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
        holder.progressBar.visibility = View.INVISIBLE
        holder.program_name.text = program.program_name
        holder.cardView.setOnClickListener {
            val transition = WeeklyProgramFragmentDirections.weeklyProgramFProgramOverview(program)
            Navigation.findNavController(it).navigate(transition)
        }
        holder.more_image.setOnClickListener {
            val transition1 = WeeklyProgramFragmentDirections.weeklyProgramFAddProgramF(program)
            val popupmenu = PopupMenu(mContext,holder.more_image)
            popupmenu.menuInflater.inflate(R.menu.program_card_menu,popupmenu.menu)
            popupmenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.action_program_change -> {
                        holder.progressBar.visibility = View.VISIBLE
                        Handler().postDelayed({Navigation.findNavController(it).navigate(transition1)},3000)
                        true
                    }
                    R.id.action_program_delete -> {
                        viewmodel.deleteProgram(program)
                        true
                    }
                    else -> {false}
                }
            })
            popupmenu.show()
        }
    }

}