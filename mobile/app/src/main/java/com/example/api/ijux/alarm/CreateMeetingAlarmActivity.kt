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
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreateMeetingAlarmActivity : AppCompatActivity() {

    private lateinit var meetingNameTextInputLayout: TextInputLayout
    private lateinit var meetingNameEditText: TextInputEditText
    private lateinit var startDateMeetingTextInputLayout: TextInputLayout
    private lateinit var startHourMeetingTextInputLayout: TextInputLayout
    private lateinit var durationMeetingTextInputLayout: TextInputLayout
    private lateinit var repeatMeetingTextInputLayout: TextInputLayout
    private lateinit var soundMeetingTextInputLayout: TextInputLayout
    private lateinit var startDateMeetingEditText: TextInputEditText
    private lateinit var startHourMeetingEditText: TextInputEditText
    private lateinit var durationMeetingEditText: TextInputEditText
    private lateinit var repeatMeetingAutoCompleteText: MaterialAutoCompleteTextView
    private lateinit var soundMeetingAutoCompleteText: MaterialAutoCompleteTextView
    private lateinit var buttonBackCreateMeeting: AppCompatImageButton
    private lateinit var buttonLogOutCreateMeeting: AppCompatImageButton
    private lateinit var buttonSaveAlarmMeeting: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meeting_alarm)
        initViews()
        setupHints()
        setupValidation()
        setupButtons()
        setupDatePicker()
        setupTimePicker()
        setupRepeatList()
        setupSoundList()
    }

    private fun initViews() {
        meetingNameTextInputLayout = findViewById(R.id.textInputLayoutMeetingName)
        startDateMeetingTextInputLayout = findViewById(R.id.textInputLayoutStartDateMeeting)
        startHourMeetingTextInputLayout = findViewById(R.id.textInputLayoutStartHourMeeting)
        durationMeetingTextInputLayout = findViewById(R.id.textInputLayoutDurationMeeting)
        repeatMeetingTextInputLayout = findViewById(R.id.textInputLayoutRepeatMeeting)
        soundMeetingTextInputLayout = findViewById(R.id.textInputLayoutSoundMeeting)
        meetingNameEditText = findViewById(R.id.editTextMeetingName)
        startDateMeetingEditText = findViewById(R.id.editTextStartDateMeeting)
        startHourMeetingEditText = findViewById(R.id.editTextStartHourMeeting)
        durationMeetingEditText = findViewById(R.id.editTextDurationMeeting)
        repeatMeetingAutoCompleteText = findViewById(R.id.autoCompleteTextViewRepeatMeeting)
        soundMeetingAutoCompleteText = findViewById(R.id.autoCompleteTextViewSoundMeeting)
        buttonBackCreateMeeting = findViewById(R.id.imageButtonBackCreateMeeting)
        buttonLogOutCreateMeeting = findViewById(R.id.imageButtonLogOutCreateMeeting)
        buttonSaveAlarmMeeting = findViewById(R.id.buttonSaveAlarmMeeting)
    }

    private fun setupButtons() {
        buttonSaveAlarmMeeting.setOnClickListener {
            val isValid = listOf(
                meetingNameEditText.validate(meetingNameTextInputLayout),
                startDateMeetingEditText.validate(startDateMeetingTextInputLayout),
                startHourMeetingEditText.validate(startHourMeetingTextInputLayout),
                durationMeetingEditText.validate(durationMeetingTextInputLayout),
                repeatMeetingAutoCompleteText.validateList(repeatMeetingTextInputLayout),
                soundMeetingAutoCompleteText.validateList(soundMeetingTextInputLayout)
            ).all { it }

            if (isValid) {
                showSuccessDialog()
            }
        }
        buttonBackCreateMeeting.setOnClickListener {
            val intent = Intent(this, ListAlarmActivity::class.java)
            intent.putExtra("HAS_ALARMS", false)
            startActivity(intent)
        }
        buttonLogOutCreateMeeting.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupHints() {
        meetingNameTextInputLayout.setHintWithAsterisk(getString(R.string.label_nombre_de_la_reunion))
        startDateMeetingTextInputLayout.setHintWithAsterisk(getString(R.string.label_fecha_inicio))
        startHourMeetingTextInputLayout.setHintWithAsterisk(getString(R.string.label_hora_de_inicio))
        durationMeetingTextInputLayout.setHintWithAsterisk(getString(R.string.label_duracion_en_minutos))
        repeatMeetingTextInputLayout.setHintWithAsterisk(getString(R.string.label_repetir))
        soundMeetingTextInputLayout.setHintWithAsterisk(getString(R.string.label_selecciona_el_tono))
    }

    private fun setupValidation() {
        meetingNameEditText.setValidation(meetingNameTextInputLayout)
        startDateMeetingEditText.setValidation(startDateMeetingTextInputLayout)
        startHourMeetingEditText.setValidation(startHourMeetingTextInputLayout)
        durationMeetingEditText.setValidation(durationMeetingTextInputLayout)
        repeatMeetingAutoCompleteText.setValidationList(repeatMeetingTextInputLayout)
        soundMeetingAutoCompleteText.setValidationList(soundMeetingTextInputLayout)
    }

    private fun setupDatePicker() {
        startDateMeetingEditText.setOnClickListener {
            meetingNameEditText.validate(meetingNameTextInputLayout)
            durationMeetingEditText.validate(durationMeetingTextInputLayout)
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Selecciona una fecha")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.show(supportFragmentManager, "DATE_PICKER")

            datePicker.addOnPositiveButtonClickListener { selection ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selection
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                startDateMeetingEditText.setText(dateFormat.format(calendar.time))
                startDateMeetingTextInputLayout.error = null
                startDateMeetingTextInputLayout.isErrorEnabled = false
                startDateMeetingTextInputLayout.setEndIconTint(R.color.purple)
            }
        }
    }

    private fun setupTimePicker() {
        startHourMeetingEditText.setOnClickListener {
            meetingNameEditText.validate(meetingNameTextInputLayout)
            durationMeetingEditText.validate(durationMeetingTextInputLayout)
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
                startHourMeetingEditText.setText(selectedTime)
                startHourMeetingTextInputLayout.error = null
                startHourMeetingTextInputLayout.isErrorEnabled = false
                startHourMeetingTextInputLayout.setEndIconTint(R.color.purple)
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
        repeatMeetingAutoCompleteText.setAdapter(adapter)
        repeatMeetingAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            repeatMeetingTextInputLayout.error = null
            repeatMeetingTextInputLayout.isErrorEnabled = false
        }
    }

    private fun setupSoundList() {
        val unidades =
            listOf("Clásica", "Electrónica", "Merengue", "Pop", "Rock", "Salsa")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, unidades)
        soundMeetingAutoCompleteText.setAdapter(adapter)
        soundMeetingAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            soundMeetingTextInputLayout.error = null
            soundMeetingTextInputLayout.isErrorEnabled = false
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
            val intent = Intent(this, ListAlarmActivity::class.java)
            intent.putExtra("HAS_ALARMS", true)
            startActivity(intent)
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