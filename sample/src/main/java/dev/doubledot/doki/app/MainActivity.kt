package dev.doubledot.doki.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import dev.doubledot.doki.ui.DokiActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityBtn: AppCompatButton? = findViewById(R.id.launch_activity_btn)
        activityBtn?.setOnClickListener {
            DokiActivity.start(this)
        }

        val themedActivityBtn: AppCompatButton? = findViewById(R.id.launch_themed_activity_btn)
        themedActivityBtn?.setOnClickListener {
            startActivity(Intent(this@MainActivity, DokiThemedActivity::class.java))
        }


        val customizedActivityBtn: AppCompatButton? = findViewById(R.id.launch_customized_activity_btn)
        customizedActivityBtn?.setOnClickListener {
            startActivity(Intent(this@MainActivity, DokiCustomizedActivity::class.java))
        }

        val dialogBtn: AppCompatButton? = findViewById(R.id.launch_dialog_btn)
        dialogBtn?.setOnClickListener { DokiDialog.show(this@MainActivity) }


    }
}
