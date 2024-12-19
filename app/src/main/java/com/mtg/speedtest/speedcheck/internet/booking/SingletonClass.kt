package com.mtg.speedtest.speedcheck.internet.booking


class SingletonClass private constructor() {
    var userId = ""
    var userName = ""
    var address = ""



    companion object {
        @Volatile
        private var instance: SingletonClass? = null

        // Function to obtain the instance of the SingletonClass
        fun getInstance(): SingletonClass {
            return instance ?: synchronized(this) {
                instance ?: SingletonClass().also { instance = it }
            }
        }

        fun setUserId(userId: String) {
            getInstance().userId = userId
        }

        fun getUserId(): String {
            return getInstance().userId
        }

        fun setUserName(userName: String) {
            getInstance().userName = userName
        }

        fun getUserName(): String {
            return getInstance().userName
        }

        fun setAddress(address: String) {
            getInstance().address = address
        }

        fun getAddress(): String {
            return getInstance().address
        }
    }
}