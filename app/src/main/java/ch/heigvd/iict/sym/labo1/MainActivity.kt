package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import ch.heigvd.iict.sym.labo1.utils.Validator

class MainActivity : LoggerActivity() {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    // /!\ listOf() retourne une List<T> qui est immuable
    private var credentials = arrayListOf(Pair("user1@heig-vd.ch", "1234"),Pair("user2@heig-vd.ch", "abcd"))

    // le modifieur lateinit permet de définir des variables avec un type non-null
    // sans pour autant les initialiser immédiatement
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var registerButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main)

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)
        registerButton = findViewById(R.id.main_new_account)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null
            return@setOnClickListener
        }

        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()
            val validator = Validator(applicationContext, email, password)

            if (!validator.validateNotEmpty()) {
                return@setOnClickListener
            }

            if (validator.validateEmailFormat()){
                return@setOnClickListener
            }

            if (!credentials.contains(Pair(emailInput, passwordInput))) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(getString(R.string.main_dialog_error_title))
                    .setPositiveButton(getString(R.string.main_dialog_confirm)) { dialog, id ->
                        password.text?.clear()
                    }
                builder.create()
                builder.show()

                return@setOnClickListener
            }

            // Auth success
            // Launch activity
            val intent = Intent(this, AuthActivity::class.java).apply {
                putExtra("connectedEmail", emailInput)
            }
            startActivity(intent)
            return@setOnClickListener
        }

        registerButton.setOnClickListener {

            // Call the Register Activity and get its result
            getAndCreateNewUser.launch(Intent(this, RegisterActivity::class.java))
            return@setOnClickListener
        }
    }

    private val getAndCreateNewUser = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data

            val newUser = Pair(
                intent?.getStringExtra("newUser.email").toString(),
                intent?.getStringExtra("newUser.password").toString()
            )

            // This recreates a new listOf() with the new user, because listOf is not mutable
            credentials.add(newUser);
        }
    }
}
