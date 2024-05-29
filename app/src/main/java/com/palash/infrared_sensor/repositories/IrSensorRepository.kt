package com.palash.infrared_sensor.repositories

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class IrSensorRepository @Inject constructor(
    private val sensorManager: SensorManager
) : SensorEventListener {

    private val _irSensorData = MutableLiveData<Float>()
    val irSensorData: LiveData<Float> = _irSensorData

    private var irSensor: Sensor? = null

    init {
        irSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        irSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            val irValue = event.values[0]
            _irSensorData.postValue(irValue)
        }
    }

    fun unregisterSensorListener() {
        sensorManager.unregisterListener(this)
    }
}