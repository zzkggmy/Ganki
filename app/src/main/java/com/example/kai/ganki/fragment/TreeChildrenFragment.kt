package com.example.kai.ganki.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kai.ganki.R
import com.example.kai.ganki.activity.ArticleDetailActivity
import com.example.kai.ganki.adapter.TreeChildrenAdapter
import com.example.kai.ganki.entity.TreeArticleBean
import com.example.kai.ganki.http.Api
import com.example.kai.ganki.utils.Common
import com.tencent.smtt.sdk.QbSdk
import kotlinx.android.synthetic.main.frag_tree_children.*
import kotlinx.android.synthetic.main.frag_tree_children.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import tbsplus.tbs.tencent.com.tbsplus.TbsPlus

class TreeChildrenFragment : Fragment() {
    private var isRefresh = true
    private val articleList: ArrayList<TreeArticleBean.Data.Datas> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(activity, R.layout.frag_tree_children, null)
        QbSdk.initX5Environment(activity,null)
        view.rv_tree_children_frag.layoutManager = LinearLayoutManager(activity)
        this.getTreeChildrenArticle(view,arguments!!.getInt("cid",60))
        return view
    }

    private fun getTreeChildrenArticle(view: View, id: Int) {

        if (isRefresh) {
            view.sr_tree_children_frag.isRefreshing = true
        }
        async(UI) {
            val result = Api.retrofitService.getTreeArticle( 0,id).await()
            if (result.errorCode == 0) {
                sr_tree_children_frag.isRefreshing = false
                articleList.addAll(result.data.datas)
                view.rv_tree_children_frag.adapter = TreeChildrenAdapter(articleList){view, position ->
//                    startActivity(Intent(activity,ArticleDetailActivity::class.java).putExtra("url",articleList[position].link).putExtra("title",articleList[position].title))
                    TbsPlus.openUrl(Common.context, articleList[position].link)
                }
            }
        }
    }
}