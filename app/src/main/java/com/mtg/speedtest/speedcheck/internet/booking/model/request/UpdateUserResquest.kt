package com.mtg.speedtest.speedcheck.internet.booking.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateUserResquest(
    @SerializedName("email") val email: String,
    @SerializedName("address") val address: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String
) : RequestModel()