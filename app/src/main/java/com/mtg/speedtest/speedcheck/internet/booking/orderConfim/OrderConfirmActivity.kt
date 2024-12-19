package com.mtg.speedtest.speedcheck.internet.booking.orderConfim

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.formatVndMoney
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActOrderConfirmationBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.VoucherData
import com.mtg.speedtest.speedcheck.internet.booking.payment_screen.PaymentActivity


class OrderConfirmActivity : AppCompatActivity(){
    private lateinit var binding: ActOrderConfirmationBinding
    private lateinit var viewModel: OrderConfirmViewModel
    private lateinit var orderData: ArrayList<CartData>
    private lateinit var orderConfirmAdapter: OrderConfirmAdapter

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val voucher = data?.getParcelableExtra<VoucherData>("key_voucher")
            if (voucher != null) {
                viewModel.setSelectedVoucher(voucher)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        binding.imvBack.setOnClickListener {
            finish()
        }
        binding.tvAddVoucher.setOnClickListener {
            val intent = Intent(this, VoucherActivity::class.java)
            resultLauncher.launch(intent)
        }

        binding.tvUpdateProfile.setOnClickListener {
            val orderIds = orderData.map { it.id!! }
           viewModel.createOrder(this, SingletonClass.getUserId(), orderIds, SingletonClass.getAddress())
        }
    }

    private fun getTotalValue(): Int {
        var total = 0
        for (item in orderData) {
            total += item.productId?.price ?: 0
        }
        return total
    }

    private fun initViews() {
        orderData = intent.getParcelableArrayListExtra("key_order_confirm") ?: ArrayList()

        viewModel = ViewModelProvider(this)[OrderConfirmViewModel::class.java]

        orderConfirmAdapter = OrderConfirmAdapter(this,
            orderData as MutableList<CartData>
        )
        val layoutManagerHotTrend: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOrderItems.layoutManager = layoutManagerHotTrend
        binding.recyclerViewOrderItems.adapter = orderConfirmAdapter

        viewModel.getSelectedVoucher().observe(this) {
            binding.tvAddVoucher.text = it.code
            binding.tvDiscount.text = formatVndMoney(it.discountValue.toString())
            binding.tvTotalPayment.text = formatVndMoney((getTotalValue() - it.discountValue!!).toString())
        }

        binding.tvTotalItemsCost.text = formatVndMoney(getTotalValue().toString())
        binding.tvTotalPayment.text = formatVndMoney(getTotalValue().toString())

        viewModel.getPaymentUrl().observe(this) {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("key_payment_url", it)
            startActivity(intent)
        }
    }
}