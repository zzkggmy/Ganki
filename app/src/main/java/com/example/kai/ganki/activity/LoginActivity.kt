package com.example.kai.ganki.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kai.ganki.R
import com.example.kai.ganki.http.Api
import com.example.kai.ganki.http.RetrofitHelper
import com.example.kai.ganki.utils.StatusBarUtil
import com.example.kai.ganki.utils.Toast
import com.example.kai.ganki.utils.Toast.showShortToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        StatusBarUtil.setColorNoTranslucent(this,resources.getColor(R.color.lightBlue))
        btn_login.setOnClickListener { login(tiet_username_login.text.toString(),tiet_password_login.text.toString()) }
        iv_back_login.setOnClickListener { finish() }
    }

    private fun login(username: String,password: String) {
        val map = HashMap<String, String>()
        if (username.isEmpty()) {
            showShortToast("用户名不能为空")
            return
        }

        if (password.isEmpty()) {
            showShortToast("密码不能为空")
            return
        }

        map["username"] = tiet_username_login.text!!.trim().toString()
        map["password"] = tiet_password_login.text!!.trim().toString()
        async(UI) {
                val result = Api.retrofitService.login(map).await()
                Log.d("res",result.toString())
                if (result.errorCode == 0) {
                    Toast.showShortToast("登录成功")
                    finish()
                } else {
                    Toast.showShortToast(result.errorMsg)
                }
        }
    }
}