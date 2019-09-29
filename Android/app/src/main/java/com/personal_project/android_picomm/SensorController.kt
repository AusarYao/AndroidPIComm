package com.personal_project.android_picomm

import android.app.Activity
import kotlin.collections.HashMap

class SensorController {
    private val TAG = "SENSOR_CONTROLLER"
    private val mActivity: Activity
    private val mSensorAdapterMap = HashMap<String, SensorAdapter>()
    private val mSensorControllerListener = SensorControllerListenerImpl()

    constructor(activity: Activity) {
        mActivity = activity
//        mSensorListers.add(CameraAdapter(mActivity))
        val tmp = CommonSensorAdapter(mActivity)
        mSensorAdapterMap.put(tmp.getAdapterName(), tmp)
//        mSensorListers.add(GPSAdapter(mActivity))

    }

    fun startAll(){
        mSensorAdapterMap.values.forEach { it.startAll() }
//        Log.d(TAG, "connection start")
    }

    fun pauseAll(){
        mSensorAdapterMap.values.forEach { it.pauseAll() }
//        Log.d(TAG, "connection end")
    }

    fun getListener(): SensorControllerListener{
        return mSensorControllerListener
    }

    inner class SensorControllerListenerImpl: SensorControllerListener{
        override fun updateConnectionStatus(status: ConnectionStatus) {
            when(status){
                ConnectionStatus.CONNECTION_BEGIN -> {
                    DataManager.setConnectionStatus(ConnectionStatus.CONNECTION_BEGIN)
                    startAll()
//                    Log.d(TAG, "connection start")
                }
                ConnectionStatus.CONNECTION_END -> {
                    DataManager.setConnectionStatus(ConnectionStatus.CONNECTION_END)
                    pauseAll()
//                    Log.d(TAG, "connection end")
                }
            }
        }

        override fun updateSensorActivity(sensor: String, status: Boolean) {
            DataManager.setSensorActive(sensor, status)
            if(DataManager.getConnectionStatus()) {
                if(status)
                    mSensorAdapterMap[DataManager.getAdapterName(sensor)]!!.start(sensor)
                else
                    mSensorAdapterMap[DataManager.getAdapterName(sensor)]!!.pause(sensor)
            }
        }
    }
}

interface ConnectionStatusListener {
    fun updateConnectionStatus(status: ConnectionStatus)
}

interface SensorControllerListener: ConnectionStatusListener{
    override fun updateConnectionStatus(status: ConnectionStatus)
    fun updateSensorActivity(sensor: String, status: Boolean)
}