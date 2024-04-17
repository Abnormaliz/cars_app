package com.example.thecars.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thecars.App
import com.example.thecars.R
import com.example.thecars.adapters.FavoritesAdapter
import com.example.thecars.data.NameEntity
import com.example.thecars.databinding.FragmentFavoritesBinding
import com.example.thecars.interfaces.OnItemClickListener
import com.example.thecars.lists.allCarsList
import com.example.thecars.model.FavoritesViewModel
import com.example.thecars.model.FavoritesViewModelFactory


class FavoritesFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesAdapter
    private lateinit var favoritesViewModel: FavoritesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val database = (requireContext().applicationContext as App)
        favoritesViewModel = ViewModelProvider(this, FavoritesViewModelFactory(database))
            .get(FavoritesViewModel::class.java)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionmenu, menu)
        menu.findItem(R.id.fav).isVisible = false
        menu.findItem(R.id.remove).isVisible = false
        menu.findItem(R.id.add).isVisible = false
        if (adapter.getFlag()) {
            menu.findItem(R.id.remove).isVisible = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }

            R.id.remove -> {
                favoritesViewModel.removeItem(adapter.getNameEntity(adapter.selectedPosition))
                adapter.longClickFlag = false
                adapter.selectedPosition.clear()
                invalidateOptionsMenu(requireActivity())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        adapter = FavoritesAdapter(emptyList(), this)
        binding.rcViewFavorites.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Избранное"

        favoritesViewModel.dataList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

    }

    override fun onItemClick(position: NameEntity) {
        if (!adapter.longClickFlag) {
        val carList = allCarsList.find { it.title == position.brand }
        val modelList = carList?.modelList
        val date =
            modelList?.find { it.name == position.model }?.list?.find { it.name == position.name }
            date?.isFavorite = 1

        val bundle = Bundle().apply {
            putParcelable("selectedDate", date)
        }
        findNavController().navigate(R.id.action_favoritesFragment_to_imagesFragment, bundle)
    }
    }

}