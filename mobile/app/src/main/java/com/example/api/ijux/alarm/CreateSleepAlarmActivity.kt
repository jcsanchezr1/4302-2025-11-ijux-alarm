package com.example.api.ijux.alarm

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class CreateSleepAlarmActivity : AppCompatActivity() {

    private lateinit var sleepNameTextInputLayout: TextInputLayout
    private lateinit var startHourSleepTextInputLayout: TextInputLayout
    private lateinit var endHourSleepTextInputLayout: TextInputLayout
    private lateinit var repeatSleepTextInputLayout: TextInputLayout
    private lateinit var soundSleepTextInputLayout: TextInputLayout
    private lateinit var sleepNameEditText: TextInputEditText
    private lateinit var startHourSleepEditText: TextInputEditText
    private lateinit var endHourSleepEditText: TextInputEditText
    private lateinit var repeatSleepAutoCompleteText: MaterialAutoCompleteTextView
    private lateinit var soundSleepAutoCompleteText: MaterialAutoCompleteTextView
    private lateinit var buttonBackCreateSleep: AppCompatImageButton
    private lateinit var buttonLogOutCreateSleep: AppCompatImageButton
    private lateinit var buttonSaveAlarmSleep: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_sleep_alarm)
        initViews()
        setupButtons()
        setupHints()
        setupValidation()
        setupTimePicker()
        setupRepeatList()
        setupSoundList()
    }

    private fun initViews() {
        sleepNameTextInputLayout = findViewById(R.id.textInputLayoutSleepName)
        startHourSleepTextInputLayout = findViewById(R.id.textInputLayoutStartHourSleep)
        endHourSleepTextInputLayout = findViewById(R.id.textInputLayoutEndHourSleep)
        repeatSleepTextInputLayout = findViewById(R.id.textInputLayoutRepeatSleep)
        soundSleepTextInputLayout = findViewById(R.id.textInputLayoutSoundSleep)
        sleepNameEditText = findViewById(R.id.editTextSleepName)
        startHourSleepEditText = findViewById(R.id.editTextStartHourSleep)
        endHourSleepEditText = findViewById(R.id.editTextEndHourSleep)
        repeatSleepAutoCompleteText = findViewById(R.id.autoCompleteTextViewRepeatSleep)
        soundSleepAutoCompleteText = findViewById(R.id.autoCompleteTextViewSoundSleep)
        buttonBackCreateSleep = findViewById(R.id.imageButtonBackCreateSleep)
        buttonLogOutCreateSleep = findViewById(R.id.imageButtonLogOutCreateSleep)
        buttonSaveAlarmSleep = findViewById(R.id.buttonSaveAlarmSleep)
    }

    private fun setupButtons() {

        buttonSaveAlarmSleep.setOnClickListener {
            val isValid = listOf(
                sleepNameEditText.validate(sleepNameTextInputLayout),
                startHourSleepEditText.validate(startHourSleepTextInputLayout),
                endHourSleepEditText.validate(endHourSleepTextInputLayout),
                repeatSleepAutoCompleteText.validateList(repeatSleepTextInputLayout),
                soundSleepAutoCompleteText.validateList(soundSleepTextInputLayout)
            ).all { it }
            if (isValid) {
                showSuccessDialog()
            }
        }
        buttonBackCreateSleep.setOnClickListener {
            val listAlarmActivity = Intent(this, ListAlarmActivity::class.java)
            val hasAlarms = intent.getBooleanExtra("HAS_ALARMS", false)
            listAlarmActivity.putExtra("HAS_ALARMS", hasAlarms)
            startActivity(listAlarmActivity)
        }
        buttonLogOutCreateSleep.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupHints() {
        sleepNameTextInputLayout.setHintWithAsterisk(getString(R.string.label_nombre_de_la_reunion))
        startHourSleepTextInputLayout.setHintWithAsterisk(getString(R.string.label_hora_de_inicio))
        endHourSleepTextInputLayout.setHintWithAsterisk(getString(R.string.label_hora_fin))
        repeatSleepTextInputLayout.setHintWithAsterisk(getString(R.string.label_repetir))
        soundSleepTextInputLayout.setHintWithAsterisk(getString(R.string.label_selecciona_el_tono))
    }

    private fun setupValidation() {
        sleepNameEditText.setValidation(sleepNameTextInputLayout)
        startHourSleepEditText.setValidation(startHourSleepTextInputLayout)
        endHourSleepEditText.setValidation(endHourSleepTextInputLayout)
        repeatSleepAutoCompleteText.setValidationList(repeatSleepTextInputLayout)
        soundSleepAutoCompleteText.setValidationList(soundSleepTextInputLayout)
    }

    private fun setupTimePicker() {
        startHourSleepEditText.setOnClickListener {
            sleepNameEditText.validate(sleepNameTextInputLayout)
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Selecciona la hora")
                .build()

            picker.show(supportFragmentManager, "TIME_PICKER")

            picker.addOnPositiveButtonClickListener {
                val hour = picker.hour
                val minute = picker.minute
                val amPm = if (hour >= 12) "PM" else "AM"
                val formattedHour = if (hour == 0 || hour == 12) 12 else hour % 12

                val selectedTime = String.format("%02d:%02d %s", formattedHour, minute, amPm)
                startHourSleepEditText.setText(selectedTime)
                startHourSleepTextInputLayout.error = null
                startHourSleepTextInputLayout.isErrorEnabled = false
                startHourSleepTextInputLayout.setEndIconTint(R.color.purple)
            }
        }
        endHourSleepEditText.setOnClickListener {
            sleepNameEditText.validate(sleepNameTextInputLayout)
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Selecciona la hora")
                .build()

            picker.show(supportFragmentManager, "TIME_PICKER")

            picker.addOnPositiveButtonClickListener {
                val hour = picker.hour
                val minute = picker.minute
                val amPm = if (hour >= 12) "PM" else "AM"
                val formattedHour = if (hour == 0 || hour == 12) 12 else hour % 12

                val selectedTime = String.format("%02d:%02d %s", formattedHour, minute, amPm)
                endHourSleepEditText.setText(selectedTime)
                endHourSleepTextInputLayout.error = null
                endHourSleepTextInputLayout.isErrorEnabled = false
                endHourSleepTextInputLayout.setEndIconTint(R.color.purple)
            }
        }
    }

    private fun setupRepeatList() {
        val unidades = listOf(
            "No repetir",
            "Todos los días",
            "Días laborables (Lunes a Viernes)",
            "Fines de semana (Sábado y Domingo)"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, unidades)
        repeatSleepAutoCompleteText.setAdapter(adapter)
        repeatSleepAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            repeatSleepTextInputLayout.error = null
            repeatSleepTextInputLayout.isErrorEnabled = false
        }
    }

    private fun setupSoundList() {
        val unidades =
            listOf("Clásica", "Electrónica", "Merengue", "Pop", "Rock", "Salsa")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, unidades)
        soundSleepAutoCompleteText.setAdapter(adapter)
        soundSleepAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            soundSleepTextInputLayout.error = null
            soundSleepTextInputLayout.isErrorEnabled = false
        }
    }

    private fun showSuccessDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.success_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val titleText = dialog.findViewById<TextView>(R.id.titleTextSuccessRegister)
        val descriptionText = dialog.findViewById<TextView>(R.id.descriptionTextSuccessRegister)
        titleText.text = "Alarma Creada"
        descriptionText.text = "La alarma ha sido creada con éxito"

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        val buttonAccept = dialog.findViewById<MaterialButton>(R.id.buttonAccept)
        buttonAccept.setOnClickListener {
            dialog.dismiss()
            val listAlarmActivity = Intent(this, ListAlarmActivity::class.java)
            listAlarmActivity.putExtra("HAS_ALARMS", true)
            startActivity(listAlarmActivity)
            finish()
        }

        dialog.show()
    }

    private fun TextInputLayout.setHintWithAsterisk(hintText: String) {
        val color = ContextCompat.getColor(context, R.color.red)
        val spannable = SpannableStringBuilder("* $hintText").apply {
            setSpan(ForegroundColorSpan(color), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        hint = spannable
    }

    private fun TextInputEditText.setValidation(textInputLayout: TextInputLayout) {
        this.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                this.validate(textInputLayout)
            }
        }
    }

    private fun MaterialAutoCompleteTextView.setValidationList(textInputLayout: TextInputLayout) {
        this.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                this.setValidationList(textInputLayout)
            }
        }
    }

    private fun TextInputEditText.validate(textInputLayout: TextInputLayout): Boolean {
        return if (this.text.isNullOrBlank()) {
            textInputLayout.error = "Este campo es obligatorio"
            textInputLayout.errorIconDrawable = null
            textInputLayout.setEndIconTint(R.color.red)
            false
        } else {
            textInputLayout.error = null
            textInputLayout.setEndIconTint(R.color.purple)
            true
        }
    }

    private fun MaterialAutoCompleteTextView.validateList(textInputLayout: TextInputLayout): Boolean {
        return if (this.text.isNullOrBlank()) {
            textInputLayout.error = "Este campo es obligatorio"
            textInputLayout.errorIconDrawable = null
            textInputLayout.setEndIconTint(R.color.red)
            false
        } else {
            textInputLayout.error = null
            textInputLayout.setEndIconTint(R.color.purple)
            true
        }
    }

    fun TextInputLayout.setEndIconTint(colorRes: Int) {
        val color = ContextCompat.getColor(context, colorRes)
        this.endIconDrawable?.setTint(color)
    }
}