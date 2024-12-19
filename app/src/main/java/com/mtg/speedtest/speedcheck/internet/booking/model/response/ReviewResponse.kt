package com.mtg.speedtest.speedcheck.internet.booking.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewResponse(
    @SerializedName("data")
    var data: List<ReviewData>?,
) : BaseResponse()


@Parcelize
data class ReviewData(
    @SerializedName("userId")
    var userId: UserData?,
    @SerializedName("productId")
    var productId: TourItemReview?,
    @SerializedName("rating")
    var rating: Int?,
    @SerializedName("reviewText")
    var reviewText: String?,
): Parcelable


@Parcelize
data class TourItemReview(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("categorysId")
    var categorysId: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("image")
    var image: List<String>?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("discount")
    var discount: Int?,
    @SerializedName("quantity")
    var quantity: Int?,
    @SerializedName("favoritesValue")
    var favoritesValue: Int?,
    @SerializedName("reviewValue")
    var reviewValue: Int?,
    @SerializedName("reviewCount")
    var reviewCount: Int?,
): Parcelable