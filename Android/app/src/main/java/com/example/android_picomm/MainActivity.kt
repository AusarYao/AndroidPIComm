package com.example.android_picomm

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.personal_project.android_picomm.ConnectionStatusListener
import com.personal_project.android_picomm.DataManager
import com.personal_project.android_picomm.SensorController


class MainActivity : AppCompatActivity() {

    private val TAG = "MAIN_ACTIVITY"
    private lateinit var mViewController: ViewController
    private lateinit var mSensorController: SensorController

    data class SendAddress(var IP: String, var port: Int)

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()

        Log.d(TAG, "Main activity creating")
        mSensorController = SensorController(this)
        mViewController = ViewController(this, mSensorController.getListener())
        mViewController.setupUI(UI.MAIN)
        Log.d(TAG, "Main activity created")
    }
}