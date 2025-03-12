package com.example.api.ijux.alarm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton

class ListAlarmActivity : AppCompatActivity() {

    private lateinit var buttonLogOut: AppCompatImageButton
    private lateinit var buttonHeart: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_alarm)
        initViews()
        setupButtons()

        val hasAlarms = intent.getBooleanExtra("HAS_ALARMS", false)

        val layoutListAlarms = findViewById<LinearLayout>(R.id.LayoutListAlarms)
        val layoutTextNoAlarms = findViewById<LinearLayout>(R.id.LayoutTextNoAlarms)

        if (hasAlarms) {
            layoutListAlarms.visibility = View.VISIBLE
            layoutTextNoAlarms.visibility = View.GONE
        } else {
            layoutListAlarms.visibility = View.GONE
            layoutTextNoAlarms.visibility = View.VISIBLE
        }
    }

    private fun initViews() {
        buttonLogOut = findViewById(R.id.imageButtonLogOut)
        buttonHeart = findViewById(R.id.imageViewHeart)
    }

    private fun setupButtons() {
        buttonLogOut.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        buttonHeart.setOnClickListener {
            startActivity(Intent(this, CreateTreatmentAlarmActivity::class.java))
        }
    }
}