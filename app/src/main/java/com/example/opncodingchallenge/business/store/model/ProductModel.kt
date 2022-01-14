package com.example.opncodingchallenge.business.store.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    var name: String,
    var isSelected: Boolean = false,
    var imageDrawable: String,
    var price: Double,
    var unitPrice: Double,
    var quantity: Int = 0
) : Parcelable