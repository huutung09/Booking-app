package com.mtg.speedtest.speedcheck.internet.booking.tour_review

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.AddReview.AddReviewViewModel
import com.mtg.speedtest.speedcheck.internet.booking.R
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityAddReviewBinding
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityTourReviewBinding
import com.mtg.speedtest.speedcheck.internet.booking.order_detail.OrderDetailActivity
import com.mtg.speedtest.speedcheck.internet.booking.order_list.OrderListAdapter

class TourReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTourReviewBinding
    private lateinit var viewModel: TourReviewViewModel
    private lateinit var adapter: TourReviewAdapter
    private  var productId: String? = null
    private  var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourReviewBinding.inflate(layoutInflater)
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
        viewModel = ViewModelProvider(this)[TourReviewViewModel::class.java]
        productId = intent.getStringExtra("productId")
        userId = intent.getStringExtra("userId")
        if (userId != null) {
            viewModel.getReviewListByUser(this, userId!!)
        } else {
            viewModel.getReviewList(this, productId!!)
        }

        viewModel.getReviewData().observe(this) {
            adapter = TourReviewAdapter(this, it as MutableList)
            val layoutManagerProvince: RecyclerView.LayoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.reviewRecyclerView.layoutManager = layoutManagerProvince
            binding.reviewRecyclerView.adapter = adapter
        }

    }
}