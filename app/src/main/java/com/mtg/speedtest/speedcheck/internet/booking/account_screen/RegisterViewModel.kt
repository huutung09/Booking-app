package com.mtg.speedtest.speedcheck.internet.booking.account_screen

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.model.FbUser
import kotlinx.coroutines.launch


class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val userMutableLiveData: MutableLiveData<FbUser> = MutableLiveData<FbUser>();
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore
    fun createAccount(context: Context, user: FbUser) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(user.emailUser, user.passwordUser!!).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val fbCurrentUser = auth.currentUser
                        userMutableLiveData.postValue(FbUser(user.emailUser, user.firstUser, user.lastUser, user.passwordUser, fbCurrentUser?.uid))
                        val userMap = hashMapOf(
                            "emailUser" to user.emailUser,
                            "firstUser" to user.firstUser,
                            "lastUser" to user.lastUser,
                            "passwordUser" to user.passwordUser,
                            "tokenUser" to fbCurrentUser?.uid
                        )
                        db.collection("users").document(fbCurrentUser?.uid!!).set(userMap)
                        Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.w("Error", "createUserWithEmail:failure")
                        Toast.makeText(
                            context,
                            "${it.exception?.message}.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

            }catch (e : Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }

    fun getUser(): MutableLiveData<FbUser> {
        return userMutableLiveData
    }


}