package com.example.kai.ganki.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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
    private var page = 0
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.zhihu_primary))
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
                R.id.menu_hot -> startActivity(Intent(this,HotActivity::class.java))
                R.id.menu_collection -> showShortToast("3")
                R.id.menu_setting -> showShortToast("4")
                R.id.menu_about -> showShortToast("5")

            }
            false
        }
        srl_main.setOnRefreshListener {
            articleList.clear()
            getTodayEssay()
        }
    }

    private fun getTodayEssay() {
        async(UI) {
            try {
                val result = Api.retrofitService.getArticle(page).await()
                if (result.errorCode == 0) {
                    srl_main.isRefreshing = false
                    Log.d("aaa", result.toString())
                    articleList.addAll(result.data.datas)
                    rv_article.layoutManager = LinearLayoutManager(this@MainActivity)
                    rv_article.adapter = ArticleAdapter(articleList) { view, position ->
                                                startActivity(Intent(this@MainActivity, ArticleDetailActivity::class.java).putExtra("url", articleList[position].link).putExtra("title", articleList[position].title))
                    }
                    rv_article.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            Log.d("tag", "" + newState + rv_article.childCount)
                            if (newState == 2) {
                                MainActivity().page++
                            }
                            if (rv_article.childCount > 0) {
                                MainActivity().page++
                                articleList.addAll(result.data.datas)
                                rv_article.adapter!!.notifyDataSetChanged()
                            }
                        }
                    })
                } else {
                    Toast.makeText(this@MainActivity, "获取资料失败", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
