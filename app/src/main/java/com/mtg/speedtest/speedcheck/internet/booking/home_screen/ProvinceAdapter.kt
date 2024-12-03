package com.mtg.speedtest.speedcheck.internet.booking.home_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemProvinceHomeBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.Province

class ProvinceAdapter(
    private val context: Context,
    private val listProvince: MutableList<Province>,
    val clickListener: (Province, Int) -> Unit
) :
    RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProvinceHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProvinceHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listProvince[position]) {
                Glide.with(context)
                    .load(this.imageProvince)
                    .into(binding.imvProvince)
                binding.tvAddressProvince.text = this.addressProvince
                binding.tvDescriptionProvince.text = context.getString(this.descriptionProvince)
                binding.constraintItemProvince.setOnClickListener {
                    clickListener(listProvince[position], position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listProvince.size
    }


}