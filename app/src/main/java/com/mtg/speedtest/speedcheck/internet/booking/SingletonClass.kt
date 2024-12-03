package com.mtg.speedtest.speedcheck.internet.booking

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.Province


class SingletonClass private constructor() {
    val listHotTrend: MutableList<HotTrend> = mutableListOf()
    val listProvince: MutableList<Province> = mutableListOf()



    companion object {
        // The single instance of the class
        @Volatile
        private var instance: SingletonClass? = null

        // Function to obtain the instance of the SingletonClass
        fun getInstance(): SingletonClass {
            return instance ?: synchronized(this) {
                instance ?: SingletonClass().also { instance = it }
            }
        }

//        fun uploadHotTrend() {
//            val listHotTrend = getInstance().listHotTrend
//            val db = FirebaseFirestore.getInstance()
//            val hotTrendsCollection = db.collection("hot_trends")
//
//            for (trend in listHotTrend) {
//                val trendMap: MutableMap<String, Any> = HashMap()
//                trendMap["nameHotTrend"] = trend.nameHotTrend
//                trendMap["idHotTrend"] = trend.idHotTrend
//                trendMap["addressHotTrend"] = trend.addressHotTrend
//                trendMap["idProvince"] = trend.idProvince
//                trendMap["description"] = trend.description
//                trendMap["rating"] = trend.rating
//                trendMap["latitude"] = trend.latitude
//                trendMap["longitude"] = trend.longitude
//                trendMap["imageHotTrend"] = trend.imageHotTrend
//
//
//                hotTrendsCollection.add(trendMap)
//                    .addOnSuccessListener { documentReference ->
//                        Log.d(
//                            "Firestore",
//                            "Document added with ID: " + documentReference.id
//                        )
//                    }
//                    .addOnFailureListener { e -> Log.w("Firestore", "Error adding document", e) }
//            }
//        }
//        fun uploadProvince() {
//            val listHotTrend = getInstance().listProvince
//            val db = FirebaseFirestore.getInstance()
//            val hotTrendsCollection = db.collection("provinces")
//
//            for (trend in listHotTrend) {
//                val trendMap: MutableMap<String, Any> = HashMap()
//                trendMap["idProvince"] = trend.idProvince
//                trendMap["addressProvince"] = trend.addressProvince
//                trendMap["descriptionProvince"] = trend.descriptionProvince
//                trendMap["imageProvince"] = trend.imageProvince
//
//
//
//                hotTrendsCollection.add(trendMap)
//                    .addOnSuccessListener { documentReference ->
//                        Log.d(
//                            "Firestore",
//                            "Document added with ID: " + documentReference.id
//                        )
//                    }
//                    .addOnFailureListener { e -> Log.w("Firestore", "Error adding document", e) }
//            }
//        }
    }
}