package com.example.test30

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.create_id_main.back_button
import kotlinx.android.synthetic.main.create_id_main1.*
import kotlinx.android.synthetic.main.create_id_main3.*
import kotlinx.android.synthetic.main.create_id_main3.next_button

import java.lang.Math.abs

class CreateIdActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.create_id_main3)
        back_button.setOnClickListener({
            val intent = Intent(this, CreateIdActivity2::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
        next_button.setOnClickListener({
            if((year_input.getText().toString().equals("") || year_input.getText().toString() == null) || (month_input.getText().toString().equals("") || month_input.getText().toString() == null) || (day_input.getText().toString().equals("") || day_input.getText().toString() == null)){
                Toast.makeText(applicationContext, "생년월일을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, CreateIdActivity1::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
            }
        })
    }
    override fun onBackPressed(){
        val intent = Intent(this, CreateIdActivity2::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }
}
