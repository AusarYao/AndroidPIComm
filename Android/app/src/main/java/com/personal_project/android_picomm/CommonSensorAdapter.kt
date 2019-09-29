package com.personal_project.android_picomm

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class CommonSensorAdapter : SensorAdapter {
    private val TAG = "CommonSensorAdapter"
    private val mSensorReadInterval = 10000
    private val mSensorManager: SensorManager
    private val mSensorListenerMap = HashMap<String, SensorListener>()

    constructor(activity: Activity) : super(activity) {
        mSensorManager = mActivity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listOf(mSensorManager.getSensorList(Sensor.TYPE_ALL)).forEach {
            it.forEach {
                val sensorType = mSensorManager.getDefaultSensor(it.type)
                DataManager.addSensor(sensorType.name, TAG)
                mSensorListenerMap.put(sensorType.name, SensorListener(sensorType))
            }
        }
    }

    override fun getAdapterName(): String = TAG

    override fun startAll() {
        mSensorListenerMap.forEach { (key, listener) ->
            if (DataManager.getSensorActive(key))
                listener.onStart()
        }
    }

    override fun start(sensor: String) {
        mSensorListenerMap[sensor]!!.onStart()
    }

    override fun pauseAll() {
        mSensorListenerMap.values.forEach { it.onPause() }
    }

    override fun pause(sensor: String) {
        mSensorListenerMap[sensor]!!.onPause()
    }

    inner class SensorListener : SensorEventListener {
        private val mmSensor: Sensor

        constructor(sensor: Sensor) {
            mmSensor = sensor
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            Log.d(TAG, "Accuracy changed")
        }

        override fun onSensorChanged(event: SensorEvent) {
            val tmp = "${event.timestamp} ${event.sensor.name} ${event.values.forEach { it.toString() }}"
            Log.d(TAG, tmp)
        }

        fun onStart() {
            mSensorManager.registerListener(this, mmSensor, mSensorReadInterval)
        }

        fun onPause() {
            mSensorManager.unregisterListener(this)
        }
    }
}