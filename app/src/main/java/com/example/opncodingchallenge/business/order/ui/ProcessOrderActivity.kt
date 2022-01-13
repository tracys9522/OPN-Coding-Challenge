package com.example.opncodingchallenge.business.order.ui

import com.example.opncodingchallenge.base.BaseActivity
import com.example.opncodingchallenge.business.order.contract.ProcessOrderContract
import com.example.opncodingchallenge.business.order.presenter.ProcessOrderPresenter
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.business.store.model.StoreResultModel
import com.example.opncodingchallenge.business.store.ui.StoreListActivity
import com.example.opncodingchallenge.databinding.ActivityProcessOrderBinding

class ProcessOrderActivity : BaseActivity<ActivityProcessOrderBinding, ProcessOrderPresenter>(),
    ProcessOrderContract.View {
    private lateinit var initOrder: MutableList<ProductModel>
    override fun getViewBinding() = ActivityProcessOrderBinding.inflate(layoutInflater)
    override fun getPresenter() = ProcessOrderPresenter()

    override fun initView() {
        binding.apply {
            confirmBtn.setOnClickListener {
                if (addressEt.text.isNotBlank()) {
                    mPresenter?.makeOrder()
                } else {

                }
            }
        }
    }

    override fun initData() {
        val initOrder = intent.extras?.getSerializable(StoreListActivity.ORDER_INTENT)
    }

    override fun requestOrderOnSuccess(storeResultModel: StoreResultModel) {
        TODO("Not yet implemented")
    }

    override fun requestOrderOnFailure() {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable?) {
        TODO("Not yet implemented")
    }

}