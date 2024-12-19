package com.mtg.speedtest.speedcheck.internet.booking.detail_category

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val tourData: MutableLiveData<TourData> = MutableLiveData<TourData>();

    fun getTourList(context: Context, categoryId: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getTourByCategory(1, 10, categoryId).enqueue(object:
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

    fun getTourData(): MutableLiveData<TourData> {
        return tourData
    }
}