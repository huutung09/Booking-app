package com.mtg.speedtest.speedcheck.internet.booking.account_screen

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.database.EndlessDatabase
import com.mtg.speedtest.speedcheck.internet.booking.model.FbUser
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.Province
import com.mtg.speedtest.speedcheck.internet.booking.model.User
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _listUsers = MutableLiveData<MutableList<User>>()
    val listUsers: LiveData<MutableList<User>> = _listUsers
    private val userTokenMutableLiveData: MutableLiveData<String> = MutableLiveData<String>();
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

     fun getListUser(context: Context) {
        viewModelScope.launch {
            try {
                val list = EndlessDatabase.getInstance(context)?.userDao()?.getListUsers()!!
                _listUsers.postValue(list)
            } catch (e: Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = auth.currentUser
                        userTokenMutableLiveData.postValue(user?.uid)
                    } else {
                        Log.e("Logger", "${it.exception?.message}")
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }

    fun getUserTokenMutableLiveData(): MutableLiveData<String> {
        return userTokenMutableLiveData
    }

    fun getListHotTrend() {
        db.collection("hot_trends")
            .get()
            .addOnSuccessListener {
                SingletonClass.getInstance().listHotTrend.clear()
                for (document in it) {
                    val hotTrend = document.toObject(HotTrend::class.java)
                    SingletonClass.getInstance().listHotTrend.add(hotTrend)
                }
            }

    }

    fun getListProvince() {
        db.collection("provinces")
            .get()
            .addOnSuccessListener {
                SingletonClass.getInstance().listProvince.clear()
                for (document in it) {
                    val province = document.toObject(Province::class.java)
                    SingletonClass.getInstance().listProvince.add(province)
                }
            }

    }
}