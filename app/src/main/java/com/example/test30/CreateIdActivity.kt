package com.example.test30

import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.test30.databinding.Dialog1Binding
import kotlinx.android.synthetic.main.create_id_main.*
import kotlinx.android.synthetic.main.create_id_main.back_button
import kotlinx.android.synthetic.main.create_id_main.next_button
import kotlinx.android.synthetic.main.create_id_main1.*
import kotlinx.android.synthetic.main.gune_main.*
import kotlinx.android.synthetic.main.setting_main.*
import java.lang.Math.abs
import java.util.*

class CreateIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_id_main)
        val anim_test = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_anim)
        var id_tmp = ""
        var pass_tmp = ""
        var birth_tmp = ""
        var name_tmp = ""
        var pro_pn = ""
        var i_pn = ""
        var regis = "1234"
        var seq = 0
        back_button.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        })
        next_button.setOnClickListener({
            when (seq) {
                0 -> {
                    if (all_input.getText().toString().equals("") || all_input.getText()
                            .toString() == null
                    ) Toast.makeText(applicationContext, "아이디를 입력하세요", Toast.LENGTH_SHORT).show()
                    else if (all_input.getText().length > 20 || all_input.getText().length < 5) Toast.makeText(
                        applicationContext,
                        "5~20자의 영어 소문자, 숫자만 사용가능합니다",
                        Toast.LENGTH_SHORT
                    ).show()
                    else {
                        main_menu.text = "비밀번호 입력"
                        id_tmp = all_input.getText().toString()
                        all_input.setText(null)
                        all_input.setHint("비밀번호를 입력하세요")
                        ex_main.text = "8자 이상 입력해주세요"
                        seq++
                        main_menu.startAnimation(anim_test)
                        all_input.startAnimation(anim_test)
                    }
                }
                1 -> {
                    if (all_input.getText().toString().equals("") || all_input.getText()
                            .toString() == null
                    ) Toast.makeText(applicationContext, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
                    else if (all_input.getText().length < 7) Toast.makeText(
                        applicationContext,
                        "비밀번호를 8자 이상 입력하세요",
                        Toast.LENGTH_SHORT
                    ).show()
                    else {
                        main_menu.text = "이름 입력"
                        pass_tmp = all_input.getText().toString()
                        all_input.setText(null)
                        all_input.setHint("이름을 입력하세요")
                        ex_main.setText(null)
                        seq++
                        main_menu.startAnimation(anim_test)
                        all_input.startAnimation(anim_test)
                    }
                }
                2 -> {
                    if (all_input.getText().toString().equals("") || all_input.getText()
                            .toString() == null
                    ) Toast.makeText(applicationContext, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
                    else {
                        all_input.visibility = View.INVISIBLE
                        birth_input.visibility = View.VISIBLE
                        main_menu.text = "생년월일 입력"
                        id_tmp = all_input.getText().toString()
                        all_input.setText(null)
                        seq++
                        main_menu.startAnimation(anim_test)
                        birth_input.startAnimation(anim_test)
                    }
                }
                3 -> {
                    if (year_input.getText().toString().equals("") || year_input.getText()
                            .toString() == null || month_input.getText().toString()
                            .equals("") || month_input.getText()
                            .toString() == null || day_input.getText().toString()
                            .equals("") || day_input.getText().toString() == null
                    )
                        Toast.makeText(applicationContext, "생년월일을 입력하세요", Toast.LENGTH_SHORT).show()
                    else {
                        main_menu.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40.0f)
                        main_menu.text = "보호자 휴대전화 입력"
                        birth_tmp =
                            year_input.toString() + month_input.toString() + day_input.toString()
                        all_input.setHint("예: 01012345678")
                        all_input.visibility = View.VISIBLE
                        birth_input.visibility = View.INVISIBLE
                        all_input.setText(null)
                        seq++
                        main_menu.startAnimation(anim_test)
                        all_input.startAnimation(anim_test)
                    }
                }
                4 -> {
                    if (all_input.getText().toString().equals("") || all_input.getText()
                            .toString() == null
                    ) Toast.makeText(applicationContext, "보호자 휴대전화를 입력하세요", Toast.LENGTH_SHORT)
                        .show()
                    else {
                        main_menu.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50.0f)
                        main_menu.text = "본인 휴대전화 입력"
                        pro_pn = all_input.getText().toString()
                        all_input.setText(null)
                        seq++
                        main_menu.startAnimation(anim_test)
                        all_input.startAnimation(anim_test)
                    }
                }
                5 -> {
                    if (all_input.getText().toString().equals("") || all_input.getText()
                            .toString() == null
                    ) Toast.makeText(applicationContext, "본인 휴대전화를 입력하세요", Toast.LENGTH_SHORT)
                        .show()
                    else {
                        main_menu.text = "인증번호 입력"
                        all_input.setText(null)
                        all_input.setHint(null)
                        seq++
                        main_menu.startAnimation(anim_test)
                        all_input.startAnimation(anim_test)
                    }
                }
                6 -> {
                    if (all_input.getText().toString().equals("") || all_input.getText().toString() == null)
                        Toast.makeText(applicationContext, "인증번호를 입력하세요", Toast.LENGTH_SHORT)
                    else if(all_input.getText().toString().equals(regis)){
                            var dialog = AlertDialog.Builder(this)
                            dialog.setTitle("회원가입 성공!")
                            dialog.setMessage("성공적으로 회원가입을 하셨습니다!")
                            var dialog_listener = object: DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    when(which){



                                        DialogInterface.BUTTON_POSITIVE -> onBackPressed()
                                    }
                                }
                            }
                            dialog.setPositiveButton("로그인 화면으로 가기",dialog_listener)
                            dialog.show()
                    }
                    else Toast.makeText(applicationContext, "인증번호가 틀립니다", Toast.LENGTH_SHORT)
                }
            }
        })
    }
    override fun onBackPressed(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }

}
