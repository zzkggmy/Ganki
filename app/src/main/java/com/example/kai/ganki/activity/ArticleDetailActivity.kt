package com.example.kai.ganki.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kai.ganki.R
import com.example.kai.ganki.utils.StatusBarUtil
import com.tencent.smtt.sdk.QbSdk
import kotlinx.android.synthetic.main.activity_article_detail.*
import tbsplus.tbs.tencent.com.tbsplus.TbsPlus

class ArticleDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.lightBlue))
        iv_back_article_details.setOnClickListener { finish() }
        QbSdk.initX5Environment(this,null)
        tv_title_article_details.text = intent.getStringExtra("title")
        TbsPlus.openUrl(this,intent.getStringExtra("url"))
    }
}