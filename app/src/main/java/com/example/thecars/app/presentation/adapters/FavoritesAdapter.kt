package com.example.thecars.app.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R
import com.example.thecars.app.presentation.interfaces.OnItemClickListener
import com.example.thecars.app.presentation.models.CarUi

class FavoritesAdapter(
    private var favorites: List<CarUi>,
    private var listener: OnItemClickListener,
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    var selectedCars: MutableList<CarUi> = mutableListOf()
    var longClickFlag: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.iv_photo_favorites)
        val title: TextView = itemView.findViewById(R.id.tv_name_favorites)
        val selectedColor: FrameLayout = itemView.findViewById(R.id.selected_color)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false)
        val viewHolder = FavoritesViewHolder(itemView)


        itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(favorites[position])
                if (longClickFlag.value == true) {
                    if (selectedCars.contains(favorites[position])) {
                        selectedCars.remove(favorites[position])
                        if (selectedCars.isEmpty()) {
                            longClickFlag.value = false
                        }
                    } else {
                        selectedCars.add(favorites[position])
                    }
                    notifyDataSetChanged()
                }
            }
        }

        itemView.setOnLongClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                selectedCars.clear()
                selectedCars.add(favorites[position])
                longClickFlag.value = true
                notifyDataSetChanged()
                true
            } else
            false
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val currentFavorite = favorites[position]
        holder.title.text = "${currentFavorite.brand}\n${currentFavorite.name}"
        holder.photo.setImageResource(currentFavorite.previewPhoto)

        val isCarSelected = selectedCars.contains(currentFavorite)
        holder.itemView.isSelected = isCarSelected
        if (isCarSelected) {
            holder.selectedColor.alpha = 0.5F
        } else {
            holder.selectedColor.alpha = 0F
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(favoritesList: List<CarUi>) {
        favorites = favoritesList
        notifyDataSetChanged()
    }
}