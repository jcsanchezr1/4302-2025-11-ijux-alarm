package com.example.api.ijux.alarm

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar;


class CreateTreatmentAlarmActivity : AppCompatActivity() {

    private lateinit var treatmentNameEditText: TextInputEditText
    private lateinit var dateEditText: TextInputEditText;
    private lateinit var quantityEditText: TextInputEditText
    private lateinit var unitAutoComplete: TextInputEditText
    private lateinit var intervalAutoComplete: TextInputEditText
    private lateinit var toneAutoComplete: TextInputEditText
    private lateinit var buttonSaveAlarm: MaterialButton
    private lateinit var buttonLogOutCreateTreatment: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_treatment_alarm)
        initViews()
        setupHints()
        setupValidation()
        setupButtons()
        setupDatePicker()
    }

    private fun initViews() {
        treatmentNameEditText = findViewById(R.id.editTextTreatmentName)
        dateEditText = findViewById(R.id.editTextStartDate);
        quantityEditText = findViewById(R.id.editTextQuantity)
        buttonSaveAlarm = findViewById(R.id.buttonSaveAlarm)
        buttonLogOutCreateTreatment = findViewById(R.id.imageButtonLogOutCreateTreatment)
        /*unitAutoComplete = findViewById(R.id.autoCompleteUnit)
        intervalAutoComplete = findViewById(R.id.autoCompleteInterval)
        toneAutoComplete = findViewById(R.id.autoCompleteTone)
        */
    }

    private fun setupButtons() {
        buttonSaveAlarm.setOnClickListener {
            val isValid = listOf(
                treatmentNameEditText.validate(findViewById(R.id.textInputLayoutTreatmentName)),
                quantityEditText.validate(findViewById(R.id.textInputLayoutQuantity)),
                dateEditText.validate(findViewById(R.id.textInputLayoutStartDate)),
                /*unitAutoComplete.validate(findViewById(R.id.textInputLayoutUnit)),
                intervalAutoComplete.validate(findViewById(R.id.textInputLayoutInterval)),
                toneAutoComplete.validate(findViewById(R.id.textInputLayoutTone))*/
            ).all { it }

            if (isValid) {
                showSuccessDialog()
                val intent = Intent(this, ListAlarmActivity::class.java)
                intent.putExtra("HAS_ALARMS", true)
                startActivity(intent)
            } else {
                Snackbar.make(
                    findViewById(R.id.main),
                    "Por favor, complete todos los campos correctamente",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        buttonLogOutCreateTreatment.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupHints() {
        findViewById<TextInputLayout>(R.id.textInputLayoutTreatmentName).setHintWithAsterisk("Nombre del tratamiento")
        findViewById<TextInputLayout>(R.id.textInputLayoutStartDate).setHintWithAsterisk("Fecha de tratamiento");
        findViewById<TextInputLayout>(R.id.textInputLayoutQuantity).setHintWithAsterisk("Cantidad")
        /*findViewById<TextInputLayout>(R.id.textInputLayoutUnit).setHintWithAsterisk("Unidad de medida")
        findViewById<TextInputLayout>(R.id.textInputLayoutInterval).setHintWithAsterisk("Seleccione el tiempo")
        findViewById<TextInputLayout>(R.id.textInputLayoutTone).setHintWithAsterisk("Tono de alarma")*/
    }

    private fun setupValidation() {
        treatmentNameEditText.setValidation(findViewById(R.id.textInputLayoutTreatmentName))
        dateEditText.setValidation(findViewById(R.id.textInputLayoutStartDate));
        quantityEditText.setValidation(findViewById(R.id.textInputLayoutQuantity))
        /*unitAutoComplete.setValidation(findViewById(R.id.textInputLayoutUnit))
        intervalAutoComplete.setValidation(findViewById(R.id.textInputLayoutInterval))
        toneAutoComplete.setValidation(findViewById(R.id.textInputLayoutTone))*/
    }

    private fun setupDatePicker() {
        dateEditText.setOnClickListener { view: View? ->
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { datePicker: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    val selectedDate =
                        selectedDay.toString() + "/" + (selectedMonth + 1) + "/" + selectedYear
                    dateEditText.setText(selectedDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }


    private fun showSuccessDialog() {
        Snackbar.make(findViewById(R.id.main), "Alarma guardada exitosamente", Snackbar.LENGTH_LONG)
            .show()
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

    private fun TextInputEditText.validate(textInputLayout: TextInputLayout): Boolean {
        return if (this.text.isNullOrBlank()) {
            textInputLayout.error = "Este campo es obligatorio"
            textInputLayout.errorIconDrawable = null
            false
        } else {
            textInputLayout.error = null
            true
        }
    }
}