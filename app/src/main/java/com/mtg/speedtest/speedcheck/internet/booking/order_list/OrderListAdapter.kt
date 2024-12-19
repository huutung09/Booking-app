package com.mtg.speedtest.speedcheck.internet.booking.order_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemCartBinding
import com.mtg.speedtest.speedcheck.internet.booking.databinding.OrderListItemBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.OrderData


class OrderListAdapter(
    private val context: Context,
    private val listOrder: List<OrderData>,
    val clickListener: (OrderData, Int) -> Unit
) :
    RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    class ViewHolder(val binding: OrderListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            OrderListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listOrder[position]) {
                binding.tvOrderId.text = "Order code: #${this.id}"
                binding.tvTotalAmount.text = CommonUtils.formatVndMoney(this.totalAmount.toString())
                binding.btnStatus.text = this.status
                binding.tvDetails.setOnClickListener {
                    clickListener(listOrder[position], position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }


}