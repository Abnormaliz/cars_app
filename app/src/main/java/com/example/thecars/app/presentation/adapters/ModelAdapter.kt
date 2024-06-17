package com.example.thecars.app.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R
import com.example.thecars.app.presentation.interfaces.OnModelClickListener
import com.example.thecars.app.presentation.models.ModelUi

class ModelAdapter(
    private var models: List<ModelUi>,
    private val listener: OnModelClickListener
):
     RecyclerView.Adapter<ModelAdapter.ModelViewHolder>() {

    class ModelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.tv_name_model)
        val emptyCard: FrameLayout = itemView.findViewById(R.id.empty_card)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent,false )
         return ModelViewHolder(itemView)
     }

     override fun getItemCount(): Int {
         return models.size
     }

     @SuppressLint("ResourceAsColor")
     override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.name.text = models[position].name
         if (models[position].list.isEmpty()) {
             holder.emptyCard.alpha = 0.5F
         } else {
             holder.emptyCard.alpha = 0F
         }
         holder.itemView.setOnClickListener {
             listener.onModelClick(models[position])
         }
     }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newModelList: List<ModelUi>) {
        models = newModelList
        notifyDataSetChanged()
    }


 }