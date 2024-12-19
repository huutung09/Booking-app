package com.mtg.speedtest.speedcheck.internet.booking.edit_profile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mtg.speedtest.speedcheck.internet.booking.R
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
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
        binding.imvBack.setOnClickListener {
            finish()
        }

        binding.tvUpdateProfile.setOnClickListener {
            val email = binding.edtEmailProfile.text.toString().trim()
            val name = binding.edtNameProfile.text.toString().trim()
            val phone = binding.edtPhoneProfile.text.toString().trim()
            val address = binding.edtAddressProfile.text.toString().trim()

            viewModel.updateProfile(SingletonClass.getUserId(), name, email, phone, address)
        }

        viewModel.getProfileData().observe(this) {
            binding.edtNameProfile.setText(it.name)
            binding.edtEmailProfile.setText(it.email)
            binding.edtPhoneProfile.setText(it.phone)
            binding.edtAddressProfile.setText(it.address)
        }

        viewModel.getStatusUpdate().observe(this) {
            if (it) {
                Toast.makeText(
                    this,
                    getString(R.string.update_success),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                   this,
                    getString(R.string.update_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]
        viewModel.getProfile(this, SingletonClass.getUserId())
    }
}