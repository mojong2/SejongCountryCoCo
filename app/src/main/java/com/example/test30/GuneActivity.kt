package com.example.test30

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.gongji_sub_main.*
import kotlinx.android.synthetic.main.gune_main.*
import kotlinx.android.synthetic.main.gune_main.size_small_button
import kotlinx.android.synthetic.main.gune_main.size_text
import kotlinx.android.synthetic.main.gune_main.size_up_button
import kotlinx.android.synthetic.main.setting_main.*
import kotlinx.android.synthetic.main.setting_main.back_button

class GuneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.gune_main)
        back_button.setOnClickListener({
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
        size_up_button.setOnClickListener({
            if(size_text.text == "작게"){
                size_text.text = "중간"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
            }
            else if(size_text.text == "중간"){
                size_text.text = "크게"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50.0f)
            }
        })
        size_small_button.setOnClickListener({
            if(size_text.text == "중간"){
                size_text.text = "작게"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30.0f)
            }
            else if(size_text.text == "크게"){
                size_text.text = "중간"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
            }
        })
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }

}
