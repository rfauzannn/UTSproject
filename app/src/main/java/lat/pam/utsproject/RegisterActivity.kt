package lat.pam.utsproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private var isPasswordVisible = false

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        val registerButton = findViewById<Button>(R.id.btnRegister)

        // Set listener for password visibility toggle
        etPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Periksa apakah klik terjadi di drawableEnd
                if (event.rawX >= (etPassword.right - etPassword.compoundDrawables[2].bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    updatePasswordVisibility()
                    return@setOnTouchListener true
                }
            }
            false
        }

        registerButton.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Check if all fields are filled
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please, Fill in all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (username.length < 4 || password.length < 4) {
                Toast.makeText(this, "Username and password must be at least 4 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save the username and password in SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show()
            finish() // Optionally go back to the MainActivity
        }
    }

    private fun updatePasswordVisibility() {
        if (isPasswordVisible) {
            etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0)
        } else {
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0)
        }
        etPassword.setSelection(etPassword.text.length) // Memastikan kursor tetap di akhir teks
    }
}
