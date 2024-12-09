package com.mtg.speedtest.speedcheck.internet.booking.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("is_admin")
    var isAdmin: String?,
    ): Parcelable
