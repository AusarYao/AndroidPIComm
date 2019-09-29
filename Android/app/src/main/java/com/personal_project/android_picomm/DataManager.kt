package com.personal_project.android_picomm

object DataManager {
    private val TAG = "DataManager"
    private var mIsConnected = false
    private val mSensorMap = HashMap<String, SensorData>()

    fun getVisibleSensors(): List<String> {
        return mSensorMap.keys.filter { mSensorMap[it]!!.isVisible }
    }

    fun getSensorActive(sensorName: String): Boolean {
        return mSensorMap[sensorName]!!.isActive && mSensorMap[sensorName]!!.isVisible
    }

    fun getConnectionStatus(): Boolean = mIsConnected

    fun getAdapterName(sensor: String): String = mSensorMap[sensor]!!.adapter

    fun addSensor(sensor: String, adapter: String) {
        mSensorMap.put(sensor, SensorData(adapter, false, true))
    }

    fun setSensorActive(sensorName: String, status: Boolean) {
        mSensorMap[sensorName]!!.isActive = status
    }

    fun setConnectionStatus(status: ConnectionStatus) {
        mIsConnected = if (status == ConnectionStatus.CONNECTION_BEGIN) true else false
    }
}

data class SensorData(
        val adapter: String,
        var isActive: Boolean,
        var isVisible: Boolean)