package com.itsucces.udlatelecom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import com.itsucces.udlatelecom.beacon.ProximityContent
import com.itsucces.udlatelecom.beacon.ProximityContentAdapter
import com.itsucces.udlatelecom.beacon.ProximityContentManager

class BeaconActivity : AppCompatActivity() {
    private var proximityContentManager: ProximityContentManager? = null
    private var proximityContentAdapter: ProximityContentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beacon)

        proximityContentAdapter = ProximityContentAdapter(this)
        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = proximityContentAdapter

        RequirementsWizardFactory
            .createEstimoteRequirementsWizard()
            .fulfillRequirements(this,
                {
                    Log.d("app", "requirements fulfilled")
                    startProximityContentManager()
                },
                { requirements ->
                    Log.e("app", "requirements missing: " + requirements)
                }
                , { throwable ->
                    Log.e("app", "requirements error: " + throwable)
                })
    }

    private fun startProximityContentManager() {
        proximityContentManager = ProximityContentManager(this)
        proximityContentManager?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        proximityContentManager?.stop()
    }

    fun setNearbyContent(nearbyContent: List<ProximityContent>) {
        proximityContentAdapter?.setNearbyContent(nearbyContent)
        proximityContentAdapter?.notifyDataSetChanged()
    }
}