package com.example.kai.ganki.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.kai.ganki.R
import com.example.kai.ganki.adapter.HotAdapter
import com.example.kai.ganki.entity.HotBean
import com.example.kai.ganki.http.Api
import com.example.kai.ganki.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_hot.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async


class HotActivity : AppCompatActivity() {
    private var hotList: ArrayList<HotBean.Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.zhihu_primary))
        rv_hot.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        iv_back_hot.setOnClickListener { finish() }
        getHot()
    }

    fun getHot() {
        async(UI) {
            val result = Api.retrofitService.getHot().await()
            if (result.errorCode == 0) {
                hotList.addAll(result.data)
                rv_hot.adapter = HotAdapter(hotList) { view, position ->
                    startActivity(Intent(this@HotActivity, ArticleDetailActivity::class.java).putExtra("title", hotList[position].name).putExtra("url", hotList[position].link))
                }

            }
        }
    }

}