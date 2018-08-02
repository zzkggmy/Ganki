package com.example.kai.ganki.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kai.ganki.R
import com.example.kai.ganki.http.Api
import com.example.kai.ganki.utils.StatusBarUtil
import com.example.kai.ganki.utils.Toast.showShortToast
import kotlinx.android.synthetic.main.activity_regist.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class RegistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.zhihu_primary))
        btn_register.setOnClickListener {
            register()
        }
        iv_back_register.setOnClickListener { finish() }
        tv_login_register.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }

    private fun register() {
        val map = HashMap<String, String>()
        if (tiet_username.text.toString().isEmpty()) {
            com.example.kai.ganki.utils.Toast.showShortToast("用户名不能为空")
            return
        }

        if (tiet_password.text.toString().isEmpty()) {
            com.example.kai.ganki.utils.Toast.showShortToast("密码不能为空")
            return
        }

        if (tiet_repassword.text.toString().isEmpty()) {
            com.example.kai.ganki.utils.Toast.showShortToast("请再次确认密码")
            return
        }
        map["username"] = tiet_username.text.toString()
        map["password"] = tiet_password.text.toString()
        map["repassword"] = tiet_repassword.text.toString()
        async(UI) {
                val result = Api.retrofitService.register(map).await()
                Log.d("rag",result.toString())
                if (result.errorCode == 0) {
                    showShortToast("注册成功")
                } else {
                    showShortToast(result.errorMsg)
                }
        }
    }
}