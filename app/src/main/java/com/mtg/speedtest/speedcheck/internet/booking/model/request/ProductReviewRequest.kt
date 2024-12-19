package com.mtg.speedtest.speedcheck.internet.booking.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductReviewRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("productId") val productId: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("reviewText") val reviewText: String,
    ) : RequestModel()