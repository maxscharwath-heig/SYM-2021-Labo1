package ch.heigvd.iict.sym.labo1.utils

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import ch.heigvd.iict.sym.labo1.R

class Validator (var context: Context, var email: EditText, var password: EditText) {

  fun validateNotEmpty (): Boolean {
      val emailInput = email.text?.toString()
      val passwordInput = password.text?.toString()

      if (emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
          // on affiche un message dans les logs de l'application
          Log.d(context.javaClass.simpleName,"Au moins un des deux champs est vide")
          // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
          // la méthode getString permet de charger un String depuis les ressources de
          // l'application à partir de son id
          if(emailInput.isNullOrEmpty())
              email.error = context.getString(R.string.main_mandatory_field)
          if(passwordInput.isNullOrEmpty())
              password.error = context.getString(R.string.main_mandatory_field)
          // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
          // doit être appliqué
          return false
      }
      return true
  }

    fun validateEmailFormat (): Boolean{
        val emailInput = email.text?.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput!!).matches()){
            val toast = Toast.makeText(context, context.getString(R.string.main_email_format), Toast.LENGTH_SHORT)
            toast.show()
           return false
        }
        return true
    }
}