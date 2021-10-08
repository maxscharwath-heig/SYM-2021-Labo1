/**
 * Etudiants :
 * Nicolas Crausaz
 * Teo Ferrari
 * Maxime Scharwath
 */
package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity that implements some logging to the on* methods
 */
open class LoggerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate() has been called")
    }

    override fun onStart() {
        super.onStart()
        log("onStart() has been called")
    }

    override fun onResume() {
        super.onResume()
        log("onResume() has been called")
    }

    override fun onPause() {
        super.onPause()
        log("onPause() has been called")
    }

    override fun onStop() {
        super.onStop()
        log("onStop() has been called")
    }

    /**
     * Logs the message and give the name of the called class as the tag
     */
    fun log(content : String) {
        Log.d(this.javaClass.simpleName, content)
    }
}