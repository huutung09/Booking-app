package com.mtg.speedtest.speedcheck.internet.booking.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TourResponse(
    @SerializedName("data") val tourData: TourData
) : BaseResponse()

@Parcelize
data class TourData(
    @SerializedName("data")
    var data: List<TourItem>?,
): Parcelable
