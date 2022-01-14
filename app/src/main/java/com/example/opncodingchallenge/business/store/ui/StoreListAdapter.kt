package com.example.opncodingchallenge.business.store.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.opncodingchallenge.base.CommonAdapter
import com.example.opncodingchallenge.base.CommonViewHolder
import com.example.opncodingchallenge.business.service.bean.ProductInfoBean
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.databinding.StoreItemBinding

class StoreListAdapter : CommonAdapter<ProductModel, StoreItemBinding>() {
    lateinit var onSelectAllListener: (Boolean) -> Unit

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
            Glide.with(binding.root.context)
                .load(data.imageDrawable)
                .into(itemImageIv)

            selectItemCb.setOnClickListener {
                data.isSelected = !data.isSelected
                if (data.isSelected && data.quantity == 0) {
                    ++data.quantity
                }
                notifyItemChanged(adapterPosition)
                if (isAllSelected()) {
                    onSelectAllListener.invoke(true)
                } else {
                    onSelectAllListener.invoke(false)
                }
            }

            addItemIv.setOnClickListener {
                ++data.quantity
                data.price += data.unitPrice
                notifyItemChanged(adapterPosition)
            }
            minusItemIv.setOnClickListener {
                if (data.quantity > 0) {
                    --data.quantity
                    data.price -= data.unitPrice
                }
                notifyItemChanged(adapterPosition)
            }

        }
        (holder as StoreListViewHolder).apply {
            bindData(data)
        }
    }

    private fun isAllSelected(): Boolean {
        return mDatas.filter { it.isSelected }.size == mDatas.size
    }

    fun setData(source: List<ProductInfoBean>) {
        source.forEachIndexed { index, productInfoBean ->
            mDatas.add(
                ProductModel(
                    imageDrawable = productInfoBean.imageUrl,
                    price = productInfoBean.price,
                    unitPrice = productInfoBean.price,
                    name = productInfoBean.name
                )
            )
            notifyItemChanged(index)
        }
    }

    fun selectAllOnClick(isSelected: Boolean) {
        mDatas.onEachIndexed { index, productModel ->
            if (isSelected && productModel.quantity == 0) {
                ++productModel.quantity
            }
            productModel.isSelected = isSelected
            notifyItemChanged(index)
        }
    }

    inner class StoreListViewHolder(binding: StoreItemBinding) :
        CommonViewHolder<StoreItemBinding>(binding) {

        fun bindData(product: ProductModel) {
            binding.apply {
                itemPriceTv.text = product.unitPrice.toString()
                selectItemCb.isChecked = product.isSelected
                itemAmountTv.text = product.quantity.toString()
                totalPriceTv.text = product.price.toString()
            }
        }
    }

}
