package com.example.kai.ganki.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.example.kai.ganki.R
import com.example.kai.ganki.adapter.TreeAdapter
import com.example.kai.ganki.adapter.TreeChildrenAdapter
import com.example.kai.ganki.entity.TreeBean
import com.example.kai.ganki.http.Api
import com.example.kai.ganki.utils.StatusBarUtil
import com.example.kai.ganki.utils.Toast
import kotlinx.android.synthetic.main.activity_tree.*
import kotlinx.android.synthetic.main.tree_children_pop.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class TreeActivity : AppCompatActivity() {

    private var treeList: ArrayList<TreeBean.Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.lightBlue))
        iv_back_tree.setOnClickListener { finish() }
        rv_tree.layoutManager = GridLayoutManager(this@TreeActivity, 3)
        getTree()
    }

    private fun getTree() {
        async(UI) {
            val result = Api.retrofitService.getTree().await()
            if (result.errorCode == 0) {
                treeList.addAll(result.data)
                rv_tree.adapter = TreeAdapter(treeList) { view, position ->
                    startActivity(Intent(this@TreeActivity, TreeChildrenActivity::class.java).putExtra("position", position).putExtra("title", treeList[position].name))
                }
            }
        }
    }

    fun setBackgroundAlpha(alpha: Float) {
        val lp: WindowManager.LayoutParams = window.attributes
        lp.alpha = alpha
        window.attributes = lp
    }
}