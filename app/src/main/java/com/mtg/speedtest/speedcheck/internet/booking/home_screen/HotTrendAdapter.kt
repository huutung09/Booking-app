package com.mtg.speedtest.speedcheck.internet.booking.home_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemHotTrendHomeBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend

class HotTrendAdapter(
    private val context: Context,
    private val listHotTrend: MutableList<HotTrend>,
    val clickListener: (HotTrend, Int) -> Unit
) :
    RecyclerView.Adapter<HotTrendAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHotTrendHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHotTrendHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listHotTrend[position]) {
                Glide.with(context)
                    .load(this.imageHotTrend)
                    .into(binding.imvHotTrend)
                binding.tvNameHotTrend.text = this.nameHotTrend
                binding.tvAddressHotTrend.text = this.addressHotTrend
                binding.tvDescriptionHotTrend.text = context.getString(this.description)
                binding.ratingBarHotTrend.rating = this.rating
                binding.constraintHotTrendDetail.setOnClickListener {
                    clickListener(listHotTrend[position], position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listHotTrend.size
    }


}