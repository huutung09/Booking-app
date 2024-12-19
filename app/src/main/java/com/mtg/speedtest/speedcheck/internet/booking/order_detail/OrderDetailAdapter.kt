package com.mtg.speedtest.speedcheck.internet.booking.order_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemCartBinding
import com.mtg.speedtest.speedcheck.internet.booking.databinding.OrderItemDetailBinding
import com.mtg.speedtest.speedcheck.internet.booking.databinding.OrderListItemBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartOrderData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.OrderData


class OrderDetailAdapter(
    private val context: Context,
    private val listOrder: List<CartOrderData>,
) :
    RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {

    class ViewHolder(val binding: OrderItemDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            OrderItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listOrder[position]) {
                Glide.with(context)
                    .load(this.productId?.image?.get(0))
                    .into(binding.ivProduct)
                binding.tvTitle.text = this.productId?.name
                binding.tvDescription.text = this.productId?.description
            }
        }
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }


}