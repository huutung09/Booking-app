package com.mtg.speedtest.speedcheck.internet.booking.detail_category

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActDetailProvinceBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryItem
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem

class DetailCategoryAct : AppCompatActivity() {
    private lateinit var binding: ActDetailProvinceBinding
    private lateinit var detailCategoryAdapter: DetailCategoryAdapter
    private lateinit var category: CategoryItem
    private lateinit var detailCategoryViewModel: DetailCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDetailProvinceBinding.inflate(layoutInflater)
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
        detailCategoryViewModel = ViewModelProvider(this)[DetailCategoryViewModel::class.java]
        category = intent.getParcelableExtra<CategoryItem>("key_detail_category") as CategoryItem
        binding.tvNameProvinceDetail.text = category.name

        detailCategoryViewModel.getTourList(this, category.id!!)

        detailCategoryViewModel.getTourData().observe(this) {
            detailCategoryAdapter = DetailCategoryAdapter(
                this, it.data as MutableList<TourItem>
            ) { hotTrend, _ ->
                val intent = Intent(this, DetailHotTrend::class.java)
                intent.putExtra("key_detail_hotTrend", hotTrend)
                startActivity(intent)
            }
            val layoutManagerProvince: RecyclerView.LayoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.revProvinceDetail.layoutManager = layoutManagerProvince
            binding.revProvinceDetail.adapter = detailCategoryAdapter
        }

    }
}