package com.mtg.speedtest.speedcheck.internet.booking.account_screen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mtg.speedtest.speedcheck.internet.booking.R
import com.mtg.speedtest.speedcheck.internet.booking.database.EndlessDatabase
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActLoginBinding
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActRegisterBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.FbUser
import com.mtg.speedtest.speedcheck.internet.booking.model.User
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserLoginRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.request.UserRegisterRequest

class RegisterAct : AppCompatActivity() {
    private lateinit var binding: ActRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initEvents()
    }

    private fun initEvents() {
        binding.tvRegister.setOnClickListener {
            createAccount()
        }

        binding.tvSignIn.setOnClickListener {
            finish()
        }

        viewModel.getUser().observe(this) {
            if (it != null) {
                finish()
            }
        }
    }

    private fun createAccount() {
        val firstNameUser = binding.edtFirstNameRegister.text.toString().trim()
        val address = binding.edtAddress.text.toString().trim()
        val phoneNumber = binding.editPhoneNumber.text.toString().trim()
        val emailUser = binding.edtEmailRegister.text.toString().trim()
        val password = binding.edtPasswordRegister.text.toString().trim()
        val confirmPassword = binding.edtConfirmPasswordRegister.text.toString().trim()

        if(firstNameUser.isEmpty() || address.isEmpty() || emailUser.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, resources.getString(R.string.need_to_fill), Toast.LENGTH_LONG).show()
        }else if (password != confirmPassword) {
            Toast.makeText(this, resources.getString(R.string.confirm_password), Toast.LENGTH_LONG).show()
        }else {
            val request = UserRegisterRequest(firstNameUser, address, phoneNumber, emailUser, password)
            viewModel.createAccount(this, request)
            finish()
        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }
}