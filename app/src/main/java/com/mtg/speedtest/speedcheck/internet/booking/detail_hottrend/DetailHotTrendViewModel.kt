package com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.request.AddCartRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.BaseResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailHotTrendViewModel(application: Application) : AndroidViewModel(application) {
    private val addCartResponse: MutableLiveData<BaseResponse> = MutableLiveData<BaseResponse>();


    fun addToCart(context: Context, productId: String, userId: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.addCart(AddCartRequest("1", productId, userId)).enqueue(object: Callback<BaseResponse> {
                    override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            addCartResponse.postValue(response.body())
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        Log.w("Error", "createUserWithEmail:failure")
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

    fun getAddCartResponse(): MutableLiveData<BaseResponse> {
        return addCartResponse
    }

}