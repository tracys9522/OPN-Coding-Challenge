package com.example.opncodingchallenge.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.opncodingchallenge.databinding.LoadingDialogBinding

class LoadingDialog : DialogFragment() {
    protected lateinit var binding: LoadingDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoadingDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

}