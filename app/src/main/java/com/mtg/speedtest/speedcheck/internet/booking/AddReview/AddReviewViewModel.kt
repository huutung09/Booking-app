package com.mtg.speedtest.speedcheck.internet.booking.AddReview

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.request.ProductReviewRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserRegisterRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.BaseResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddReviewViewModel(application: Application) : AndroidViewModel(application) {

    private val resMutableLiveData: MutableLiveData<BaseResponse> = MutableLiveData<BaseResponse>();

    fun addReview(context: Context, userId: String, productId: String, rating: Int, reviewText: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.reviewProduct(ProductReviewRequest(userId, productId, rating, reviewText)).enqueue(object: Callback<BaseResponse> {
                    override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            resMutableLiveData.postValue(response.body())
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
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
            }catch (e : Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }

    fun getRes(): MutableLiveData<BaseResponse> {
        return resMutableLiveData
    }
}