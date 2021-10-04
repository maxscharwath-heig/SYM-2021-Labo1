package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity

import android.content.Intent




class RegisterActivity : AppCompatActivity() {
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

        validateButton.setOnClickListener {

            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()

            if (emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if(emailInput.isNullOrEmpty())
                    email.error = getString(R.string.main_mandatory_field)
                if(passwordInput.isNullOrEmpty())
                    password.error = getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return@setOnClickListener
            }

            if (!emailInput!!.contains('@')){
                val toast = Toast.makeText(applicationContext, getString(R.string.main_email_format), Toast.LENGTH_SHORT)
                toast.show()
                return@setOnClickListener
            }

            // Get values and send them to the main activity
            val returnIntent = Intent().apply {
                putExtra("newUser.email", emailInput)
                putExtra("newUser.password", passwordInput)
            }

            setResult(RESULT_OK, returnIntent)
            finish()

            return@setOnClickListener
        }
    }

    companion object {
        private const val TAG: String = "RegisterActivity"
    }
}