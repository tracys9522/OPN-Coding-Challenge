package com.example.opncodingchallenge.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.opncodingchallenge.databinding.CommonDialogBinding

class CommonDialog : DialogFragment() {
    protected lateinit var binding: CommonDialogBinding

    private var mTitle: String? = null
    private var mMessage: String? = null
    private var mCancelText: String? = null
    private var mConfirmText: String? = null
    private var mCancellable: Boolean = false
    private var onCancel: ((Dialog?) -> Unit)? = null
    private var onSuccess: ((Dialog?) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CommonDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            titleTv.text = mTitle
            messageTv.text = mMessage
            confirmBtn.text = mConfirmText
            cancelBtn.text = mCancelText
            confirmBtn.setOnClickListener {
                onSuccess?.invoke(dialog)
            }
            cancelBtn.setOnClickListener {
                onCancel?.invoke(dialog)
            }
            isCancelable = mCancellable
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog ?: return
        val window = dialog.window ?: return
        val params = window.attributes
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        params.dimAmount = 0.4f
        params.gravity = Gravity.CENTER
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        window.attributes = params
    }

    fun setTitle(title: String): CommonDialog {
        mTitle = title
        return this
    }

    fun setMessage(message: String): CommonDialog {
        mMessage = message
        return this
    }

    fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): CommonDialog {
        mCancellable = canceledOnTouchOutside
        return this
    }

    fun onSuccessListener(onSuccess: ((Dialog?) -> Unit)): CommonDialog {
        this.onSuccess = onSuccess
        return this
    }

    fun onSuccessBtnText(msg: String): CommonDialog {
        mConfirmText = msg
        return this
    }


}