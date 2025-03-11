package com.example.api.ijux.alarm

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var buttonLogin: MaterialButton
    private lateinit var buttonRegister: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupInsets()
        initViews()
        setupHints()
        setupValidation()
        setupLoginButton()
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews() {
        emailLayout = findViewById(R.id.textInputLayoutEmail)
        passwordLayout = findViewById(R.id.textInputLayoutPassword)
        emailEditText = findViewById(R.id.editTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)
    }

    private fun setupHints() {
        emailLayout.setHintWithAsterisk(getString(R.string.correo_electronico))
        passwordLayout.setHintWithAsterisk(getString(R.string.contrasena))
    }

    private fun setupValidation() {
        emailEditText.setValidation(emailLayout)
        passwordEditText.setValidation(passwordLayout)
    }

    private fun setupLoginButton() {
        buttonLogin.setOnClickListener {
            val isEmailValid = emailEditText.validate(emailLayout)
            val isPasswordValid = passwordEditText.validate(passwordLayout)

            if (isEmailValid && isPasswordValid) {
                startActivity(Intent(this, ListAlarmActivity::class.java))
            }
        }
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
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
        } else if (this.id == R.id.editTextEmailAddress && !Patterns.EMAIL_ADDRESS.matcher(this.text!!)
                .matches()
        ) {
            textInputLayout.error = "El formato del correo no es válido"
            false
        } else {
            textInputLayout.error = null
            true
        }
    }
}
