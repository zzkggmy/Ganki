package com.example.kai.ganki.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.kai.ganki.R
import com.example.kai.ganki.adapter.TreeChildrenVpAdapter
import com.example.kai.ganki.entity.TreeBean
import com.example.kai.ganki.fragment.TreeChildrenFragment
import com.example.kai.ganki.http.Api
import com.example.kai.ganki.utils.StatusBarUtil
import com.example.kai.ganki.utils.Toast
import kotlinx.android.synthetic.main.activity_tree_children.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class TreeChildrenActivity : AppCompatActivity() {
    private val fragList: ArrayList<Fragment> = ArrayList()
    private var treeChildrenList: ArrayList<TreeBean.Data.Children> = ArrayList()
    private val treeChildrenFragment = TreeChildrenFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_children)
        StatusBarUtil.setColorNoTranslucent(this, resources.getColor(R.color.lightBlue))
        tv_title_tree_children.text = intent.getStringExtra("title")
        iv_back_tree_children.setOnClickListener { finish() }
        tl_tree_children.layoutMode = TabLayout.MODE_SCROLLABLE
        ViewCompat.setElevation(tl_tree_children, 10f)
        getTreeChildren()
    }

    private fun getTreeChildren() {

        async(UI) {
            val result = Api.retrofitService.getTree().await()
            if (result.errorCode == 0) {
                treeChildrenList.addAll(result.data[intent.getIntExtra("position", 0)].children)
                for (i in 0 until treeChildrenList.size) {
                    fragList.add(treeChildrenFragment)
                }
                vp_tree_children.adapter = TreeChildrenVpAdapter(supportFragmentManager, fragList, treeChildrenList)
                tl_tree_children.setupWithViewPager(vp_tree_children, true)
            }
        }
    }
}