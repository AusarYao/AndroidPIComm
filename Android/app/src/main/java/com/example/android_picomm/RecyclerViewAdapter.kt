package com.example.android_picomm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.personal_project.android_picomm.DataManager
import com.personal_project.android_picomm.SensorController
import com.personal_project.android_picomm.SensorControllerListener


class RecyclerViewAdapter(private val mSensorControllerListener: SensorControllerListener)
    : RecyclerView.Adapter<RecycleViewHolder>() {
    private val TAG = "RecyclerViewAdapter"
    private var listItems: List<String> = DataManager.getVisibleSensors()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val linearLayout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_base, parent, false) as LinearLayout
//        Log.d(TAG, listItems.size.toString())
        return RecycleViewHolder(linearLayout, mSensorControllerListener)
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.setName(listItems[position])
    }

    override fun getItemCount() = listItems.size
}