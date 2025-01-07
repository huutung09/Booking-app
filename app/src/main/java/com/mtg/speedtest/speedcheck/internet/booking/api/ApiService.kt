package com.mtg.speedtest.speedcheck.internet.booking.api

import com.mtg.speedtest.speedcheck.internet.booking.model.request.AddCartRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.CreateOrderRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.ProductReviewRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UpdateUserResquest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserLoginRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserRegisterRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.BaseResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CreateOrderResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.OrderListResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.ReviewResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.UserLoginResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.VoucherResponse
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
        @Query("name") name: String?,
        @Query("priceMin") priceMin: Int?,
        @Query("priceMax") priceMax: Int?,
        @Query("category") category: String?,
        ): Call<TourResponse>

    @GET("user/{userId}")
    fun getUserProfile(
        @Path("userId") userId: String,
        ): Call<UserLoginResponse>

    @PUT("user/{userId}")
    fun updateUserProfile(
        @Path("userId") userId: String,
        @Body bodyRequest: UpdateUserResquest,
    ): Call<BaseResponse>

    @GET("voucher/get-all")
    fun getAllVoucher(
    ): Call<VoucherResponse>

    @POST("order")
    fun createOrder(
        @Body bodyRequest: CreateOrderRequest,
    ): Call<CreateOrderResponse>

    @GET("order/u/{userId}")
    fun getOrderList(
        @Path("userId") userId: String,
        ): Call<OrderListResponse>

    @GET("product/get-all")
    fun getTourByCategory(
        @Query("category") category: String,
    ): Call<TourResponse>

    @POST("product-review")
    fun reviewProduct(
        @Body bodyRequest: ProductReviewRequest,
        ): Call<BaseResponse>

    @GET("product-review/p/{productId}")
    fun getProductReview(
        @Path("productId") productId: String,
    ): Call<ReviewResponse>

    @GET("product-review/u/{userId}")
    fun getUserReview(
        @Path("userId") userId: String,
    ): Call<ReviewResponse>
}
