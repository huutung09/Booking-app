package com.mtg.speedtest.speedcheck.internet.booking.search_screen

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

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val tourData: MutableLiveData<TourData> = MutableLiveData<TourData>();

    fun getTourList(context: Context, searchText: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getTourByName(1, 10, searchText).enqueue(object:
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