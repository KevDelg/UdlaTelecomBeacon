package com.itsucces.udlatelecom

import android.app.Application
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials

class MyApplication : Application() {

    val cloudCredentials =  EstimoteCloudCredentials("udlatelecom-l0q", "9116850162d0b2c8c11ed6ee0040d890")
}