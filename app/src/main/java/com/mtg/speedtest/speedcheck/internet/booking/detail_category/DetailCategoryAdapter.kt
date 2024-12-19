package com.mtg.speedtest.speedcheck.internet.booking.detail_category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemHotTrendHomeBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.TourItem

class DetailCategoryAdapter(
    private val context: Context,
    private val listTrend: MutableList<TourItem>,
    val clickListener: (TourItem, Int) -> Unit
) :
    RecyclerView.Adapter<DetailCategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHotTrendHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHotTrendHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listTrend[position]) {
                Glide.with(context)
                    .load(this.image?.get(0))
                    .into(binding.imvHotTrend)
                binding.tvNameHotTrend.text = this.name
                binding.productPrice.text =  CommonUtils.formatVndMoney(this.price.toString())
                binding.tvDescriptionHotTrend.text = this.description
                binding.constraintHotTrendDetail.setOnClickListener {
                    clickListener(listTrend[position], position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listTrend.size
    }


}