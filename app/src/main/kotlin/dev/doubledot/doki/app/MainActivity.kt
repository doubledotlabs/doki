package dev.doubledot.doki.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import dev.doubledot.doki.Doki

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: AppCompatButton? = findViewById(R.id.launch_btn)
        btn?.setOnClickListener {
            Doki.launch(this@MainActivity)
        }
    }
}
