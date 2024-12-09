package com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
import com.mtg.speedtest.speedcheck.internet.booking.home_screen.HomeViewModel
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem

class DetailHotTrend : AppCompatActivity() {
    private lateinit var binding: ActDetailHottrendBinding
    private lateinit var hotTrend: TourItem
    private lateinit var viewModel: DetailHotTrendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDetailHottrendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }


    private fun initEvents() {
        binding.imvBack.setOnClickListener {
            finish()
        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[DetailHotTrendViewModel::class.java]

        viewModel.getAddCartResponse().observe(this) {
            if (it != null) {
                finish()
            }
        }

        binding.tvAddToCart.setOnClickListener {
            viewModel.addToCart(this, hotTrend.id!!, SingletonClass.getUserId())
        }

        hotTrend = intent.getParcelableExtra<TourItem>("key_detail_hotTrend") as TourItem
        Glide.with(this)
            .load(hotTrend.image?.get(0))
            .into(binding.imvHotTrendDetail)
        binding.tvNameHotTrendDetail.text = hotTrend.name
        binding.tvDescriptionHotTrendDetail.text = hotTrend.description
        binding.tvRealPrice.text = hotTrend.price.toString() + " VND"
        binding.tvDiscountPrice.text = hotTrend.discount.toString() + " VND"
        binding.tvReviewCount.text = "(" + hotTrend.reviewCount.toString() + " reviews)"
        binding.tvReviewValue.text = hotTrend.reviewValue.toString()

    }

}