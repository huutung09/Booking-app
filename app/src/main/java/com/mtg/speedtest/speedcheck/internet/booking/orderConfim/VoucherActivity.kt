package com.mtg.speedtest.speedcheck.internet.booking.orderConfim

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityVoucherBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.VoucherData

class VoucherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoucherBinding
    private lateinit var viewModel: OrderConfirmViewModel
    private var voucherSelect: VoucherData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoucherBinding.inflate(layoutInflater)
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
        viewModel = ViewModelProvider(this)[OrderConfirmViewModel::class.java]
        viewModel.getVoucherData(this)

        viewModel.getVoucherList().observe(this, {
            val adapter = VoucherAdapter(this, it as MutableList<VoucherData>)  { voucher, _ ->
                voucherSelect = voucher
            }
            val layoutManager: RecyclerView.LayoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewVoucherItems.layoutManager = layoutManager
            binding.recyclerViewVoucherItems.adapter = adapter
        })

        binding.tvApplyVoucher.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("key_voucher", voucherSelect)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}