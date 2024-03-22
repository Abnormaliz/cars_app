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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.findNavController
import com.example.thecars.App
import com.example.thecars.R
import com.example.thecars.adapters.FavoritesAdapter
import com.example.thecars.data.MainDb
import com.example.thecars.data.NameEntity
import com.example.thecars.databinding.FragmentFavoritesBinding
import com.example.thecars.interfaces.OnItemLongCLickListener
import com.example.thecars.model.FavoritesViewModel
import com.example.thecars.model.FavoritesViewModelFactory
import com.example.thecars.objects.FavoritesRepository


class FavoritesFragment : Fragment(), OnItemLongCLickListener {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesAdapter
    private lateinit var favoritesViewModel: FavoritesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Избранное"

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
        Log.i("values", "${adapter.getFlag()}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
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
        favoritesViewModel.dataList.observe(viewLifecycleOwner){
            adapter.updateData(it)
        }

    }

    override fun onItemLongClick(position: NameEntity) {
    }
}