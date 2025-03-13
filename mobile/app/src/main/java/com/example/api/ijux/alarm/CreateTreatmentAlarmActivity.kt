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


class CreateTreatmentAlarmActivity : AppCompatActivity() {

    private lateinit var treatmentNameTextInputLayout: TextInputLayout
    private lateinit var startDateTextInputLayout: TextInputLayout
    private lateinit var endDateTextInputLayout: TextInputLayout
    private lateinit var startHourTextInputLayout: TextInputLayout
    private lateinit var quantityTextInputLayout: TextInputLayout
    private lateinit var unitTextInputLayout: TextInputLayout
    private lateinit var rememberTextInputLayout: TextInputLayout
    private lateinit var soundTextInputLayout: TextInputLayout
    private lateinit var treatmentNameEditText: TextInputEditText
    private lateinit var startDateEditText: TextInputEditText
    private lateinit var endDateEditText: TextInputEditText
    private lateinit var startHourEditText: TextInputEditText
    private lateinit var quantityEditText: TextInputEditText
    private lateinit var unitAutoCompleteText: MaterialAutoCompleteTextView
    private lateinit var rememberAutoCompleteText: MaterialAutoCompleteTextView
    private lateinit var soundAutoCompleteText: MaterialAutoCompleteTextView
    private lateinit var buttonSaveAlarm: MaterialButton
    private lateinit var buttonBackCreateTreatment: AppCompatImageButton
    private lateinit var buttonLogOutCreateTreatment: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_treatment_alarm)
        initViews()
        setupHints()
        setupValidation()
        setupButtons()
        setupDatePicker()
        setupTimePicker()
        setupUnitList()
        setupRememberList()
        setupSoundList()
    }

    private fun initViews() {
        treatmentNameTextInputLayout = findViewById(R.id.textInputLayoutTreatmentName)
        startDateTextInputLayout = findViewById(R.id.textInputLayoutStartDate)
        endDateTextInputLayout = findViewById(R.id.textInputLayoutEndDate)
        startHourTextInputLayout = findViewById(R.id.textInputLayoutStartHour)
        quantityTextInputLayout = findViewById(R.id.textInputLayoutQuantity)
        unitTextInputLayout = findViewById(R.id.textInputLayoutUnit)
        rememberTextInputLayout = findViewById(R.id.textInputLayoutRemember)
        soundTextInputLayout = findViewById(R.id.textInputLayoutSound)
        treatmentNameEditText = findViewById(R.id.editTextTreatmentName)
        startDateEditText = findViewById(R.id.editTextStartDate)
        startHourEditText = findViewById(R.id.editTextStartHour)
        endDateEditText = findViewById(R.id.editTextEndDate)
        quantityEditText = findViewById(R.id.editTextQuantity)
        unitAutoCompleteText = findViewById(R.id.autoCompleteTextViewUnit)
        rememberAutoCompleteText = findViewById(R.id.autoCompleteTextViewRemember)
        soundAutoCompleteText = findViewById(R.id.autoCompleteTextViewSound)
        buttonSaveAlarm = findViewById(R.id.buttonSaveAlarm)
        buttonBackCreateTreatment = findViewById(R.id.imageButtonBackCreateTreatment)
        buttonLogOutCreateTreatment = findViewById(R.id.imageButtonLogOutCreateTreatment)
    }

    private fun setupButtons() {
        buttonSaveAlarm.setOnClickListener {
            val isValid = listOf(
                treatmentNameEditText.validate(treatmentNameTextInputLayout),
                startDateEditText.validate(startDateTextInputLayout),
                endDateEditText.validate(endDateTextInputLayout),
                startHourEditText.validate(startHourTextInputLayout),
                quantityEditText.validate(quantityTextInputLayout),
                unitAutoCompleteText.validateList(unitTextInputLayout),
                rememberAutoCompleteText.validateList(rememberTextInputLayout),
                soundAutoCompleteText.validateList(soundTextInputLayout)
            ).all { it }

            if (isValid) {
                showSuccessDialog()
            }
        }
        buttonBackCreateTreatment.setOnClickListener {
            val intent = Intent(this, ListAlarmActivity::class.java)
            intent.putExtra("HAS_ALARMS", false)
            startActivity(intent)
        }
        buttonLogOutCreateTreatment.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupHints() {
        treatmentNameTextInputLayout.setHintWithAsterisk(getString(R.string.label_nombre_tratamiento))
        startDateTextInputLayout.setHintWithAsterisk(getString(R.string.label_fecha_inicio))
        endDateTextInputLayout.setHintWithAsterisk(getString(R.string.label_fecha_final))
        startHourTextInputLayout.setHintWithAsterisk(getString(R.string.label_hora_de_inicio))
        quantityTextInputLayout.setHintWithAsterisk(getString(R.string.label_cantidad))
        unitTextInputLayout.setHintWithAsterisk(getString(R.string.label_unidad_de_medida))
        rememberTextInputLayout.setHintWithAsterisk(getString(R.string.label_recordar_cada))
        soundTextInputLayout.setHintWithAsterisk(getString(R.string.label_selecciona_el_tono))
    }

    private fun setupValidation() {
        treatmentNameEditText.setValidation(treatmentNameTextInputLayout)
        startDateEditText.setValidation(startDateTextInputLayout)
        endDateEditText.setValidation(endDateTextInputLayout)
        startHourEditText.setValidation(startHourTextInputLayout)
        quantityEditText.setValidation(quantityTextInputLayout)
        unitAutoCompleteText.setValidationList(unitTextInputLayout)
        rememberAutoCompleteText.setValidationList(rememberTextInputLayout)
        soundAutoCompleteText.setValidationList(soundTextInputLayout)
    }

    private fun setupDatePicker() {
        startDateEditText.setOnClickListener {
            treatmentNameEditText.validate(treatmentNameTextInputLayout)
            quantityEditText.validate(quantityTextInputLayout)
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
                startDateEditText.setText(dateFormat.format(calendar.time))
                startDateTextInputLayout.error = null
                startDateTextInputLayout.isErrorEnabled = false
                startDateTextInputLayout.setEndIconTint(R.color.purple)
            }
        }

        endDateEditText.setOnClickListener {
            treatmentNameEditText.validate(treatmentNameTextInputLayout)
            quantityEditText.validate(quantityTextInputLayout)
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
                endDateEditText.setText(dateFormat.format(calendar.time))
                endDateTextInputLayout.error = null
                endDateTextInputLayout.isErrorEnabled = false
                endDateTextInputLayout.setEndIconTint(R.color.purple)
            }
        }
    }

    private fun setupTimePicker() {
        startHourEditText.setOnClickListener {
            treatmentNameEditText.validate(treatmentNameTextInputLayout)
            quantityEditText.validate(quantityTextInputLayout)
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
                startHourEditText.setText(selectedTime)
                startHourTextInputLayout.error = null
                startHourTextInputLayout.isErrorEnabled = false
                startHourTextInputLayout.setEndIconTint(R.color.purple)
            }
        }
    }

    private fun setupUnitList() {
        val unidades = listOf("Cucharada", "Pastilla", "Mililitros", "Jeringa")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, unidades)
        unitAutoCompleteText.setAdapter(adapter)
        unitAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            unitTextInputLayout.error = null
            unitTextInputLayout.isErrorEnabled = false
        }
    }

    private fun setupRememberList() {
        val unidades = listOf("1 Hora", "2 Horas", "4 Horas", "8 Horas", "12 Horas", "24 Horas")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, unidades)
        rememberAutoCompleteText.setAdapter(adapter)
        rememberAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            rememberTextInputLayout.error = null
            rememberTextInputLayout.isErrorEnabled = false
        }
    }

    private fun setupSoundList() {
        val unidades =
            listOf("Clásica", "Electrónica", "Merengue", "Pop", "Rock", "Salsa")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, unidades)
        soundAutoCompleteText.setAdapter(adapter)
        soundAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            soundTextInputLayout.error = null
            soundTextInputLayout.isErrorEnabled = false
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