package com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.AddReview.AddReviewActivity
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActDetailHottrendBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem
import com.mtg.speedtest.speedcheck.internet.booking.tour_review.TourReviewActivity

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
        binding.tvReview.setOnClickListener {
            val intent = Intent(this, AddReviewActivity::class.java)
            intent.putExtra("productId", hotTrend.id)
            startActivity(intent)
        }

        binding.tvReviewValue.setOnClickListener {
            val intent = Intent(this, TourReviewActivity::class.java)
            intent.putExtra("productId", hotTrend.id)
            startActivity(intent)
        }

        binding.tvReviewCount.setOnClickListener {
            val intent = Intent(this, TourReviewActivity::class.java)
            intent.putExtra("productId", hotTrend.id)
            startActivity(intent)
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
        binding.tvRealPrice.text = CommonUtils.formatVndMoney( hotTrend.price.toString())
        binding.tvDiscountPrice.text = CommonUtils.formatVndMoney(hotTrend.discount.toString())
        binding.tvReviewCount.text = "(" + hotTrend.reviewCount.toString() + " reviews)"
        binding.tvReviewValue.text = hotTrend.reviewValue.toString()

    }

}