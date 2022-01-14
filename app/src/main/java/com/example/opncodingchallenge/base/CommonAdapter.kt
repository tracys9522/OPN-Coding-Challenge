package com.example.opncodingchallenge.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class CommonAdapter<T : Any, VB : ViewBinding> :
    RecyclerView.Adapter<CommonViewHolder<VB>>() {
    open var mDatas: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<VB>

    override fun onBindViewHolder(holder: CommonViewHolder<VB>, position: Int) {
        onBindViewHolder(
            holder,
            holder.adapterPosition,
            holder.binding,
            mDatas[holder.adapterPosition]
        )
    }

    abstract fun onBindViewHolder(
        holder: CommonViewHolder<VB>,
        adapterPosition: Int,
        binding: VB,
        data: T
    )

    override fun getItemCount() = mDatas.size
}

open class CommonViewHolder<VB : ViewBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root)