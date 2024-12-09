package com.mtg.speedtest.speedcheck.internet.booking.home_screen

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserLoginRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.UserLoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryData: MutableLiveData<CategoryData> = MutableLiveData<CategoryData>();
    private val tourData: MutableLiveData<TourData> = MutableLiveData<TourData>();

    fun getCategoryList(context: Context) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getCategory().enqueue(object:
                    Callback<CategoryResponse> {
                    override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.categoryData
                            categoryData.postValue(data!!)
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
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

    fun getTourList(context: Context) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getTour(1, 10).enqueue(object:
                    Callback<TourResponse> {
                    override fun onResponse(call: Call<TourResponse>, response: Response<TourResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.tourData
                            tourData.postValue(data!!)
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<TourResponse>, t: Throwable) {
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

    fun getCategoryData(): MutableLiveData<CategoryData> {
        return categoryData
    }

    fun getTourData():  MutableLiveData<TourData> {
        return tourData
    }

}