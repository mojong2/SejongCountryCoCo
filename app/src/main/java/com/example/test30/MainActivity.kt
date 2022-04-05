package com.example.test30

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.login_main)
        loginbutton.setOnClickListener({
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        create_id_button.setOnClickListener({
            val intent = Intent(this, CreateIdActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
    }
}
