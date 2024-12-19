package com.mtg.speedtest.speedcheck.internet.booking.order_list

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.response.OrderData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.OrderListResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderListViewModel(application: Application) : AndroidViewModel(application) {
    private val orderData: MutableLiveData<List<OrderData>> = MutableLiveData<List<OrderData>>();


    fun getOrderList(context: Context, userId: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getOrderList(userId).enqueue(object:
                    Callback<OrderListResponse> {
                    override fun onResponse(call: Call<OrderListResponse>, response: Response<OrderListResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.data
                            orderData.postValue(data!!)
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
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

    fun getOrderData(): MutableLiveData<List<OrderData>> {
        return orderData
    }
}