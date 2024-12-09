package com.mtg.speedtest.speedcheck.internet.booking.search_screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentSearchBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem

class SearchFragment : Fragment() {
    companion object {
        private val TAG = SearchFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel.getTourList(requireContext(), binding.edtSearch.text.toString())

        binding.ivSearchIcon.setOnClickListener {
            viewModel.getTourList(requireContext(), binding.edtSearch.text.toString())
            hideKeyboard(binding.edtSearch)
        }

        viewModel.getTourData().observe(viewLifecycleOwner) {
            if (it != null) {
                searchAdapter = SearchAdapter(requireContext(),
                    it.data as MutableList<TourItem>
                ) { hotTrend, _ ->
                    val intent = Intent(requireContext(), DetailHotTrend::class.java)
                    intent.putExtra("key_detail_hotTrend", hotTrend)
                    startActivity(intent)
                }
                val layoutManagerProvince: RecyclerView.LayoutManager =
                    LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
                binding.revFavorite.layoutManager = layoutManagerProvince
                binding.revFavorite.adapter = searchAdapter            }
        }


    }

    private fun hideKeyboard(view: EditText) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}