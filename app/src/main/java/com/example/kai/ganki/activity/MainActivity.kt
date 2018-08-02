package com.example.kai.ganki.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.kai.ganki.R
import com.example.kai.ganki.adapter.ArticleAdapter
import com.example.kai.ganki.entity.ArticleBean
import com.example.kai.ganki.http.Api
import com.example.kai.ganki.utils.StatusBarUtil
import com.example.kai.ganki.utils.Toast.showShortToast
import com.tencent.smtt.sdk.QbSdk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_header.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class MainActivity : AppCompatActivity() {
    private val articleList: ArrayList<ArticleBean.Data.Datas> = ArrayList()
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.lightBlue))
        setSupportActionBar(tb_main)
        QbSdk.initX5Environment(this,null)
        getTodayEssay()
        val headView = nv_main.inflateHeaderView(R.layout.navigation_header)
        headView.ll_user_center.setOnClickListener {
            startActivity(Intent(this, RegistActivity::class.java))
        }
        iv_open_drawer.setOnClickListener { dl_main.openDrawer(nv_main) }

        nv_main.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_tree -> startActivity(Intent(this, TreeActivity::class.java))
                R.id.menu_project -> showShortToast("2")
                R.id.menu_collection -> showShortToast("3")
                R.id.menu_setting -> showShortToast("4")
                R.id.menu_about -> showShortToast("5")

            }
            false
        }
    }

    private fun getTodayEssay() {
        async(UI) {
            try {
                val result = Api.retrofitService.getArticle(0).await()
                if (result.errorCode == 0) {
                    articleList.addAll(result.data.datas)
                    rv_article.layoutManager = LinearLayoutManager(this@MainActivity)
                    rv_article.adapter = ArticleAdapter(articleList) { view, position ->
                                                startActivity(Intent(this@MainActivity, ArticleDetailActivity::class.java).putExtra("url", articleList[position].link).putExtra("title", articleList[position].title))
                    }
                } else {
                    Toast.makeText(this@MainActivity, "获取资料失败", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
