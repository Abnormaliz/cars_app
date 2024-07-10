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
import com.example.thecars.app.presentation.interfaces.OnBrandClickListener
import com.example.thecars.app.presentation.models.BrandUi

class BrandsAdapter(
    private var brands: List<BrandUi>,
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
        val viewHolder = CarsViewHolder(itemView)

        itemView.setOnClickListener{
            val position = viewHolder.bindingAdapterPosition
            listener.onBrandClick(brands[position])
        }

        return viewHolder
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
        holder.title.text = currentBrand.brand
    }

    override fun getItemCount(): Int {
        return brands.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCarList: List<BrandUi>) {
        brands = newCarList
        notifyDataSetChanged()
    }
}


