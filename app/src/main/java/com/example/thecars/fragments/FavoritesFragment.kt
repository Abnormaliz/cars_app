package com.example.thecars.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thecars.R
import com.example.thecars.adapters.FavoritesAdapter
import com.example.thecars.data.CarEntity
import com.example.thecars.databinding.FragmentFavoritesBinding
import com.example.thecars.interfaces.OnItemClickListener
import com.example.thecars.model.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesAdapter
    private val favoritesViewModel: FavoritesViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
                val car = adapter.getCarEntity(adapter.selectedPosition)
                car.forEachIndexed { index, carEntity ->
                }
                favoritesViewModel.removeItem(car)
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

    override fun onItemClick(position: CarEntity) {
        if (!adapter.longClickFlag) {
            val car = favoritesViewModel.getCarFromCarEntity(position)

            val bundle = Bundle().apply {
                putParcelable("selectedCar", car)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_carDetailsFragment,
                bundle
            )
        }
    }
}
