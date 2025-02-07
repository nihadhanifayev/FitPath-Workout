package com.example.fitpath.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpath.R
import com.example.fitpath.data.model.ChildItem

class ChildRecyclerViewAdapter(private val mContext:Context,private var ChildRecyclerViewList:List<ChildItem>):
    RecyclerView.Adapter<ChildRecyclerViewAdapter.ChildRecyclerViewDesignObjects>() {

    inner class ChildRecyclerViewDesignObjects(design: View):RecyclerView.ViewHolder(design){
        val title:TextView
        val img:ImageView

        init {
            title = design.findViewById(R.id.ChildTextView)
            img = design.findViewById(R.id.imageViewChild)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildRecyclerViewDesignObjects {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design_child,parent,false)
        return ChildRecyclerViewDesignObjects(design)
    }

    override fun getItemCount(): Int {
        return ChildRecyclerViewList.size
    }

    override fun onBindViewHolder(holder: ChildRecyclerViewDesignObjects, position: Int) {
        val child = ChildRecyclerViewList[position]

        holder.title.text = child.title
        holder.img.setImageResource(mContext.resources.getIdentifier(child.img,"drawable",mContext.packageName))
    }

}