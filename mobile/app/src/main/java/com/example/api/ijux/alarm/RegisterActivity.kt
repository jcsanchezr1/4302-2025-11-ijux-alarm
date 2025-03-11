package com.example.api.ijux.alarm

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailLayoutRegister: TextInputLayout
    private lateinit var passwordLayoutRegister: TextInputLayout
    private lateinit var passwordConfirmLayoutRegister: TextInputLayout
    private lateinit var fullNamesLayoutRegister: TextInputLayout
    private lateinit var emailEditTextRegister: TextInputEditText
    private lateinit var passwordEditTextRegister: TextInputEditText
    private lateinit var passwordConfirmEditTextRegister: TextInputEditText
    private lateinit var fullNamesEditTextRegister: TextInputEditText
    private lateinit var buttonSaveRegister: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initViews()
        setupHints()
        setupValidation()
        setupSaveRegisterButton()
    }

    private fun initViews() {
        emailLayoutRegister = findViewById(R.id.textInputLayoutEmailRegister)
        passwordLayoutRegister = findViewById(R.id.textInputLayoutPasswordRegister)
        passwordConfirmLayoutRegister = findViewById(R.id.textInputLayoutPasswordConfirmRegister)
        fullNamesLayoutRegister = findViewById(R.id.textInputLayoutFullNamesRegister)
        emailEditTextRegister = findViewById(R.id.editTextEmailAddressRegister)
        passwordEditTextRegister = findViewById(R.id.editTextPasswordRegister)
        passwordConfirmEditTextRegister = findViewById(R.id.editTextPasswordConfirmRegister)
        fullNamesEditTextRegister = findViewById(R.id.editTextFullNamesRegister)
        buttonSaveRegister = findViewById(R.id.buttonSaveRegister)
    }

    private fun setupHints() {
        emailLayoutRegister.setHintWithAsterisk(getString(R.string.ingrese_su_correo))
        passwordLayoutRegister.setHintWithAsterisk(getString(R.string.ingrese_su_contrasena))
        passwordConfirmLayoutRegister.setHintWithAsterisk(getString(R.string.confirme_su_contrasena))
        fullNamesLayoutRegister.setHintWithAsterisk(getString(R.string.nombres_y_apellidos))
    }

    private fun setupValidation() {
        emailEditTextRegister.setValidation(emailLayoutRegister)
        passwordEditTextRegister.setValidation(passwordLayoutRegister)
        passwordConfirmEditTextRegister.setValidation(passwordConfirmLayoutRegister)
        fullNamesEditTextRegister.setValidation(fullNamesLayoutRegister)
    }

    private fun setupSaveRegisterButton() {
        buttonSaveRegister.setOnClickListener {
            val isEmailValid = emailEditTextRegister.validate(emailLayoutRegister)
            val isPasswordValid = passwordEditTextRegister.validate(passwordLayoutRegister)
            val isPasswordConfirmValid =
                passwordConfirmEditTextRegister.validate(passwordConfirmLayoutRegister)
            val isnNamesValid = fullNamesEditTextRegister.validate(fullNamesLayoutRegister)

            if (isEmailValid && isPasswordValid && isPasswordConfirmValid && isnNamesValid) {
                showSuccessDialog()
            }
        }
    }

    private fun showSuccessDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.success_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        val buttonAccept = dialog.findViewById<MaterialButton>(R.id.buttonAccept)
        buttonAccept.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, MainActivity::class.java)
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

    private fun TextInputEditText.validate(textInputLayout: TextInputLayout): Boolean {
        return if (this.text.isNullOrBlank()) {
            textInputLayout.error = "Este campo es obligatorio"
            textInputLayout.errorIconDrawable = null
            false
        } else if (this.id == R.id.editTextEmailAddressRegister && !Patterns.EMAIL_ADDRESS.matcher(
                this.text!!
            ).matches()
        ) {
            textInputLayout.error = "El formato del correo no es válido"
            textInputLayout.errorIconDrawable = null
            false
        } else if (this.id == R.id.editTextPasswordConfirmRegister) {
            val password = passwordEditTextRegister.text.toString()
            val confirmPassword = passwordConfirmEditTextRegister.text.toString()
            if (password != confirmPassword) {
                textInputLayout.error = "Las contraseñas no coinciden"
                textInputLayout.errorIconDrawable = null
                false
            } else {
                textInputLayout.error = null
                true
            }
        } else {
            textInputLayout.error = null
            true
        }
    }
}