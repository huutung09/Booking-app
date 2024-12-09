package com.mtg.speedtest.speedcheck.internet.booking.detail_category

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActDetailProvinceBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryItem

class DetailCategoryAct : AppCompatActivity() {
    private lateinit var binding: ActDetailProvinceBinding
    private lateinit var detailCategoryAdapter: DetailCategoryAdapter
    private lateinit var category: CategoryItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDetailProvinceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {
//        detailCategoryAdapter = DetailCategoryAdapter(this, SingletonClass.getInstance().listHotTrend.filter { it.idProvince == province.idProvince }.sortedBy { it.rating }.reversed() as MutableList<HotTrend>) { hotTrend, _ ->
//            val intent = Intent(this, DetailHotTrend::class.java)
//            intent.putExtra("key_detail_hotTrend", hotTrend)
//            startActivity(intent)
//        }
//        val layoutManagerProvince: RecyclerView.LayoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.revProvinceDetail.layoutManager = layoutManagerProvince
//        binding.revProvinceDetail.adapter = detailCategoryAdapter
    }

    private fun initViews() {
        category = intent.getParcelableExtra<CategoryItem>("key_detail_province") as CategoryItem
        binding.tvNameProvinceDetail.text = category.name
        binding.tvDescriptionDetail.text = category.name
    }
}