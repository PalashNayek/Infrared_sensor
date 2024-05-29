package com.palash.infrared_sensor.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.palash.infrared_sensor.R
import com.palash.infrared_sensor.databinding.FragmentHomeBinding
import com.palash.infrared_sensor.view_model.IrSensorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private val irSensorViewModel by viewModels<IrSensorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        irSensorViewModel.irSensorData.observe(viewLifecycleOwner, Observer {
            binding.textViewIrSensorValue.text = "IR Sensor Value: $it"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}