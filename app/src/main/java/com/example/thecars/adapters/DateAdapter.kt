package com.example.thecars.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.classes.Date
import com.example.thecars.R
import com.example.thecars.interfaces.OnDateClickListener

class DateAdapter (
    private var dates: List<Date>,
    private val listener: OnDateClickListener): RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    class DateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_date)
        val image: ImageView = itemView.findViewById(R.id.item_date_image)
        val emptyCard: FrameLayout = itemView.findViewById(R.id.empty_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
        return DateViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val currentDate = dates[position]
        if (dates[position].backPhoto == 0) {
            holder.emptyCard.alpha = 0.5F
        } else {
            holder.emptyCard.alpha = 0F
        }
        holder.title.text = currentDate.name
        holder.image.setImageResource(currentDate.previewPhoto)
        holder.itemView.setOnClickListener { listener.onDateClick(currentDate) }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newDatesList: List<Date>) {
        dates = newDatesList
        notifyDataSetChanged()
    }
}