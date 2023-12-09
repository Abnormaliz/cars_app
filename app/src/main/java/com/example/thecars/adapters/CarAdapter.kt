package com.example.thecars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.CarsFragment
import com.example.thecars.classes.Car
import com.example.thecars.R
import com.example.thecars.interfaces.OnCarClickListener

class CarAdapter(
    private val cars: List<Car>,
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

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val currentCar = cars[position]
        holder.logos.setImageResource(currentCar.imageId)
        holder.title.text = currentCar.title
        holder.itemView.setOnClickListener() {
            listener.onCarClick(currentCar)
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }

}


