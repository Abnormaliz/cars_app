package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thecars.databinding.FragmentCarsBinding
import com.example.thecars.databinding.FragmentModelsBinding


class CarsFragment : Fragment() {
    private lateinit var binding: FragmentCarsBinding
    lateinit var adapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

       binding = FragmentCarsBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CarAdapter(getCarList(), onCarClickListener = {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.place_holder, ModelsFragment.newInstance()).addToBackStack(null).commit()
        })
        binding.rcViewCars.adapter = adapter



    }
    private fun getCarList(): List<Car> {
        return this.resources.getStringArray(R.array.car_names).map {
            val logo = when (it) {
                "Audi" -> R.drawable.audi
                "BMW" -> R.drawable.bmw
                "Mercedes-benz" -> R.drawable.mb
                "Toyota" -> R.drawable.toyota
                "Volkswagen" -> R.drawable.volkswagen
                "Volvo" -> R.drawable.volvo
                else -> R.drawable.unknown
            }
            Car(logo, it)

        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = CarsFragment()
    }

}