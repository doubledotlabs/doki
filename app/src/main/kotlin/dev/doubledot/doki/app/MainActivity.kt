package dev.doubledot.doki.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityBtn: AppCompatButton? = findViewById(R.id.launch_activity_btn)
        activityBtn?.setOnClickListener {
            startActivity(Intent(this@MainActivity, DokiActivity::class.java))
        }

        val themedActivityBtn: AppCompatButton? = findViewById(R.id.launch_themed_activity_btn)
        themedActivityBtn?.setOnClickListener {
            startActivity(Intent(this@MainActivity, DokiThemedActivity::class.java))
        }
    }
}
