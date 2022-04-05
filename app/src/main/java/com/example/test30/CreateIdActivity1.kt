package com.example.test30

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.create_id_main1.*
import kotlinx.android.synthetic.main.create_id_main.back_button
import java.lang.Math.abs

class CreateIdActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.create_id_main1)
        back_button.setOnClickListener({
            val intent = Intent(this, CreateIdActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
        next_button.setOnClickListener({
            if(pw_input.getText().toString().equals("") || pw_input.getText().toString() == null){
                Toast.makeText(applicationContext, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else if(pw_input.getText().length < 7){
                Toast.makeText(applicationContext, "비밀번호는 8자 이상으로 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, CreateIdActivity2::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
            }
        })
    }
    override fun onBackPressed(){
        val intent = Intent(this, CreateIdActivity::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }
}
