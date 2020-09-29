package com.example.a17_lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(val activity: MainActivity): RecyclerView.Adapter<ListViewHolder>() {

    var models: MutableList<User> = mutableListOf()

    fun setData(data: MutableList<User>){
        models = data
        notifyDataSetChanged()
    }

    fun addUser(position: Int, title: String, descripton: String){
        models.add(position, User(title,descripton))
        notifyItemInserted(position)
        notifyItemRangeChanged(position,models.size)
    }

    fun removeUser(position: Int){
        models.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, models.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.populateModel(models[position], position, activity)
    }

}