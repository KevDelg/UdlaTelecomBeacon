package com.itsucces.udlatelecom.beacon

import android.content.Context
import android.util.Log
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.estimote.proximity_sdk.api.ProximityZoneBuilder
import com.itsucces.udlatelecom.BeaconActivity
import com.itsucces.udlatelecom.MyApplication

class ProximityContentManager(private val context: Context) {

    private var proximityObserverHandler: ProximityObserver.Handler? = null

    fun start() {

        val proximityObserver = ProximityObserverBuilder(context, (context.applicationContext as MyApplication).cloudCredentials)
            .withTelemetryReportingDisabled()
            .withEstimoteSecureMonitoringDisabled()
            .onError { throwable ->
                Log.e("app", "proximity observer error: $throwable")
            }
            .withBalancedPowerMode()
            .build()

        val zone = ProximityZoneBuilder()
            .forTag("telecomudlabeacon-o55")
            .inNearRange()
            .onContextChange { contexts ->
                val nearbyContent = ArrayList<ProximityContent>(contexts.size)
                for (context in contexts) {
                    val title: String = context.attachments["telecomudlabeacon-o55/title"] ?: "unknown"
                    val subtitle = Utils.getShortIdentifier(context.deviceId)
                    nearbyContent.add(ProximityContent(title, subtitle))

                    Log.d("cambio",title)
                }
                (context as BeaconActivity).setNearbyContent(nearbyContent)
            }
            .build()

        proximityObserverHandler = proximityObserver.startObserving(zone)
    }

    fun stop() {
        proximityObserverHandler?.stop()
    }
}