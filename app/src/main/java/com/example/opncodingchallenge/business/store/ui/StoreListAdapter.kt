package com.example.opncodingchallenge.business.store.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.opncodingchallenge.base.CommonAdapter
import com.example.opncodingchallenge.base.CommonViewHolder
import com.example.opncodingchallenge.bean.ProductInfoBean
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.databinding.StoreItemBinding

class StoreListAdapter : CommonAdapter<ProductModel, StoreItemBinding>() {

    lateinit var onItemClickListener: (ProductModel, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = StoreListViewHolder(
        StoreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: CommonViewHolder<StoreItemBinding>,
        adapterPosition: Int,
        binding: StoreItemBinding,
        data: ProductModel
    ) {
        binding.apply {
            selectItemCb.setOnClickListener {
                data.isSelected = !data.isSelected
            }
            addItemIv.setOnClickListener {
                ++data.quantity
            }
            minusItemIv.setOnClickListener {
                if (data.quantity > 0) --data.quantity
            }

            onItemClickListener.invoke(data, adapterPosition)

        }

        (holder as StoreListViewHolder).apply {
            bindData(data)
        }
    }

    fun setData(source: List<ProductInfoBean>) {
        source.forEach {
            mDatas.add(ProductModel(imageDrawable = it.imageUrl, price = it.price.toString()))
        }
    }

    inner class StoreListViewHolder(binding: StoreItemBinding) :
        CommonViewHolder<StoreItemBinding>(binding) {

        fun bindData(product: ProductModel) {
            binding.apply {
                itemPriceTv.text = product.price
                selectItemCb.isSelected = product.isSelected
                itemAmountTv.text = product.quantity.toString()
            }
        }
    }

}
