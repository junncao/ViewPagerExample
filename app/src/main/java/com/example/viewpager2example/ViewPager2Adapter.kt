package com.example.viewpager2example

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPager2Adapter(private val context: Context,
                        private val listItem: List<String>): RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_view_pager_itemview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.rvItems).text = listItem[position]
        holder.itemView.setBackgroundColor(
        when(position){
            0 -> context.getColor(R.color.purple_500)
            1 -> context.getColor(R.color.teal_700)
            2 -> context.getColor(R.color.design_default_color_primary)
            3 -> context.getColor(R.color.design_default_color_error)
            else -> context.getColor(R.color.white)
        })
    }

    override fun getItemCount(): Int = listItem.size
}