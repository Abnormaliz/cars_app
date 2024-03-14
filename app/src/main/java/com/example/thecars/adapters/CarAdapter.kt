package com.example.thecars.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.classes.Car
import com.example.thecars.R
import com.example.thecars.interfaces.OnCarClickListener

class CarAdapter(
    private var cars: List<Car>,
    private val listener: OnCarClickListener
) :
    RecyclerView.Adapter<CarAdapter.CarsViewHolder>() {

    class CarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logos: ImageView = itemView.findViewById(R.id.iv_logos)
        val title: TextView = itemView.findViewById(R.id.tv_name_model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarsViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val currentCar = cars[position]
        if (cars[position].modelList.isEmpty()) {
            holder.logos.setBackgroundColor(R.color.black_overlay)
        } else {
            holder.logos.setBackgroundColor(android.R.color.transparent)
        }
        holder.logos.setImageResource(currentCar.imageId)
        holder.title.text = currentCar.title
        holder.itemView.setOnClickListener() {
            listener.onCarClick(currentCar)
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCarList: List<Car>) {
        cars = newCarList
        notifyDataSetChanged()
    }
}


