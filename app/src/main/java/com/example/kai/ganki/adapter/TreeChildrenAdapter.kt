package com.example.kai.ganki.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kai.ganki.R
import com.example.kai.ganki.entity.TreeArticleBean
import com.example.kai.ganki.utils.Common
import kotlinx.android.synthetic.main.tree_children_item.view.*

class TreeChildrenAdapter(val list: ArrayList<TreeArticleBean.Data.Datas>, val onClick: (view: View, position: Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return TreeChildrenHolder(LayoutInflater.from(Common.context).inflate(R.layout.tree_children_item, p0, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_tree_children_item.text = list[position].title
        holder.itemView.tv_author_tree_children_frag.text = list[position].author
        holder.itemView.tv_date_tree_children_frag.text = list[position].niceDate
        holder.itemView.tv_origin_tree_children_frag.text = list[position].origin
        holder.itemView.cv_tree_children_item.setOnClickListener {
            onClick(holder.itemView, position)
        }
    }

    class TreeChildrenHolder(view: View) : RecyclerView.ViewHolder(view)
}