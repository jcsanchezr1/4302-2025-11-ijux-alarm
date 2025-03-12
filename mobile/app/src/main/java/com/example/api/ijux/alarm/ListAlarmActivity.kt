package com.example.api.ijux.alarm

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
        val textViewNoAlarms1 = findViewById<TextView>(R.id.textViewNoAlarms1)
        val textViewNoAlarms2 = findViewById<TextView>(R.id.textViewNoAlarms2)

        if (hasAlarms) {
            layoutListAlarms.visibility = View.VISIBLE
            layoutTextNoAlarms.visibility = View.GONE
        } else {
            layoutListAlarms.visibility = View.GONE
            layoutTextNoAlarms.visibility = View.VISIBLE
            textViewNoAlarms1.setTextColor(Color.parseColor("#4F2380"))
            textViewNoAlarms2.setTextColor(Color.parseColor("#4F2380"))
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