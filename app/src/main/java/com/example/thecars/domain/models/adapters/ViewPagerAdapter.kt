package com.example.thecars.domain.models.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R

class ViewPagerAdapter(private var imageList: List<Int>):
    RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.idIVImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_item, parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.image.setImageResource(imageList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newImageList: List<Int>) {
        imageList = newImageList
        notifyDataSetChanged()
    }
}
