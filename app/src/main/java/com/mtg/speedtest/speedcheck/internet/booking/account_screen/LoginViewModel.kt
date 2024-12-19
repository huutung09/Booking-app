package com.mtg.speedtest.speedcheck.internet.booking.account_screen

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserLoginRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.UserLoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userTokenMutableLiveData: MutableLiveData<String> = MutableLiveData<String>();

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.loginUser(UserLoginRequest(email, password)).enqueue(object: Callback<UserLoginResponse> {
                    override fun onResponse(call: Call<UserLoginResponse>, response: Response<UserLoginResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val userData = response.body()?.userData
                            userTokenMutableLiveData.postValue(userData?.id.toString())
                            SingletonClass.setUserName(userData?.name.toString())
                            SingletonClass.setAddress(userData?.address.toString())
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
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

    fun getUserTokenMutableLiveData(): MutableLiveData<String> {
        return userTokenMutableLiveData
    }
}