package com.example.android_picomm

import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.personal_project.android_picomm.ConnectionStatus
import com.personal_project.android_picomm.SensorControllerListener

class ViewController(
        private val mActivity: Activity,
        private val mCommListener: SensorControllerListener) {
    private val TAG = "ViewController"

    private lateinit var mConnectButton: Button
    private lateinit var mDisconnectButton: Button
    private lateinit var mSaveButton: Button
    private lateinit var mIPTextView: TextView
    private lateinit var mIPEditText: EditText
    private lateinit var mPortTextView: TextView
    private lateinit var mPortEditText: EditText
    private lateinit var mSensorRecyclerView: RecyclerView
    private lateinit var mRecyclerViewManager: RecyclerView.LayoutManager
    private lateinit var mRecyclerViewAdapter: RecyclerViewAdapter

    fun setupUI(uiName: UI) {
        when (uiName) {
            UI.MAIN -> {
                mActivity.setContentView(R.layout.activity_main)
                mConnectButton = mActivity.findViewById(R.id.connect_button)
                mDisconnectButton = mActivity.findViewById(R.id.disconnect_button)
                mIPTextView = mActivity.findViewById(R.id.ip_textView)
                mSaveButton = mActivity.findViewById(R.id.save_button)
                mIPEditText = mActivity.findViewById(R.id.ip_editText)
//                mIPEditText.addTextChangedListener()
                mPortTextView = mActivity.findViewById(R.id.port_textView)
                mPortEditText = mActivity.findViewById(R.id.port_editText)

                mRecyclerViewManager = LinearLayoutManager(mActivity)
                mRecyclerViewAdapter = RecyclerViewAdapter(mCommListener)
                mSensorRecyclerView = mActivity.findViewById<RecyclerView>(R.id.sensor_list).apply {
                    setHasFixedSize(true)
                    layoutManager = mRecyclerViewManager
                    adapter = mRecyclerViewAdapter
                }

                mConnectButton.setOnClickListener {
                    mConnectButton.visibility = View.GONE
                    mDisconnectButton.visibility = View.VISIBLE
                    mCommListener.updateConnectionStatus(ConnectionStatus.CONNECTION_BEGIN)
                }

                mDisconnectButton.setOnClickListener {
                    mConnectButton.visibility = View.VISIBLE
                    mDisconnectButton.visibility = View.GONE
                    mCommListener.updateConnectionStatus(ConnectionStatus.CONNECTION_END)
                }
            }
        }
    }
}


enum class UI(val UIName: String) {
    MAIN("main"),
}