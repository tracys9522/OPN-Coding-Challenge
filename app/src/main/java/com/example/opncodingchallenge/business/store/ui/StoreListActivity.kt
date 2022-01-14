package com.example.opncodingchallenge.business.store.ui

import android.content.Intent
import com.example.opncodingchallenge.R
import com.example.opncodingchallenge.base.BaseActivity
import com.example.opncodingchallenge.business.order.ui.ProcessOrderActivity
import com.example.opncodingchallenge.business.store.contract.StoreListContract
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.business.store.model.StoreResultModel
import com.example.opncodingchallenge.business.store.presenter.StoreListPresenter
import com.example.opncodingchallenge.databinding.ActivityStoreListBinding

class StoreListActivity : BaseActivity<ActivityStoreListBinding, StoreListPresenter>(),
    StoreListContract.View {
    private var productAdapter = StoreListAdapter()

    override fun requestInfoOnSuccess(storeResultModel: StoreResultModel) {
        binding.apply {
            storeResultModel.storeInfoBean?.apply {
                shopTitleTv.text = name
                shopRatingTv.text = rating.toString()
                openTimeTv.text = openingTime
                closeTimeTv.text = closingTime
            }
            if (storeResultModel.productList.isNotEmpty()) {
                productAdapter.setData(storeResultModel.productList)
            }
        }
    }

    override fun getInfoOnFailure() {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun getViewBinding() = ActivityStoreListBinding.inflate(layoutInflater)

    override fun getPresenter() = StoreListPresenter()

    override fun initView() {
        binding.apply {
            storeItemsRv.adapter = productAdapter

            productAdapter.onSelectAllListener = { isAllSelected ->
                selectAllCb.isChecked = isAllSelected
            }

            selectAllCb.setOnClickListener {
                productAdapter.selectAllOnClick(selectAllCb.isChecked)
            }

            orderBtn.setOnClickListener {
                val selectedData = arrayListOf<ProductModel>()
                val data = productAdapter.mDatas.filter {
                    it.isSelected
                }.onEach {
                    selectedData.add(it)
                }

                if (data.isEmpty()) {
                    showToastError(getString(R.string.opnlangSelectFirstTip))
                    return@setOnClickListener
                }

                val intent = Intent(this@StoreListActivity, ProcessOrderActivity::class.java)
                intent.putExtra(ORDER_INTENT, selectedData)
                startActivity(intent)
            }
        }
    }

    override fun initData() {
        mPresenter?.requestInfo()
    }

    companion object {
        const val ORDER_INTENT = "ORDER_INTENT"
    }

}