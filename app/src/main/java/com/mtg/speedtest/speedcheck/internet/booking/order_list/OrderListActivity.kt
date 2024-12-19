package com.mtg.speedtest.speedcheck.internet.booking.order_list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.AddReview.AddReviewActivity
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.cart_screen.CartAdapter
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityOrderListBinding
import com.mtg.speedtest.speedcheck.internet.booking.order_detail.OrderDetailActivity

class OrderListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderListBinding
    private lateinit var viewModel: OrderListViewModel
    private lateinit var orderAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
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
        viewModel = ViewModelProvider(this)[OrderListViewModel::class.java]
        viewModel.getOrderList(this, SingletonClass.getUserId())

        viewModel.getOrderData().observe(this, {
            orderAdapter = OrderListAdapter(this, it) { order, _ ->
                val intent = Intent(this, OrderDetailActivity::class.java)
                intent.putExtra("key_detail_order", order)
                startActivity(intent)
            }
            val layoutManagerProvince: RecyclerView.LayoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewOrderItems.layoutManager = layoutManagerProvince
            binding.recyclerViewOrderItems.adapter = orderAdapter
        })
    }
}