package com.mtg.speedtest.speedcheck.internet.booking.cart_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentCartBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend

class CartFragment : Fragment() {
    companion object {
        private val TAG = CartFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val listBookmark = mutableListOf<HotTrend>()
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartList(requireContext(), SingletonClass.getUserId())
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        viewModel.getCartList(requireContext(), SingletonClass.getUserId())

        viewModel.getCartData().observe(viewLifecycleOwner) {
            cartAdapter = CartAdapter(requireContext(), it) { hotTrend, _ ->
                val intent = Intent(requireContext(), DetailHotTrend::class.java)
                intent.putExtra("key_detail_hotTrend", hotTrend.productId)
                startActivity(intent)
            }
            val layoutManagerProvince: RecyclerView.LayoutManager =
                LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
            binding.revBookmark.layoutManager = layoutManagerProvince
            binding.revBookmark.adapter = cartAdapter
        }


    }


}