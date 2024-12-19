package com.mtg.speedtest.speedcheck.internet.booking.profile_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentProfileBinding
import com.mtg.speedtest.speedcheck.internet.booking.edit_profile.EditProfileActivity
import com.mtg.speedtest.speedcheck.internet.booking.order_list.OrderListActivity
import com.mtg.speedtest.speedcheck.internet.booking.tour_review.TourReviewActivity

class ProfileFragment : Fragment() {
    companion object {
        private val TAG = ProfileFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initEvents()
    }

    @SuppressLint("SetTextI18n")
    private fun initEvents() {
        binding.llEditProfile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.llLogout.setOnClickListener {
            requireActivity().finish()
        }

        binding.llOrder.setOnClickListener {
             val intent = Intent(requireContext(), OrderListActivity::class.java)
             startActivity(intent)
        }

        binding.llReviews.setOnClickListener {
            val intent = Intent(requireContext(), TourReviewActivity::class.java)
            intent.putExtra("userId", SingletonClass.getUserId())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvNameProfile.text = SingletonClass.getUserName()
    }

    private fun initViews() {
        binding.tvNameProfile.text = SingletonClass.getUserName()
    }
}