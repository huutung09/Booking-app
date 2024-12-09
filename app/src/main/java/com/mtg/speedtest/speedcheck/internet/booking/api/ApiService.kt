package com.mtg.speedtest.speedcheck.internet.booking.api

import com.mtg.speedtest.speedcheck.internet.booking.model.request.AddCartRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserLoginRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserRegisterRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.BaseResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.UserLoginResponse
import retrofit2.http.*
import retrofit2.Call


interface ApiService {
    @POST("user/register")
    fun registerUser(
        @Body bodyRequest: UserRegisterRequest,
    ): Call<BaseResponse>

    @POST("user/login")
    fun loginUser(
        @Body bodyRequest: UserLoginRequest,
    ): Call<UserLoginResponse>

    @GET("category/get-all")
    fun getCategory(): Call<CategoryResponse>

    @GET("product/get-all")
    fun getTour(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
    ): Call<TourResponse>

    @POST("cart")
    fun addCart(
        @Body bodyRequest: AddCartRequest,
    ): Call<BaseResponse>

    @GET("cart/u/{userId}")
    fun getCart(
        @Path("userId") userId: String,
    ): Call<CartResponse>

    @GET("product/get-all")
    fun getTourByName(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("name") name: String,
    ): Call<TourResponse>
}
