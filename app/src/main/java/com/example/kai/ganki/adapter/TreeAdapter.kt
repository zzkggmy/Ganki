package com.example.kai.ganki.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kai.ganki.R
import com.example.kai.ganki.entity.TreeBean
import com.example.kai.ganki.utils.Common
import kotlinx.android.synthetic.main.tree_item.view.*

class TreeAdapter(private val treeList: ArrayList<TreeBean.Data>, val onClick: (view: View, position: Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TREE = 0
    private val CHILDREN = 1
    private var click = true
    private var listSize = treeList.size
    private lateinit var onItemClick: OnItemClick

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return TreeHolder(LayoutInflater.from(Common.context).inflate(R.layout.tree_item, parent, false))
    }

    override fun getItemCount(): Int {
        return treeList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (treeList[position].isExpand) return CHILDREN else return TREE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.tv_tree.text = treeList[position].name
            holder.itemView.cv_tree.setOnClickListener {
                onClick(holder.itemView, position)
            }
    }

    fun exspandList(exspand: Boolean) {

    }

    fun diff() {
//        val diff = DiffUtil.calculateDiff(DiffCallBack(treeList), false)
    }

    class TreeHolder(view: View) : RecyclerView.ViewHolder(view)

    class ChildrenHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClick {
        fun itemClick(view: View, position: Int)
        fun itemLongClick(view: View, position: Int)
    }
}