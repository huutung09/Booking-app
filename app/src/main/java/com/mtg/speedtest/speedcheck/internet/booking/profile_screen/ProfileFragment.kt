package com.mtg.speedtest.speedcheck.internet.booking.profile_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.R
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentProfileBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.FbUser
import com.mtg.speedtest.speedcheck.internet.booking.model.User

class ProfileFragment : Fragment() {
    companion object {
        private val TAG = ProfileFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var user: FbUser


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
        binding.tvLogoutProfile.setOnClickListener {
            requireActivity().finish()
        }

        binding.tvUpdateProfile.setOnClickListener {
            user.firstUser = binding.edtFirstNameProfile.text.toString().trim()
            user.lastUser = binding.edtLastNameProfile.text.toString().trim()
            user.passwordUser = binding.edtPasswordProfile.text.toString().trim()

            viewModel.updateUser(requireContext(), user)
        }

        viewModel.user.observe(viewLifecycleOwner) {
            user = it
            binding.tvNameProfile.text = it.firstUser + " " + it.lastUser
            binding.tvEmailProfile.text = it.emailUser
            binding.edtFirstNameProfile.setText(it.firstUser)
            binding.edtLastNameProfile.setText(it.lastUser)
            binding.edtPasswordProfile.setText(it.passwordUser)
        }

        viewModel.statusUpdate.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.update_success),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.update_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.getUser(requireContext())
    }
}