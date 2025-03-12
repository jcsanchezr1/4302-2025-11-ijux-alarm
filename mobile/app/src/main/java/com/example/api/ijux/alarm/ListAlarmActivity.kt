package com.example.api.ijux.alarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton

class ListAlarmActivity : AppCompatActivity() {

    private lateinit var buttonLogOut: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_alarm)
        initViews()
        setupLoginButton()
    }

    private fun initViews() {
        buttonLogOut = findViewById(R.id.imageButtonLogOut)
    }

    private fun setupLoginButton() {
        buttonLogOut.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}