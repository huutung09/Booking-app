package com.mtg.speedtest.speedcheck.internet.booking.search_screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentSearchBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryItem
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem

class SearchFragment : Fragment() {
    companion object {
        private val TAG = SearchFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var viewModel: SearchViewModel
    private var minPrice: Int? = null
    private var maxPrice: Int? = null
    private var selectedCategory: String? = null
    private lateinit var categorySpinnerAdapter: CategorySpinnerAdapter

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
        viewModel.getTourList(requireContext(), binding.edtSearch.text.toString(), minPrice, maxPrice, selectedCategory)
        viewModel.getCategoryList(requireContext())

        binding.ivSearchIcon.setOnClickListener {
            minPrice = binding.edtMinPrice.text.toString().toIntOrNull()
            maxPrice = binding.edtMaxPrice.text.toString().toIntOrNull()
            viewModel.getTourList(requireContext(), binding.edtSearch.text.toString(), minPrice, maxPrice, selectedCategory)
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

        viewModel.getCategoryData().observe(viewLifecycleOwner) {
            if (it != null) {
                val categories = it
                val categoryList = mutableListOf(CategoryItem(id = null, name = "All Categories", image = null))
                categoryList.addAll(categories.data as MutableList<CategoryItem>)

                categorySpinnerAdapter = CategorySpinnerAdapter(requireContext(), categoryList)
                binding.spinnerCategory.adapter = categorySpinnerAdapter

                binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedCategory = (parent?.getItemAtPosition(position) as CategoryItem).id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        selectedCategory = null
                    }
                }
            }
        }


    }

    private fun hideKeyboard(view: EditText) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}