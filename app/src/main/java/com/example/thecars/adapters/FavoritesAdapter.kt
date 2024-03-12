package com.example.thecars.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.R
import com.example.thecars.classes.Date

class FavoritesAdapter(private var favorites: List<Date>) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val photo: ImageView = itemView.findViewById(R.id.iv_photo_favorites)
        val title: TextView = itemView.findViewById(R.id.tv_name_favorites)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false)
        return FavoritesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val currentFavorite = favorites[position]
        holder.title.text = currentFavorite.name
        holder.photo.setImageResource(currentFavorite.previewPhoto)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(favoritesList: List<Date>) {
        favorites = favoritesList
        notifyDataSetChanged()
    }
}