package com.example.test30

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.gongji_main.*
import kotlinx.android.synthetic.main.setting_main.*
import kotlinx.android.synthetic.main.setting_main.back_button
import java.text.SimpleDateFormat
import java.util.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        val currentTime : Long = System.currentTimeMillis()

        setContentView(R.layout.gongji_main)
        val dataFormat1 = SimpleDateFormat("yyyy년")
        val dataFormat2 = SimpleDateFormat("MM월dd일")
        val dataFormat3 = SimpleDateFormat("작성일자 : yyyy.MM.dd")
        val cal = Calendar.getInstance()
        cal.time = Date()
        year_view.text = (dataFormat1.format((cal.time)))
        date_text.text = "\n\n"+(dataFormat2.format((cal.time)))
        right_button.setOnClickListener({
            cal.add(Calendar.DATE, +1)
            year_view.text = (dataFormat1.format((cal.time)))
            date_text.text = "\n\n"+(dataFormat2.format((cal.time)))
        })
        left_button.setOnClickListener({
            cal.add(Calendar.DATE,-1)
            year_view.text = (dataFormat1.format((cal.time)))
            date_text.text = "\n\n"+(dataFormat2.format((cal.time)))
        })
        back_button.setOnClickListener({
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
        date_text.setOnClickListener({
            val intent = Intent(this, GongjiSubActivity::class.java)
            intent.putExtra("time",(dataFormat3.format(cal.time)))
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
        })
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }
}