package com.mtg.speedtest.speedcheck.internet.booking.model.response
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel

@Parcelize
open class BaseResponse(
    @IgnoredOnParcel
    @SerializedName("success")
    val success: String? = null,

    @IgnoredOnParcel
    @SerializedName("message")
    var message: String? = null,

    @IgnoredOnParcel
    @SerializedName("error")
    var error: String? = null
) : Parcelable
