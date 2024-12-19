package com.mtg.speedtest.speedcheck.internet.booking.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VoucherData(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("endDate")
    var endDate: String?,
    @SerializedName("startDate")
    var startDate: String?,
    @SerializedName("discountValue")
    var discountValue: Int?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("usageLimit")
    var usageLimit: String?,
    @SerializedName("image")
    var image: String?,
): Parcelable
