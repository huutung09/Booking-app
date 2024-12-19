package com.mtg.speedtest.speedcheck.internet.booking.order_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.AddReview.AddReviewActivity
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.formatVndMoney
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityOrderDetailBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.OrderData


class OrderDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailBinding
    private lateinit var orderAdapter: OrderDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
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
        val order = intent.getParcelableExtra<OrderData>("key_detail_order") as OrderData
        binding.orderId.text = "Order code: #${order.id}"
        binding.btnStatus.text = order.status
        binding.tvReceiverName.text = order.userId?.name
        binding.tvReceiverPhone.text = order.userId?.phone
        binding.tvReceiverAddress.text = order.userId?.address

        binding.tvTotalAmount.text = formatVndMoney(order.totalAmount.toString())

        orderAdapter = OrderDetailAdapter(this, order.cartData!!)
        val layoutManagerProvince: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOrderItems.layoutManager = layoutManagerProvince
        binding.recyclerViewOrderItems.adapter = orderAdapter
    }
}