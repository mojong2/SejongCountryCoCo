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
import kotlinx.android.synthetic.main.create_id_main.*
import kotlinx.android.synthetic.main.create_id_main.back_button
import kotlinx.android.synthetic.main.create_id_main.next_button
import kotlinx.android.synthetic.main.create_id_main2.*
import kotlinx.android.synthetic.main.setting_main.*
import java.lang.Math.abs

class CreateIdActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.create_id_main2)
        back_button.setOnClickListener({
            val intent = Intent(this, CreateIdActivity1::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
        next_button.setOnClickListener({
            if(name_input.getText().toString().equals("") || name_input.getText().toString() == null){
                Toast.makeText(applicationContext, "이름를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, CreateIdActivity3::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
            }
        })
    }
    override fun onBackPressed(){
        val intent = Intent(this, CreateIdActivity1::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }
}
