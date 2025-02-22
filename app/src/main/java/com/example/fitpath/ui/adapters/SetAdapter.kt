package com.example.fitpath.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.data.model.Set

class SetAdapter(private var mContext:Context,private var SetList:List<Set>):RecyclerView.Adapter<SetAdapter.cardDesignObjectsSet>() {

    inner class cardDesignObjectsSet(design: View):RecyclerView.ViewHolder(design){

        var card:CardView
        var SetText:TextView
        var setDetail:ImageView

        init {
            card = design.findViewById(R.id.cardViewSet)
            SetText = design.findViewById(R.id.textViewSetTitle)
            setDetail = design.findViewById(R.id.imageViewSetDetail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardDesignObjectsSet {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design_set,parent,false)
        return cardDesignObjectsSet(design)
    }

    override fun getItemCount(): Int {
        return SetList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: cardDesignObjectsSet, position: Int) {
        val set = SetList[position]
        holder.setDetail.setOnClickListener {
            val alertD = AlertDialog.Builder(mContext)
            alertD.setMessage("${set.setRep} X ${set.setWeight}")
            alertD.setTitle("SET ${set.setNo.toInt()-1}")
            alertD.show()
        }
        holder.SetText.text = "SET ${set.setNo.toInt()-1}"
    }


}