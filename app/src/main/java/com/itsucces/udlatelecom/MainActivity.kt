package com.itsucces.udlatelecom

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.itsucces.udlatelecom.R.layout.activity_main

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.beacon_btn)

        button.setOnClickListener {
            val intent = Intent(this, BeaconActivity::class.java)
            startActivity(intent)
        }
    }
}