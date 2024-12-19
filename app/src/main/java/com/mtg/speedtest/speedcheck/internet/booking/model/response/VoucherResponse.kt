package com.mtg.speedtest.speedcheck.internet.booking.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VoucherResponse(
    @SerializedName("data")
    var data: List<VoucherData>?,
) : BaseResponse()