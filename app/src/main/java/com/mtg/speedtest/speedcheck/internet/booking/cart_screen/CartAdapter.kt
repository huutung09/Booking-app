package com.mtg.speedtest.speedcheck.internet.booking.cart_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemHotTrendHomeBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CartData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem

class CartAdapter(
    private val context: Context,
    private val listCart: List<CartData>,
    val clickListener: (CartData, Int) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHotTrendHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHotTrendHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listCart[position]) {
                Glide.with(context)
                    .load(this.productId?.image?.get(0))
                    .into(binding.imvHotTrend)
                binding.tvNameHotTrend.text = this.productId?.name
                binding.tvAddressHotTrend.text = this.productId?.price.toString() + " VND"
                binding.tvDescriptionHotTrend.text = this.productId?.description
                binding.constraintHotTrendDetail.setOnClickListener {
                    clickListener(listCart[position], position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listCart.size
    }


}