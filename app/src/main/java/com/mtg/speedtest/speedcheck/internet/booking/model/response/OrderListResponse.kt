package com.mtg.speedtest.speedcheck.internet.booking.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderListResponse(
    @SerializedName("data")
    var data: List<OrderData>?,
) : BaseResponse()

@Parcelize
data class OrderData(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("userId")
    var userId: UserData?,
    @SerializedName("voucherId")
    var voucherId: VoucherData?,
    @SerializedName("cartId")
    var cartId: List<String>?,
    @SerializedName("totalAmount")
    var totalAmount: Int?,
    @SerializedName("cartData")
    var cartData: List<CartOrderData>?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
): Parcelable


@Parcelize
data class CartOrderData(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("userId")
    var userId: UserData?,
    @SerializedName("productId")
    var productId: TourItem?,
    @SerializedName("quantity")
    var quantity: Int?,
): Parcelable