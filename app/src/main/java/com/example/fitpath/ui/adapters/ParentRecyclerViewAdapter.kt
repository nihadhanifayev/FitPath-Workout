package com.example.fitpath.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.data.model.ParentItem

class ParentRecyclerViewAdapter(private var mContext:Context,private var parentItemList:List<ParentItem>):
    RecyclerView.Adapter<ParentRecyclerViewAdapter.ParentRecyclerViewDesignObjects>() {

    inner class ParentRecyclerViewDesignObjects(design:View):RecyclerView.ViewHolder(design){

        val title:TextView
        val rv:RecyclerView
        val constraintLayout:ConstraintLayout

        init {
            title = design.findViewById(R.id.itemTv)
            rv = design.findViewById(R.id.child_rv)
            constraintLayout = design.findViewById(R.id.CL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentRecyclerViewDesignObjects {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design_parent,parent,false)
        return ParentRecyclerViewDesignObjects(design)
    }

    override fun getItemCount(): Int {
        return  parentItemList.size
    }

    override fun onBindViewHolder(holder: ParentRecyclerViewDesignObjects, position: Int) {
        val parent = parentItemList[position]

        holder.title.text = parent.title
        holder.rv.setHasFixedSize(true)
        holder.rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)

        val adapter = ChildRecyclerViewAdapter(mContext,parent.array)
        holder.rv.adapter = adapter


        val expandable = parent.isExpandable
        holder.rv.visibility = if (expandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener {
            parent.isExpandable = !parent.isExpandable
            notifyItemChanged(position)
        }
    }

}