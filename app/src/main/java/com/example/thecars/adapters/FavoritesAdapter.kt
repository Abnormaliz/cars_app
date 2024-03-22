package com.example.thecars.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R
import com.example.thecars.classes.Date
import com.example.thecars.data.NameEntity
import com.example.thecars.interfaces.OnItemLongCLickListener

class FavoritesAdapter(
    private var favorites: List<NameEntity>,
    private var longListener: OnItemLongCLickListener) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private var selectedPosition: MutableSet<Int> = mutableSetOf()
    private var longClickFlag = false

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.iv_photo_favorites)
        val title: TextView = itemView.findViewById(R.id.tv_name_favorites)
        val selectedColor: FrameLayout = itemView.findViewById(R.id.selected_color)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false)
        return FavoritesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val currentFavorite = favorites[position]
        holder.title.text = currentFavorite.name
        holder.photo.setImageResource(currentFavorite.previewPhoto)

        val isCarSelected = selectedPosition.contains(position)
        holder.itemView.isSelected = isCarSelected
        if (isCarSelected) {
            holder.selectedColor.alpha = 0.5F
        } else {
            holder.selectedColor.alpha = 0F
        }
        holder.itemView.setOnLongClickListener {
            selectedPosition.clear()
            selectedPosition.add(position)
            longClickFlag = true
            notifyDataSetChanged()
            (holder.itemView.context as AppCompatActivity).invalidateOptionsMenu()
            true
        }

        holder.itemView.setOnClickListener {
            if (longClickFlag) {
                if (selectedPosition.contains(position)) {
                    selectedPosition.remove(position)
                    if (selectedPosition.isEmpty()) {
                        longClickFlag = false
                    }
                } else {
                    selectedPosition.add(position)
                }
                notifyDataSetChanged()
                (holder.itemView.context as AppCompatActivity).invalidateOptionsMenu()
            }

        }
    }
        @SuppressLint("NotifyDataSetChanged")
        fun updateData(favoritesList: List<NameEntity>) {
            favorites = favoritesList
            notifyDataSetChanged()
        }

    fun getFlag(): Boolean {
        return longClickFlag
    }
    }