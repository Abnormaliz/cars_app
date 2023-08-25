package com.example.thecars

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.CarsViewHolder>() {

    class CarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logos: ImageView = itemView.findViewById(R.id.iv_logos)
        val title: TextView = itemView.findViewById(R.id.tv_name_model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.logos.setImageResource(cars[position].imageId)
        holder.title.text = cars[position].title
        holder.itemView.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, CarsFragment.newInstance()).commit()
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }
}


