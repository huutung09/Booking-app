package com.mtg.speedtest.speedcheck.internet.booking.home_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentHomeBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.detail_province.DetailProvinceAct
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend


class HomeFragment : Fragment() {
    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var provinceAdapter: ProvinceAdapter
    private lateinit var hotTrendAdapter: HotTrendAdapter


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
        provinceAdapter = ProvinceAdapter(
            requireContext(),
            SingletonClass.getInstance().listProvince
        ) { province, _ ->
            val intent = Intent(requireContext(), DetailProvinceAct::class.java)
            intent.putExtra("key_detail_province", province)
            startActivity(intent)
        }
        val layoutManagerProvince: RecyclerView.LayoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        binding.revProvinceHome.layoutManager = layoutManagerProvince
        binding.revProvinceHome.adapter = provinceAdapter

        hotTrendAdapter = HotTrendAdapter(requireContext(),
            SingletonClass.getInstance().listHotTrend.filter { it.rating >= 4 }
                .sortedBy { it.rating }.reversed() as MutableList<HotTrend>
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