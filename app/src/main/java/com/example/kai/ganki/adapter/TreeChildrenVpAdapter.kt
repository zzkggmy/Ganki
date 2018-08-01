package com.example.kai.ganki.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.kai.ganki.entity.TreeBean
import com.example.kai.ganki.fragment.TreeChildrenFragment
import com.example.kai.ganki.utils.Toast

class TreeChildrenVpAdapter(fm: FragmentManager, val list: ArrayList<Fragment>, private val treeChildrenList: ArrayList<TreeBean.Data.Children>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
//        val bundle = Bundle()
//        bundle.putInt("cid",treeChildrenList[position].id)
//        TreeChildrenFragment().arguments = bundle
//        return list[position]
        val bundle = Bundle().apply {
            putInt("cid",treeChildrenList[position].id)
        }
        return TreeChildrenFragment().apply { arguments = bundle }
    }

    override fun getCount() = list.size

    override fun getPageTitle(position: Int): CharSequence? {
        return treeChildrenList[position].name
    }
}