package com.mtg.speedtest.speedcheck.internet.booking.edit_profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityEditProfileBinding


class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {

//        binding.tvUpdateProfile.setOnClickListener {
//            user.firstUser = binding.edtFirstNameProfile.text.toString().trim()
//            user.lastUser = binding.edtLastNameProfile.text.toString().trim()
//            user.passwordUser = binding.edtPasswordProfile.text.toString().trim()
//
//            viewModel.updateUser(requireContext(), user)
//        }
//
//        viewModel.user.observe(viewLifecycleOwner) {
//            user = it
//            binding.tvNameProfile.text = it.firstUser + " " + it.lastUser
//            binding.tvEmailProfile.text = it.emailUser
//            binding.edtFirstNameProfile.setText(it.firstUser)
//            binding.edtLastNameProfile.setText(it.lastUser)
//            binding.edtPasswordProfile.setText(it.passwordUser)
//        }
//
//        viewModel.statusUpdate.observe(viewLifecycleOwner) {
//            if (it) {
//                Toast.makeText(
//                    requireContext(),
//                    getString(R.string.update_success),
//                    Toast.LENGTH_SHORT
//                ).show()
//            } else {
//                Toast.makeText(
//                    requireContext(),
//                    getString(R.string.update_failed),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]
    }
}