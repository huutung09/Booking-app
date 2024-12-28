package com.mtg.speedtest.speedcheck.internet.booking.model.response
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TourItem(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("categorysId")
    var categorysId: CategoryItem?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("image")
    var image: List<String>?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("price")
    var price: Long?,
    @SerializedName("discount")
    var discount: Long?,
    @SerializedName("quantity")
    var quantity: Int?,
    @SerializedName("favoritesValue")
    var favoritesValue: Int?,
    @SerializedName("reviewValue")
    var reviewValue: Int?,
    @SerializedName("reviewCount")
    var reviewCount: Int?,
    @SerializedName("endDate")
    var endDate: String?,
    @SerializedName("startDate")
    var startDate: String?,
): Parcelable
