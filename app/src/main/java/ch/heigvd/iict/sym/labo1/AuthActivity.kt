package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class AuthActivity : LoggerActivity() {

    private lateinit var connectedEmailTextView : TextView;
    private lateinit var connectedImageView: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val passedEmail = intent.getStringExtra("connectedEmail")

        connectedEmailTextView = findViewById(R.id.connected_email)
        connectedEmailTextView.setText(passedEmail)

        connectedImageView = findViewById(R.id.connected_image)

        ImageDownloader(connectedImageView, "https://thispersondoesnotexist.com/image").show()
    }
}