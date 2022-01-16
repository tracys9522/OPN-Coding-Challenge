package com.example.opncodingchallenge.business.order.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.opncodingchallenge.R
import com.example.opncodingchallenge.base.CommonAdapter
import com.example.opncodingchallenge.base.CommonViewHolder
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.databinding.ProductItemBinding
import com.example.opncodingchallenge.util.StringUtil.processString

class OrderProductAdapter : CommonAdapter<ProductModel, ProductItemBinding>() {

    override fun onBindViewHolder(
        holder: CommonViewHolder<ProductItemBinding>,
        adapterPosition: Int,
        binding: ProductItemBinding,
        data: ProductModel
    ) {
        Glide.with(binding.root.context)
            .load(data.imageDrawable)
            .into(binding.itemImageIv)

        (holder as OrderProductViewHolder).apply {
            bindData(data)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = OrderProductViewHolder(
        ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class OrderProductViewHolder(binding: ProductItemBinding) :
        CommonViewHolder<ProductItemBinding>(binding) {

        fun bindData(product: ProductModel) {
            binding.apply {
                itemNameTv.text = product.name
                itemPriceTv.text = root.context.getString(R.string.opnlangUnitPrice)
                    .processString(product.unitPrice.toString())
                totalPriceTv.text = root.context.getString(R.string.opnlangTotalPrice)
                    .processString(product.price.toString())
            }
        }
    }
}
