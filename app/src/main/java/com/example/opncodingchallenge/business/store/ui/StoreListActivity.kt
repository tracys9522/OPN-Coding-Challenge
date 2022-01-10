package com.example.opncodingchallenge.business.store.ui

import com.example.opncodingchallenge.base.BaseActivity
import com.example.opncodingchallenge.business.store.contract.StoreListContract
import com.example.opncodingchallenge.business.store.presenter.StoreListPresenter
import com.example.opncodingchallenge.databinding.ActivityStoreListBinding

class StoreListActivity : BaseActivity<ActivityStoreListBinding, StoreListPresenter>(),
    StoreListContract.View {
    override fun requestInfoOnSuccess() {
        TODO("Not yet implemented")
    }

    override fun getInfoOnFailure() {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun getViewBinding(): ActivityStoreListBinding {
        TODO("Not yet implemented")
    }

    override fun getPresenter(): StoreListPresenter {
        TODO("Not yet implemented")
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}