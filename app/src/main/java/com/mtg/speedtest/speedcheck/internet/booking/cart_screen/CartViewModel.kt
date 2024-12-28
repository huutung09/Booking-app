package com.mtg.speedtest.speedcheck.internet.booking.cart_screen

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val cartData: MutableLiveData<List<CartData>> = MutableLiveData<List<CartData>>();


    fun getCartList(context: Context, userId: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getCart(userId).enqueue(object:
                    Callback<CartResponse> {
                    override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.data
                            cartData.postValue(data!!)
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "${t.message}.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                })
            } catch (e: Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }

    fun getCartData(): MutableLiveData<List<CartData>> {
        return cartData
    }

    fun getTotalPrice(list: List<CartData>): Long {
        var totalPrice = 0L
        for (item in list) {
            if (item.productId?.price != null) {
                totalPrice += item.productId!!.price!!
            }
        }
        return totalPrice
    }

}