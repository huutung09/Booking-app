package com.mtg.speedtest.speedcheck.internet.booking.edit_profile

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UpdateUserResquest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.BaseResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.UserData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.UserLoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val profileData: MutableLiveData<UserData> = MutableLiveData<UserData>();
    private val updateStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>();

    fun getProfile(context: Context,  userId: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getUserProfile(userId).enqueue(object:
                    Callback<UserLoginResponse> {
                    override fun onResponse(call: Call<UserLoginResponse>, response: Response<UserLoginResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.userData
                            profileData.postValue(data!!)
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

    fun updateProfile(userId: String, name: String, email: String, phone: String, address: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.updateUserProfile(userId, UpdateUserResquest(email, address, name, phone)).enqueue(object:
                    Callback<BaseResponse> {
                    override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            SingletonClass.setUserName(name)
                            SingletonClass.setAddress(address)
                            updateStatus.postValue(true)
                        } else {
                           updateStatus.postValue(false)
                        }
                    }
                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        updateStatus.postValue(false)
                    }
                })
            } catch (e: Exception) {
                Log.e("Logger", "${e.message}")
                updateStatus.postValue(false)
            }
        }
    }

    fun getProfileData(): MutableLiveData<UserData> {
        return profileData
    }

    fun getStatusUpdate(): MutableLiveData<Boolean> {
        return updateStatus
    }
}