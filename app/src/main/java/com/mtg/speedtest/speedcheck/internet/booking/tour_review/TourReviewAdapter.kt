package com.mtg.speedtest.speedcheck.internet.booking.tour_review

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ReviewItemBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.response.ReviewData

class TourReviewAdapter(
    private val context: Context,
    private val listFavorite: MutableList<ReviewData>,
) :
    RecyclerView.Adapter<TourReviewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFavorite[position]) {
                binding.userName.text = this.userId?.name
                binding.ratingBar.rating = this.rating?.toFloat() ?: 0f
                binding.reviewText.text = this.reviewText
            }
        }
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }


}