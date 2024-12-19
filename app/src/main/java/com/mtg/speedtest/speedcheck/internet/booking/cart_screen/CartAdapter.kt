package com.mtg.speedtest.speedcheck.internet.booking.cart_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemCartBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData

class CartAdapter(
    private val context: Context,
    private val listCart: List<CartData>,
    val clickListener: (CartData, Int) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listCart[position]) {
                Glide.with(context)
                    .load(this.productId?.image?.get(0))
                    .into(binding.productImage)
                binding.productName.text = this.productId?.name
                binding.productPrice.text = CommonUtils.formatVndMoney(this.productId?.price.toString())
                binding.productDescription.text = this.productId?.description
//                binding.constraintHotTrendDetail.setOnClickListener {
//                    clickListener(listCart[position], position)
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listCart.size
    }


}