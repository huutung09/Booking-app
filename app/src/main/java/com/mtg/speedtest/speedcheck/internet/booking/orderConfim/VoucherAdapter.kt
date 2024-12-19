package com.mtg.speedtest.speedcheck.internet.booking.orderConfim

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.formatDate
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ItemVoucherCodeBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.VoucherData

class VoucherAdapter(
    private val context: Context,
    private val listVoucher: MutableList<VoucherData>,
    val clickListener: (VoucherData, Int) -> Unit
) :
    RecyclerView.Adapter<VoucherAdapter.ViewHolder>() {
    private var selectedPosition: Int = -1
    class ViewHolder(val binding: ItemVoucherCodeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemVoucherCodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listVoucher[position]) {
                val code = this.code
                Glide.with(context)
                    .load(this.image)
                    .into(binding.thumbnail)
                binding.tvCode.text = this.code
                binding.tvDescription.text =  this.description
                binding.tvExpiry.text = "End: " + formatDate(this.endDate)
                holder.binding.radioButton.isChecked = position == selectedPosition

                binding.radioButton.setOnClickListener {
                    if (selectedPosition != position) {
                        val previousPosition = selectedPosition
                        selectedPosition = position
                        notifyItemChanged(previousPosition)
                        notifyItemChanged(selectedPosition)
                        clickListener(this, position)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listVoucher.size
    }
}