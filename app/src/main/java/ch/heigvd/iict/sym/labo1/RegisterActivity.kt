package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import android.content.Intent
import android.util.Patterns
import ch.heigvd.iict.sym.labo1.utils.Validator

/**
 * Activity that lest the user register a new account
 */
class RegisterActivity : LoggerActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.register_email)
        password = findViewById(R.id.register_password)
        cancelButton = findViewById(R.id.register_cancel)
        validateButton = findViewById(R.id.register_validate)

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()

            return@setOnClickListener
        }

        validateButton.setOnClickListener {

            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()

            //Here we create the validator that will make us able
            //to validate the email and password fields
            val validator = Validator(applicationContext, email, password)

            //check the user inputs
            if (!validator.validateNotEmpty()) {
                return@setOnClickListener
            }

            if (!validator.validateEmailFormat()){
                return@setOnClickListener
            }

            // Get values and send them to the caller activity
            val returnIntent = Intent().apply {
                putExtra("newUser.email", emailInput)
                putExtra("newUser.password", passwordInput)
            }

            setResult(RESULT_OK, returnIntent)

            //close this activity
            finish()

            return@setOnClickListener
        }
    }
}