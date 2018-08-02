package com.example.kai.ganki.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebSettings
import com.example.kai.ganki.R
import com.example.kai.ganki.utils.StatusBarUtil
import com.tencent.smtt.sdk.QbSdk
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.lightBlue))
        iv_back_article_details.setOnClickListener { finish() }
        QbSdk.initX5Environment(this,null)
        tv_title_article_details.text = intent.getStringExtra("title")
        wv_article_details.loadUrl(intent.getStringExtra("url"))
        val webSetting: WebSettings = wv_article_details.settings
        webSetting.apply {
            allowContentAccess = true
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            displayZoomControls = false
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        }
    }
}