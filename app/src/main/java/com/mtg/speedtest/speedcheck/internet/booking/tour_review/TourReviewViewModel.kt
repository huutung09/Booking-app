package com.mtg.speedtest.speedcheck.internet.booking.tour_review

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.response.ReviewData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.ReviewResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TourReviewViewModel(application: Application) : AndroidViewModel(application) {
    private val reviewData: MutableLiveData<List<ReviewData>> = MutableLiveData<List<ReviewData>>();

    fun getReviewList(context: Context, productId: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getProductReview(productId).enqueue(object:
                    Callback<ReviewResponse> {
                    override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.data
                            reviewData.postValue(data!!)
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
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

    fun getReviewListByUser(context: Context, userId: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getUserReview(userId).enqueue(object:
                    Callback<ReviewResponse> {
                    override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.data
                            reviewData.postValue(data!!)
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
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

    fun getReviewData(): MutableLiveData<List<ReviewData>> {
        return reviewData
    }
}