package com.mtg.speedtest.speedcheck.internet.booking.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
) : RequestModel()