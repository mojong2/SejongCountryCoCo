package com.example.test30

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.create_id_main.*
import java.util.regex.Pattern


class CreateIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_id_main)
        val anim_test = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_anim) // 글자 애니메이션
        var id_tmp = "" // 아이디 임시 저장
        var pass_tmp = "" // 비밀번호 임시 저장
        var birth_tmp = "" // 생년월일 임시 저장
        var name_tmp = "" // 이름 임시 저장
        var pro_pn = "" // 보호자 번호 임시 저장
        var i_pn = "" // 본인 번호 임시 저장
        var regis = "1234" // 인증 번호
        var seq = 0 // 순서
        // 뒤로가기 버튼이 눌렸을때
        back_button.setOnClickListener({
            when(seq){ // 코틀린 switch 버젼
                0->{//순서가 처음인 경우에는 메인화면으로 돌아간다.
                    val intent = Intent(this, StartActivity::class.java) // 로그인 화면으로 변신
                    startActivity(intent)
                    ActivityCompat.finishAffinity(this)
                    overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
                }
                1->{//순서가 1인 경우에는 아이디입력으로 돌아간다.
                    main_menu.text = "아이디 입력"
                    id_tmp = all_input.getText().toString()
                    all_input.setText(null)
                    all_input.setHint("예: jeonju123")
                    ex_main.text = "5~20자의 영어 소문자, 숫자로 입력해주세요"
                    seq--
                    main_menu.startAnimation(anim_test)
                    all_input.startAnimation(anim_test)
                }
                2->{//순서가 2인 경우에는 비밀번호입력으로 돌아간다.
                    main_menu.text = "비밀번호 입력"
                    id_tmp = all_input.getText().toString()
                    all_input.setText(null)
                    all_input.setHint("비밀번호를 입력하세요")
                    ex_main.text = "8자 이상 입력해주세요"
                    seq--
                    main_menu.startAnimation(anim_test)
                    all_input.startAnimation(anim_test)
                }
                3->{//순서가 3인 경우에는 이름입력으로 돌아간다.
                    all_input.visibility = View.VISIBLE
                    birth_input.visibility = View.INVISIBLE
                    main_menu.text = "이름 입력"
                    pass_tmp = all_input.getText().toString()
                    all_input.setText(null)
                    all_input.setHint("이름을 입력하세요")
                    ex_main.setText(null)
                    seq--
                    main_menu.startAnimation(anim_test)
                    all_input.startAnimation(anim_test)
                }
                4->{//순서가 4인 경우에는 생년월일입력으로 돌아간다.
                    all_input.visibility = View.INVISIBLE
                    birth_input.visibility = View.VISIBLE
                    main_menu.text = "생년월일 입력"
                    name_tmp = all_input.getText().toString()
                    all_input.setText(null)
                    seq--
                    main_menu.startAnimation(anim_test)
                    birth_input.startAnimation(anim_test)
                }
                5->{//순서가 5인 경우에는 보호자 전화번호 입력으로 돌아간다.
                    main_menu.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40.0f)
                    main_menu.text = "보호자 휴대전화 입력"
                    birth_tmp =
                        year_input.toString() + month_input.toString() + day_input.toString()
                    all_input.setHint("예: 01012345678")
                    all_input.setText(null)
                    seq--
                    main_menu.startAnimation(anim_test)
                    all_input.startAnimation(anim_test)
                }
                6->{//순서가 5인 경우에는 본인 전화번호 입력으로 돌아간다.
                    main_menu.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50.0f)
                    main_menu.text = "본인 휴대전화 입력"
                    pro_pn = all_input.getText().toString()
                    all_input.setHint("예: 01012345678")
                    all_input.setText(null)
                    seq--
                    main_menu.startAnimation(anim_test)
                    all_input.startAnimation(anim_test)
                }
            }
        })
        //다음단계를 눌렀을때
        next_button.setOnClickListener({
            when (seq) {
                0 -> {//순서가 맨처음인 경우에는 아이디를 확인 후 넘어간다.
                    if (all_input.getText().toString().equals("") || all_input.getText()
                            .toString() == null
                    ) Toast.makeText(applicationContext, "아이디를 입력하세요", Toast.LENGTH_SHORT).show() // 비어있으면 못간다
                    else if (all_input.getText().length < 7) Toast.makeText(
                        applicationContext,
                        "비밀번호를 8자 이상 입력하세요",
                        Toast.LENGTH_SHORT
                    ).show() // 너무 짧아도 못간다.
                    else {//성공할 경우 비밀번호 입력으로 간다.
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
                1 -> {//순서가 1인 경우에는 비밀번호를 확인 후 넘어간다.
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
                2 -> {//순서가 2인 경우에는 이름을 확인 후 넘어간다.
                    if (all_input.getText().toString().equals("") || all_input.getText()
                            .toString() == null
                    ) Toast.makeText(applicationContext, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
                    else {
                        all_input.visibility = View.INVISIBLE
                        birth_input.visibility = View.VISIBLE
                        main_menu.text = "생년월일 입력"
                        name_tmp = all_input.getText().toString()
                        all_input.setText(null)
                        seq++
                        main_menu.startAnimation(anim_test)
                        birth_input.startAnimation(anim_test)
                    }
                }
                3 -> {//순서가 3인 경우에는 생년월일을 확인 후 넘어간다.
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
                4 -> {//순서가 4인 경우에는 보호자 휴대전화를 확인 후 넘어간다.
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
                5 -> {//순서가 5인 경우에는 본인 휴대전화를 확인 후 넘어간다.
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
                6 -> {//순서가6 인 경우에는 인증번호를 확인 후 넘어간다.
                    val intent = Intent(this, MainActivity::class.java) // 로그인 화면으로 변신
                    if (all_input.getText().toString().equals("") || all_input.getText().toString() == null)
                        Toast.makeText(applicationContext, "인증번호를 입력하세요", Toast.LENGTH_SHORT)
                    else if(all_input.getText().toString().equals(regis)){
                            var dialog = AlertDialog.Builder(this)
                            dialog.setTitle("회원가입 성공!")
                            dialog.setMessage("성공적으로 회원가입을 하셨습니다!")
                            var dialog_listener = object: DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    when(which){
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            intent.putExtra("name",id_tmp)
                                            intent.putExtra("pw",pass_tmp)
                                            startActivity(intent)
                                        }
                                    }
                                }
                            }
                            dialog.setPositiveButton("처음 화면으로 가기",dialog_listener)
                            dialog.show()
                    }
                    else Toast.makeText(applicationContext, "인증번호가 틀립니다", Toast.LENGTH_SHORT)
                }
            }
        })
    }
    override fun onBackPressed(){
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }
}
