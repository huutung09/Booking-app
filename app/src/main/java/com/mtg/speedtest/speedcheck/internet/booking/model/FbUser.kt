package com.mtg.speedtest.speedcheck.internet.booking.model

import java.io.Serializable

data class FbUser(
    val emailUser: String = "",
    var firstUser: String? = "",
    var lastUser: String? = "",
    var passwordUser: String? = "",
    val tokenUser: String? = "",
) : Serializable
