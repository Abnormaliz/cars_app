package com.example.thecars.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R
import com.example.thecars.classes.Brand
import com.example.thecars.interfaces.OnBrandClickListener

class BrandsAdapter(
    private var brands: List<Brand>,
    private val listener: OnBrandClickListener
) :
    RecyclerView.Adapter<BrandsAdapter.CarsViewHolder>() {

    class CarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logos: ImageView = itemView.findViewById(R.id.iv_logos)
        val title: TextView = itemView.findViewById(R.id.tv_name_model)
        val emptyCard: FrameLayout = itemView.findViewById(R.id.empty_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_brand, parent, false)
        return CarsViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val currentBrand = brands[position]
        if (brands[position].modelList.isEmpty()) {
            holder.emptyCard.alpha = 0.5F
        } else {
            holder.emptyCard.alpha = 0F
        }
        holder.logos.setImageResource(currentBrand.logo)
        holder.title.text = currentBrand.name
        holder.itemView.setOnClickListener() {
            listener.onBrandClick(currentBrand)
        }
    }

    override fun getItemCount(): Int {
        return brands.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCarList: List<Brand>) {
        brands = newCarList
        notifyDataSetChanged()
    }
}


