package com.mtg.speedtest.speedcheck.internet.booking.model.response
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryResponse(
    @SerializedName("data") val categoryData: CategoryData
) : BaseResponse()


@Parcelize
data class CategoryData(
    @SerializedName("data")
    var data: List<CategoryItem>?,
): Parcelable

@Parcelize
data class CategoryItem(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("image")
    var image: String?,
): Parcelable