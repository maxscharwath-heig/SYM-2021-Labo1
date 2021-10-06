package ch.heigvd.iict.sym.labo1.utils

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import ch.heigvd.iict.sym.labo1.R

/**
 * Class that makes it possible to validate login fields
 * Needs the context and login fields of the activity that uses it
 */
class Validator (var context: Context, var email: EditText, var password: EditText) {

    /**
     * Checks if both fields to validate are empty or not
     * Returns true if the are not empty
     */
  fun validateNotEmpty (): Boolean {
      val emailInput = email.text?.toString()
      val passwordInput = password.text?.toString()

      if (emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
          // on affiche un message dans les logs de l'application
          Log.d(context.javaClass.simpleName,"At least one of the two fields is empty")
          // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
          // la méthode getString permet de charger un String depuis les ressources de
          // l'application à partir de son id
          if(emailInput.isNullOrEmpty())
              email.error = context.getString(R.string.main_mandatory_field)
          if(passwordInput.isNullOrEmpty())
              password.error = context.getString(R.string.main_mandatory_field)
          return false
      }
      return true
  }

    /**
     * Check if the email has an email format
     * returns true if the format is respected
     */
    fun validateEmailFormat (): Boolean{
        val emailInput = email.text?.toString()
        //We check if the email pattern is respected thanks to the pattern matcher
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput!!).matches()){
            //if it doesn't we show a toes to the user and log the problem
            val toast = Toast.makeText(context, context.getString(R.string.main_email_format), Toast.LENGTH_SHORT)
            toast.show()
            Log.d(context.javaClass.simpleName,"Wrong email format")
           return false
        }
        return true
    }
}