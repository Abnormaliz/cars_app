package com.example.thecars.app.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R
import com.example.thecars.app.presentation.interfaces.OnCarClickListener
import com.example.thecars.app.presentation.models.CarUi

class CarAdapter(
    private var
    cars: List<CarUi>,
    private val listener: OnCarClickListener
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_car)
        val image: ImageView = itemView.findViewById(R.id.item_car_image)
        val emptyCard: FrameLayout = itemView.findViewById(R.id.empty_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        val viewHolder = CarViewHolder(itemView)

        itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            listener.onCarClick(cars[position])
        }

        return viewHolder
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
        holder.title.text = currentCar.car + " " + currentCar.release
        holder.image.setImageResource(currentCar.previewPhoto)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCarList: List<CarUi>) {
        cars = newCarList
        notifyDataSetChanged()
    }
}