package com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.R
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActDetailHottrendBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend

class DetailHotTrend : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActDetailHottrendBinding
    private lateinit var hotTrend: HotTrend
    private lateinit var mMap: GoogleMap
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDetailHottrendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMaps()
        initViews()
        initEvents()
    }

    private fun initMaps() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapHotTrendDetail) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initEvents() {
        binding.imvBack.setOnClickListener {
            finish()
        }

        binding.imvFavoriteHotTrendDetail.setOnClickListener {
            if (hotTrend.isFavorite) {
                hotTrend.isFavorite = false
                removeFavorite()
                binding.imvFavoriteHotTrendDetail.setImageResource(R.drawable.ic_favorite_deactivate)
            } else {
                hotTrend.isFavorite = true
                setFavorite()
                binding.imvFavoriteHotTrendDetail.setImageResource(R.drawable.ic_favorite_active)
            }
        }
//
        binding.imvBookmarkHotTrendDetail.setOnClickListener {
            if (hotTrend.isBookMark) {
                hotTrend.isBookMark = false
                removeBookmark()
                binding.imvBookmarkHotTrendDetail.setImageResource(R.drawable.ic_bookmark_deactivate)
            } else {
                hotTrend.isBookMark = true
                setBookmark()
                binding.imvBookmarkHotTrendDetail.setImageResource(R.drawable.ic_bookmark_active)
            }
        }



    }

    private fun initViews() {
        hotTrend = intent.getSerializableExtra("key_detail_hotTrend") as HotTrend
        Glide.with(this)
            .load(hotTrend.imageHotTrend)
            .into(binding.imvHotTrendDetail)
        binding.tvNameHotTrendDetail.text = hotTrend.nameHotTrend
        binding.tvDescriptionHotTrendDetail.text = resources.getString(hotTrend.description)
        binding.ratingBarHotTrendDetail.rating = hotTrend.rating

        if (hotTrend.isFavorite) {
            binding.imvFavoriteHotTrendDetail.setImageResource(R.drawable.ic_favorite_active)
        } else {
            binding.imvFavoriteHotTrendDetail.setImageResource(R.drawable.ic_favorite_deactivate)
        }

        if (hotTrend.isBookMark) {
            binding.imvBookmarkHotTrendDetail.setImageResource(R.drawable.ic_bookmark_active)
        } else {
            binding.imvBookmarkHotTrendDetail.setImageResource(R.drawable.ic_bookmark_deactivate)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker and move the camera to a location
        val location = LatLng(hotTrend.latitude.toDouble(), hotTrend.longitude.toDouble())
        mMap.addMarker(location, "Marker in ${hotTrend.nameHotTrend}")
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18f))
    }

    // Extension function to add a marker
    private fun GoogleMap.addMarker(location: LatLng, title: String) {
        addMarker(com.google.android.gms.maps.model.MarkerOptions().position(location).title(title))
    }

    private fun setFavorite() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val collectionRef = db.collection("users").document(currentUser.uid).collection("favorite")
            collectionRef.add(hotTrend)
        }
    }
    private fun removeFavorite() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val query = db.collection("users").document(currentUser.uid).collection("favorite").whereEqualTo("idHotTrend", hotTrend.idHotTrend)
            query.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (documentSnapshot in task.result!!) {
                        val documentReference = documentSnapshot.reference
                        documentReference.delete()
                            .addOnSuccessListener {
                                Log.d("Firestore", "Element deleted successfully!")
                            }
                            .addOnFailureListener { e ->
                                Log.w("Firestore", "Error deleting element", e)
                            }
                    }
                } else {
                    Log.w("Firestore", "Error getting documents: ", task.exception)
                }
            }
        }
    }

    private fun setBookmark() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val collectionRef = db.collection("users").document(currentUser.uid).collection("bookmark")
            collectionRef.add(hotTrend)
        }
    }
    private fun removeBookmark() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val query = db.collection("users").document(currentUser.uid).collection("bookmark").whereEqualTo("idHotTrend", hotTrend.idHotTrend)
            query.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (documentSnapshot in task.result!!) {
                        val documentReference = documentSnapshot.reference
                        documentReference.delete()
                            .addOnSuccessListener {
                                Log.d("Firestore", "Element deleted successfully!")
                            }
                            .addOnFailureListener { e ->
                                Log.w("Firestore", "Error deleting element", e)
                            }
                    }
                } else {
                    Log.w("Firestore", "Error getting documents: ", task.exception)
                }
            }
        }
    }
}