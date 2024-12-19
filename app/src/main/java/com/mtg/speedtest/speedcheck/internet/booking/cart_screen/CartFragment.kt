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
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentCartBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData
import com.mtg.speedtest.speedcheck.internet.booking.orderConfim.OrderConfirmActivity

class CartFragment : Fragment() {
    companion object {
        private val TAG = CartFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
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
//                val intent = Intent(requireContext(), DetailHotTrend::class.java)
//                intent.putExtra("key_detail_hotTrend", hotTrend.productId)
//                startActivity(intent)
            }
            val layoutManagerProvince: RecyclerView.LayoutManager =
                LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
            binding.revCart.layoutManager = layoutManagerProvince
            binding.revCart.adapter = cartAdapter
            binding.tvTotalPrice.text =  "Total price: " + CommonUtils.formatVndMoney(viewModel.getTotalPrice(it).toString())
        }

        binding.tvAddToCart.setOnClickListener {
            val intent = Intent(requireContext(), OrderConfirmActivity::class.java)
            intent.putParcelableArrayListExtra("key_order_confirm", viewModel.getCartData().value as ArrayList<CartData>)
            startActivity(intent)
        }
    }


}