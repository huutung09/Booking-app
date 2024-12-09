package com.mtg.speedtest.speedcheck.internet.booking.model.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartResponse(
    @SerializedName("data")
    var data: List<CartData>?,
) : BaseResponse()


@Parcelize
data class CartData(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("userId")
    var userId: String?,
    @SerializedName("productId")
    var productId: TourItem?,
    @SerializedName("quantity")
    var quantity: Int?,
): Parcelable
