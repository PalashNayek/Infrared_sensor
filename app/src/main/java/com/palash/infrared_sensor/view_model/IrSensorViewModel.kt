package com.palash.infrared_sensor.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.palash.infrared_sensor.repositories.IrSensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IrSensorViewModel @Inject constructor(
    private val irSensorRepository: IrSensorRepository
) : ViewModel() {

    val irSensorData: LiveData<Float>
        get() = irSensorRepository.irSensorData

    override fun onCleared() {
        irSensorRepository.unregisterSensorListener()
        super.onCleared()
    }
}