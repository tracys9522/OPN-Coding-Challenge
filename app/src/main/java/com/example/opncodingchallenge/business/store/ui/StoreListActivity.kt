package com.example.opncodingchallenge.business.store.ui

import android.content.Intent
import android.view.View
import com.example.opncodingchallenge.R
import com.example.opncodingchallenge.base.BaseActivity
import com.example.opncodingchallenge.business.order.ui.ProcessOrderActivity
import com.example.opncodingchallenge.business.store.contract.StoreListContract
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.business.store.model.StoreResultModel
import com.example.opncodingchallenge.business.store.presenter.StoreListPresenter
import com.example.opncodingchallenge.databinding.ActivityStoreListBinding
import com.example.opncodingchallenge.util.StringUtil.processString

class StoreListActivity : BaseActivity<ActivityStoreListBinding, StoreListPresenter>(),
    StoreListContract.View {
    private var productAdapter = StoreListAdapter()

    override fun requestInfoOnSuccess(storeResultModel: StoreResultModel) {
        binding.apply {
            if (noItemTv.visibility == View.VISIBLE) {
                noItemTv.visibility = View.INVISIBLE
            }
            if (refreshTv.visibility == View.VISIBLE) {
                refreshTv.visibility = View.INVISIBLE
            }
            if (bottomContentLl.visibility == View.INVISIBLE) {
                bottomContentLl.visibility = View.VISIBLE
            }

            storeResultModel.storeInfoBean?.apply {
                shopTitleTv.text = name
                shopRatingTv.text =
                    getString(R.string.opnlangRating).processString(rating.toString())
                openTimeTv.text =
                    getString(R.string.opnlangOpeningTime).processString(openingTime, closingTime)
            }
            if (storeResultModel.productList.isNotEmpty()) {
                productAdapter.setData(storeResultModel.productList)
            }
        }
    }

    override fun onError(e: Throwable?) {
        showToastError(getString(R.string.opnlangContactTech))
        binding.apply {
            noItemTv.visibility = View.VISIBLE
            refreshTv.visibility = View.VISIBLE
            bottomContentLl.visibility = View.INVISIBLE
        }
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

            refreshTv.setOnClickListener {
                mPresenter?.requestInfo()
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