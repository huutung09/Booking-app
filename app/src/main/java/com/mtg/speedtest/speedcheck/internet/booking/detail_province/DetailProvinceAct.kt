package com.mtg.speedtest.speedcheck.internet.booking.detail_province

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActDetailProvinceBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.Province

class DetailProvinceAct : AppCompatActivity() {
    private lateinit var binding: ActDetailProvinceBinding
    private lateinit var detailProvinceAdapter: DetailProvinceAdapter
    private lateinit var province: Province

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDetailProvinceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        detailProvinceAdapter = DetailProvinceAdapter(this, SingletonClass.getInstance().listHotTrend.filter { it.idProvince == province.idProvince }.sortedBy { it.rating }.reversed() as MutableList<HotTrend>) { hotTrend, _ ->
            val intent = Intent(this, DetailHotTrend::class.java)
            intent.putExtra("key_detail_hotTrend", hotTrend)
            startActivity(intent)
        }
        val layoutManagerProvince: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.revProvinceDetail.layoutManager = layoutManagerProvince
        binding.revProvinceDetail.adapter = detailProvinceAdapter
    }

    private fun initViews() {
        province = intent.getSerializableExtra("key_detail_province") as Province
        binding.tvNameProvinceDetail.text = province.addressProvince
        binding.tvDescriptionDetail.text = getString(province.descriptionProvince)
    }
}