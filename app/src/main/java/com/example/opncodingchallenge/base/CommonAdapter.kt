package com.example.opncodingchallenge.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class CommonAdapter<T : Any, VB : ViewBinding> : RecyclerView.Adapter<CommonViewHolder<VB>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<VB> {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CommonViewHolder<VB>, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

open class CommonViewHolder<VB : ViewBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root)