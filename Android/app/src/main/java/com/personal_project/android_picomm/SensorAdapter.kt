package com.personal_project.android_picomm

import android.app.Activity

open class SensorAdapter {
    protected val mActivity: Activity

    constructor(activity : Activity){
        mActivity = activity
    }

    open fun startAll(){}
    open fun start(sensor: String){}
    open fun pauseAll(){}
    open fun pause(sensor: String){}
    open fun getAdapterName(): String = ""
}