package com.mtg.speedtest.speedcheck.internet.booking.AddReview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.R
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityAddReviewBinding
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityOrderListBinding
import com.mtg.speedtest.speedcheck.internet.booking.order_detail.OrderDetailActivity
import com.mtg.speedtest.speedcheck.internet.booking.order_list.OrderListAdapter
import com.mtg.speedtest.speedcheck.internet.booking.order_list.OrderListViewModel

class AddReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddReviewBinding
    private lateinit var viewModel: AddReviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        binding.imvBack.setOnClickListener {
            finish()
        }

        binding.btnSubmitReview.setOnClickListener {
            val userId = SingletonClass.getUserId()
            val productId = intent.getStringExtra("productId")
            val rating = binding.simpleRatingBar.rating.toInt()
            val reviewText = binding.edtReview.text.toString().trim()
            if (reviewText.isEmpty()) {
                binding.edtReview.error = "Please enter review"
            } else {
                viewModel.addReview(this, userId, productId!!, rating, reviewText)
            }
        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[AddReviewViewModel::class.java]

        viewModel.getRes().observe(this) {
            if (it != null) {
                finish()
            }
        }
    }
}