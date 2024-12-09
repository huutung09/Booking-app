package com.mtg.speedtest.speedcheck.internet.booking.model.request


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AddCartRequest(
    @SerializedName("quantity") val quantity: String,
    @SerializedName("productId") val productId: String,
    @SerializedName("userId") val userId: String
) : RequestModel()