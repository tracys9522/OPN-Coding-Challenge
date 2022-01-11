package com.example.opncodingchallenge.business.store.ui

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.opncodingchallenge.base.CommonAdapter
import com.example.opncodingchallenge.base.CommonViewHolder
import com.example.opncodingchallenge.bean.ProductInfoBean
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.databinding.StoreItemBinding

class StoreListAdapter : CommonAdapter<ProductModel, StoreItemBinding>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder<StoreItemBinding> {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: CommonViewHolder<StoreItemBinding>,
        adapterPosition: Int,
        binding: StoreItemBinding,
        data: ProductModel
    ) {
        TODO("Not yet implemented")
    }

    fun setData(source: List<ProductInfoBean>) {
        source.forEach {
            mDatas.add(ProductModel(imageDrawable = it.imageUrl, price = it.price.toString()))
        }
        notifyDataSetChanged()
    }
}
