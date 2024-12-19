package com.mtg.speedtest.speedcheck.internet.booking.account_screen

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserRegisterRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.BaseResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val userMutableLiveData: MutableLiveData<BaseResponse> = MutableLiveData<BaseResponse>();
    fun createAccount(context: Context, user: UserRegisterRequest) {
        viewModelScope.launch {
            try {
                ApiClient.instance.registerUser(user).enqueue(object: Callback<BaseResponse> {
                    override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            userMutableLiveData.postValue(response.body())
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
//

            }catch (e : Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }

    fun getUser(): MutableLiveData<BaseResponse> {
        return userMutableLiveData
    }


}