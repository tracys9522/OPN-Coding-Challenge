package com.example.opncodingchallenge.business.order.ui

import android.content.Intent
import com.example.opncodingchallenge.R
import com.example.opncodingchallenge.base.BaseActivity
import com.example.opncodingchallenge.business.order.contract.ProcessOrderContract
import com.example.opncodingchallenge.business.order.presenter.ProcessOrderPresenter
import com.example.opncodingchallenge.business.store.model.ProductModel
import com.example.opncodingchallenge.business.store.ui.StoreListActivity
import com.example.opncodingchallenge.databinding.ActivityProcessOrderBinding
import com.example.opncodingchallenge.util.CommonDialog
import com.example.opncodingchallenge.util.StringUtil.processString

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
            var total = 0.00
            initOrder.onEach { total += it.price }
            binding.totalTv.text =
                getString(R.string.opnlangTotalPrice).processString(total.toString())
        } else {
            finish()
        }
    }

    override fun requestOrderOnSuccess() {
        CommonDialog().setTitle(getString(R.string.opnlangDialogTitle))
            .setMessage(getString(R.string.opnlangDialogMessage))
            .setCanceledOnTouchOutside(false)
            .onSuccessBtnText(getString(R.string.opnlangOk))
            .onSuccessListener {
                it?.dismiss()
                val intent = Intent(this, StoreListActivity::class.java)
                startActivity(intent)
            }
            .show(supportFragmentManager, null)
    }

    override fun onError(e: Throwable?) {
        showToastError(getString(R.string.opnlangContactTech))
    }

}