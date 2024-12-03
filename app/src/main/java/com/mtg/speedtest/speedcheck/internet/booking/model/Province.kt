package com.mtg.speedtest.speedcheck.internet.booking.model

import java.io.Serializable

data class Province(
    val idProvince: Int = 0,
    val imageProvince: String = "",
    val addressProvince: String = "",
    val descriptionProvince: Int = 0
) :
    Serializable