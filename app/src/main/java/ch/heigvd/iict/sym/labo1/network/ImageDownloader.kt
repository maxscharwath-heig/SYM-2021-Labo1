package ch.heigvd.iict.sym.labo1.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

/**
 *  Classe utilisée pour télécharger une image sur internet de manière asynchrone
 *  le prochain laboratoire explorera plus en détail cet aspect d'Android
 */
class ImageDownloader(private var imageView: ImageView, private var url : String) {

    fun show() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                val bmp = downloadImage()
                if(bmp != null) {
                    withContext(Dispatchers.Main) {
                        imageView.setImageBitmap(bmp)
                    }
                }
            }
        }
    }

    private fun downloadImage() : Bitmap? {
        try {
            return BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
        } catch (e: IOException) {
            Log.w("ImageDownloader", "Error while downloading image $url - Check Internet permission", e)
        }
        return null
    }
}