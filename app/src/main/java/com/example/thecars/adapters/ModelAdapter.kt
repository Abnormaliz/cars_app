package com.example.thecars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R

class ModelAdapter(
     private val models: List<String>,
     private val onModelClickListener: (String) -> Unit):
     RecyclerView.Adapter<ModelAdapter.ModelViewHolder>() {

    class ModelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.tv_name_model)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent,false )
         return ModelViewHolder(itemView)
     }

     override fun getItemCount(): Int {
         return models.size
     }

     override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.name.text = models[position]
         holder.itemView.setOnClickListener { onModelClickListener.invoke(models[position]) }


     }


 }