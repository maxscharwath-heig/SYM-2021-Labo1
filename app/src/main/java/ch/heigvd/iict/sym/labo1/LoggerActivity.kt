package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class LoggerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this.javaClass.simpleName, "onCreate() has been called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(this.javaClass.simpleName, "onStart() has been called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(this.javaClass.simpleName, "onResume() has been called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(this.javaClass.simpleName, "onPause() has been called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(this.javaClass.simpleName, "onStop() has been called")
    }

    fun log(content : String) {
        Log.d(this.javaClass.simpleName, content)
    }
}