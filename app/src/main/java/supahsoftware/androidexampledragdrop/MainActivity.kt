package supahsoftware.androidexampledragdrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadImage()
    }

    private fun loadImage() {
        content_image.apply {
            setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.banner))
        }
    }
}