package com.example.a17_lesson

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.tvTitle
    private val surname: TextView = itemView.tvDescripton



    fun populateModel(user: User, position: Int, activity: MainActivity){
        name.text = user.title
        surname.text = user.description

        itemView.btnOptions.setOnClickListener{
            activity.onOptionsButtonClicked(itemView.btnOptions, position)
        }
    }
}