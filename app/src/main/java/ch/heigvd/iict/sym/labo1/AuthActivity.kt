package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class AuthActivity : AppCompatActivity() {

    private lateinit var connectedEmailTextView : TextView;
    private lateinit var connectedImageView: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val passedEmail = intent.getStringExtra(PASSED_PROPS_EMAIL)

        connectedEmailTextView = findViewById(R.id.connected_email)
        connectedEmailTextView.setText(passedEmail)

        connectedImageView = findViewById(R.id.connected_image)

        val picture = ImageDownloader(connectedImageView, "https://thispersondoesnotexist.com/image").show()
    }
}