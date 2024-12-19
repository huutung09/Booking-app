package com.mtg.speedtest.speedcheck.internet.booking.orderConfim

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.databinding.OrderItemBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData

class OrderConfirmAdapter(
    private val context: Context,
    private val listCart: MutableList<CartData>,
) :
    RecyclerView.Adapter<OrderConfirmAdapter.ViewHolder>() {

    class ViewHolder(val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listCart[position]) {
                Glide.with(context)
                    .load(this.productId?.image?.get(0))
                    .into(binding.imvProductImage)
                binding.tvProductName.text = this.productId?.name
                binding.tvProductPrice.text =  CommonUtils.formatVndMoney(this.productId?.price.toString())
                binding.tvProductDescription.text = this.productId?.description

            }
        }
    }

    override fun getItemCount(): Int {
        return listCart.size
    }
}