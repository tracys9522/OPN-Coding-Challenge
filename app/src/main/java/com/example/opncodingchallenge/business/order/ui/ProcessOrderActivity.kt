package com.example.opncodingchallenge.business.order.ui

import com.example.opncodingchallenge.R
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
    private var orderProductAdapter = OrderProductAdapter()

    override fun getViewBinding() = ActivityProcessOrderBinding.inflate(layoutInflater)
    override fun getPresenter() = ProcessOrderPresenter()

    override fun initView() {
        binding.apply {
            productRv.adapter = orderProductAdapter

            confirmBtn.setOnClickListener {
                if (addressEt.text.isNotBlank()) {
                    mPresenter?.makeOrder(initOrder, addressEt.text.toString())
                } else {
                    showToastError(getString(R.string.opnlangMakeOrderTip))
                }
            }
        }
    }

    override fun initData() {
        val intentExtras: ArrayList<ProductModel>? =
            intent.extras?.getParcelableArrayList(StoreListActivity.ORDER_INTENT)

        if (!intentExtras.isNullOrEmpty()) {
            initOrder = intentExtras
            orderProductAdapter.mDatas = initOrder.toMutableList()
        } else {
            //END ACTIVITY
        }

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