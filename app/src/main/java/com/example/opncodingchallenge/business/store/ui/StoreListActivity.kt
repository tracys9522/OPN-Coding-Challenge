package com.example.opncodingchallenge.business.store.ui

import com.example.opncodingchallenge.base.BaseActivity
import com.example.opncodingchallenge.business.store.contract.StoreListContract
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

        }
    }

    override fun initData() {
        mPresenter?.requestInfo()
    }

}