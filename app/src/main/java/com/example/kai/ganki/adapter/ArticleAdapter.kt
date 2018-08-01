package com.example.kai.ganki.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kai.ganki.R
import com.example.kai.ganki.entity.ArticleBean
import com.example.kai.ganki.utils.Common
import kotlinx.android.synthetic.main.article_item.view.*

class ArticleAdapter(private val articleList: ArrayList<ArticleBean.Data.Datas>,val onClick: (view: View,position: Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ArticleHolder(LayoutInflater.from(Common.context).inflate(R.layout.article_item, p0, false))
    }

    override fun getItemCount(): Int {
        Log.d("sd",articleList.size.toString())
        return articleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_author.text = articleList[position].author
        holder.itemView.tv_date.text = articleList[position].niceDate
        holder.itemView.tv_from.text = articleList[position].chapterName
        holder.itemView.tv_title.text = articleList[position].title
        holder.itemView.cv_main.setOnClickListener {
            onClick(holder.itemView,holder.adapterPosition)
        }
    }

    class ArticleHolder(view: View) : RecyclerView.ViewHolder(view)
}