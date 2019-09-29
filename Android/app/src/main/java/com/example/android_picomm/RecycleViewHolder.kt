package com.example.android_picomm

import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.personal_project.android_picomm.DataManager
import com.personal_project.android_picomm.SensorControllerListener

class RecycleViewHolder : RecyclerView.ViewHolder {
    private lateinit var mSensorName: String
    private var mSensorSwitch: Switch
    private var mSensorDataView: TextView
    private var mSensorNameView: TextView
    private val mSensorControllerListener: SensorControllerListener

    constructor(linearView: LinearLayout, sensorControllerListener: SensorControllerListener) : super(linearView) {
        mSensorControllerListener = sensorControllerListener
        mSensorNameView = linearView.findViewById(R.id.sensor_name)
        mSensorDataView = linearView.findViewById(R.id.sensor_value)
        mSensorSwitch = linearView.findViewById(R.id.sensor_start_switch)

    }

    fun setName(name: String) {
        mSensorName = name
        mSensorNameView.text = name
        mSensorSwitch.isChecked = DataManager.getSensorActive(name)
        mSensorSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.isChecked = isChecked
            DataManager.setSensorActive(name, isChecked)
            mSensorControllerListener.updateSensorActivity(name, isChecked)
        }
    }
}