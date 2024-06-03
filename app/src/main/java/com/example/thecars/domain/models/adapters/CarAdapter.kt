package com.example.thecars.domain.models.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.domain.models.classes.Car
import com.example.thecars.R
import com.example.thecars.domain.models.interfaces.OnCarClickListener

class CarAdapter (
    private var cars: List<Car>,
    private val listener: OnCarClickListener
): RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_car)
        val image: ImageView = itemView.findViewById(R.id.item_car_image)
        val emptyCard: FrameLayout = itemView.findViewById(R.id.empty_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val currentCar = cars[position]
        if (cars[position].backPhoto == 0) {
            holder.emptyCard.alpha = 0.5F
        } else {
            holder.emptyCard.alpha = 0F
        }
        holder.title.text = currentCar.name
        holder.image.setImageResource(currentCar.previewPhoto)
        holder.itemView.setOnClickListener { listener.onCarClick(currentCar) }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCarList: List<Car>) {
        cars = newCarList
        notifyDataSetChanged()
    }
}