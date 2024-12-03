package com.mtg.speedtest.speedcheck.internet.booking.account_screen

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.auth
import com.mtg.speedtest.speedcheck.internet.booking.*
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActLoginBinding
import com.mtg.speedtest.speedcheck.internet.booking.model.User

class LoginAct : AppCompatActivity() {
    private lateinit var binding: ActLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var userList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LanguageHelper.updateLanguage(this)
        binding = ActLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        findViewById<TextView>(R.id.tvLogin).setOnClickListener {
            openMainAct()
        }

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterAct::class.java))
        }

//        viewModel.listUsers.observe(this) {
//            userList = it
//        }
        viewModel.getUserTokenMutableLiveData().observe(this) {
            if (it != null) {
                startActivity(Intent(this, MainAct::class.java))
            }
        }
    }

    private fun initViews() {
        userList = mutableListOf()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.getListHotTrend()
        viewModel.getListProvince()
    }

    private fun openMainAct() {
        val email = binding.edtEmailLogin.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, resources.getString(R.string.need_to_fill), Toast.LENGTH_LONG)
                .show()
        } else {
           viewModel.login(this, email, password)
        }
    }

    override fun onResume() {
        super.onResume()
//        viewModel.getListUser(this)
    }

}