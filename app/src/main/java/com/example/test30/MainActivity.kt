package com.example.test30

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.login_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.login_main)
        if(intent.hasExtra("name") && intent.hasExtra("pw")){
            text1.setText(intent.getStringExtra("name"))
            text2.setText(intent.getStringExtra("pw"))
        }
        loginbutton.setOnClickListener({
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        back_button.setOnClickListener({
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
        create_id_button.setOnClickListener({
            val intent = Intent(this, CreateIdActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        forget_pwbutton.setOnClickListener({
            val intent = Intent(this, FindPwActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
    }
    override fun onBackPressed() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }
}
