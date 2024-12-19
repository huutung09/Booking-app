package com.mtg.speedtest.speedcheck.internet.booking.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreateOrderRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("cartId") val cartId: List<String>,
    @SerializedName("paymentMethodId") val paymentMethodId: String,
    @SerializedName("voucherId") val voucherId: String?,
    @SerializedName("address") val address: String,
    @SerializedName("language") val language: String
) : RequestModel()