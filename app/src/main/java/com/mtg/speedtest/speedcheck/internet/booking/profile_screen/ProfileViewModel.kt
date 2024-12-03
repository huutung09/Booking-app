package com.mtg.speedtest.speedcheck.internet.booking.profile_screen

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.PreferManager
import com.mtg.speedtest.speedcheck.internet.booking.database.EndlessDatabase
import com.mtg.speedtest.speedcheck.internet.booking.model.FbUser
import com.mtg.speedtest.speedcheck.internet.booking.model.User
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val _user = MutableLiveData<FbUser>()
    val user: LiveData<FbUser> = _user

    private val _statusUpdate = MutableLiveData<Boolean>()
    val statusUpdate: LiveData<Boolean> = _statusUpdate
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun getUser(context: Context) {
        viewModelScope.launch {
            try {
                val docRef = db.collection("users").document(auth.currentUser!!.uid)
                docRef.get().addOnSuccessListener {
                    if (it != null) {
//                        val email = it.getString("emailUser")
//                        val first = it.getString("firstUser")
//                        val last = it.getString("lastUser")
//                        val password = it.getString("passwordUser")
//                        val uid = it.getString("tokenUser")
//                        val fbUser = email?.let { it1 -> FbUser(it1, first, last, password, uid) }
//                        if (fbUser != null) {
//                            _user.postValue(fbUser!!)
//                        }
                        _user.postValue(it.toObject(FbUser::class.java))

                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    fun updateUser(context: Context, user: FbUser) {
        viewModelScope.launch {
            try {
                val docRef = db.collection("users").document(auth.currentUser!!.uid)
                docRef.set(user).addOnSuccessListener {
                    _statusUpdate.postValue(true)
                }
            } catch (e: Exception) {
                _statusUpdate.postValue(false)
            }
        }
    }
}