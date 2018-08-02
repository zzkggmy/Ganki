package com.example.kai.ganki.adapter

import android.animation.ObjectAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kai.ganki.R
import com.example.kai.ganki.entity.HotBean
import com.example.kai.ganki.utils.Common
import kotlinx.android.synthetic.main.hot_item.view.*

class HotAdapter(val hotList: ArrayList<HotBean.Data>, val onClick: (view: View, position: Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HotHolder(LayoutInflater.from(Common.context).inflate(R.layout.hot_item, parent, false))
    }

    override fun getItemCount() = hotList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_hot_item.text = hotList[position].name
        holder.itemView.cv_hot_item.setOnClickListener {
            onClick(holder.itemView, holder.adapterPosition)
        }
        val animator = ObjectAnimator.ofFloat(holder.itemView.cv_hot_item, "translationY", 0f, 600f, 0f)
        animator.repeatCount = -1
        animator.duration = 6000
        animator.start()
    }

    class HotHolder(view: View) : RecyclerView.ViewHolder(view)

}