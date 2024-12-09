package com.mtg.speedtest.speedcheck.internet.booking.home_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentHomeBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.detail_category.DetailCategoryAct
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryItem
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem


class HomeFragment : Fragment() {
    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var provinceAdapter: ProvinceAdapter
    private lateinit var hotTrendAdapter: HotTrendAdapter
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getCategoryList(requireContext())
        viewModel.getTourList(requireContext())


        viewModel.getCategoryData().observe(viewLifecycleOwner) {
            if (it != null) {
                provinceAdapter = ProvinceAdapter(
                    requireContext(),
                    it.data as MutableList<CategoryItem>
                ) { province, _ ->
//                    val intent = Intent(requireContext(), DetailCategoryAct::class.java)
//                    intent.putExtra("key_detail_province", province)
//                    startActivity(intent)
                }
                val layoutManagerProvince: RecyclerView.LayoutManager =
                    LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
                binding.revProvinceHome.layoutManager = layoutManagerProvince
                binding.revProvinceHome.adapter = provinceAdapter
            }
        }

        viewModel.getTourData().observe(viewLifecycleOwner) {
            if (it != null) {
                hotTrendAdapter = HotTrendAdapter(requireContext(),
                    it.data as MutableList<TourItem>
                ) { hotTrend, _ ->
                    val intent = Intent(requireContext(), DetailHotTrend::class.java)
                    intent.putExtra("key_detail_hotTrend", hotTrend)
                    startActivity(intent)
                }
                val layoutManagerHotTrend: RecyclerView.LayoutManager =
                    LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
                binding.revHotTrend.layoutManager = layoutManagerHotTrend
                binding.revHotTrend.adapter = hotTrendAdapter
            }
        }

    }
}