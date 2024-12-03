package com.mtg.speedtest.speedcheck.internet.booking.model

import java.io.Serializable

data class HotTrend(
    val idHotTrend: Int = 0,
    val imageHotTrend: String = "",
    val nameHotTrend: String = "",
    val addressHotTrend: String = "",
    val idProvince: Int = 0,
    var isFavorite: Boolean = false,
    var isBookMark: Boolean = false,
    val description: Int = 0,
    val rating: Float = 0F,
    val latitude: Float = 0F,
    val longitude: Float = 0F
) :
    Serializable