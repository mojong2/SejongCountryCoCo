package com.example.test30

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class RegisterActivity : AppCompatActivity() {
    private var et_id: EditText? = null
    private var et_pass: EditText? = null
    private var et_name: EditText? = null
    private var et_age: EditText? = null
    private var et_hak: EditText? = null
    private var et_major: EditText? = null
    private var et_passck: EditText? = null
    private var btn_register: Button? = null
    private var validateButton: Button? = null
    private var dialog: AlertDialog? = null
    private var validate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //아이디 값 찾아주기
        et_id = findViewById(R.id.et_id)
        et_pass = findViewById(R.id.et_pass)
        et_name = findViewById(R.id.et_name)
        et_age = findViewById(R.id.et_age)
        et_hak = findViewById(R.id.et_hak)
        et_major = findViewById(R.id.et_maj)
        et_passck = findViewById(R.id.et_passck)
        validateButton = findViewById(R.id.validateButton)
        validateButton.setOnClickListener(
            View.OnClickListener
            //id중복체크
            {
                val userID = et_id.getText().toString()
                if (validate) {
                    return@OnClickListener
                }
                if (userID == "") {
                    val builder = AlertDialog.Builder(this@RegisterActivity)
                    dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다")
                        .setPositiveButton("확인", null)
                        .create()
                    dialog!!.show()
                    return@OnClickListener
                }
                val responseListener: Response.Listener<String> = object : Listener<String?>() {
                    fun onResponse(response: String?) {
                        try {
                            val jsonResponse = JSONObject(response)
                            val success = jsonResponse.getBoolean("success")
                            if (success) {
                                val builder = AlertDialog.Builder(this@RegisterActivity)
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
                                    .setPositiveButton("확인", null)
                                    .create()
                                dialog!!.show()
                                et_id.setEnabled(false)
                                validate = true
                                validateButton.setText("확인")
                            } else {
                                val builder = AlertDialog.Builder(this@RegisterActivity)
                                dialog = builder.setMessage("사용할 수 없는 아이디입니다.")
                                    .setNegativeButton("확인", null)
                                    .create()
                                dialog!!.show()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
                val validateRequest = ValidateRequest(userID, responseListener)
                val queue: RequestQueue = Volley.newRequestQueue(this@RegisterActivity)
                queue.add(validateRequest)
            })
        btn_register = findViewById(R.id.btn_register)
        btn_register.setOnClickListener(View.OnClickListener { //editText에 입력되어있는 값을 get(가져온다)해온다
            val userID = et_id.getText().toString()
            val userPass = et_pass.getText().toString()
            val userName = et_name.getText().toString()
            val userAge = et_age.getText().toString().toInt()
            val userHak = et_hak.getText().toString().toInt()
            val userMajor = et_major.getText().toString()
            val PassCk = et_passck.getText().toString()
            val responseListener: Response.Listener<String> = object : Listener<String?>() {
                //volley
                fun onResponse(response: String?) {
                    try {
                        val jasonObject = JSONObject(response) //Register2 php에 response
                        val success = jasonObject.getBoolean("success") //Register2 php에 sucess
                        if (userPass == PassCk) {
                            if (success) { //회원등록 성공한 경우
                                Toast.makeText(applicationContext, "회원 등록 성공", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(
                                    this@RegisterActivity,
                                    LoginActivity::class.java
                                )
                                startActivity(intent)
                            }
                        } else { //회원등록 실패한 경우
                            Toast.makeText(applicationContext, "회원 등록 실패", Toast.LENGTH_SHORT)
                                .show()
                            return
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            //서버로 volley를 이용해서 요청을 함
            val registerRequest = RegisterRequest(
                userID,
                userPass,
                userName,
                userAge,
                userHak,
                userMajor,
                responseListener
            )
            val queue: RequestQueue = Volley.newRequestQueue(this@RegisterActivity)
            queue.add(registerRequest)
        })
    }
}