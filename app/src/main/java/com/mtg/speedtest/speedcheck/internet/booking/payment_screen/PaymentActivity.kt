package com.mtg.speedtest.speedcheck.internet.booking.payment_screen

import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mtg.speedtest.speedcheck.internet.booking.MainAct
import com.mtg.speedtest.speedcheck.internet.booking.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var paymentUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        binding.imvBack.setOnClickListener {
            val intent = Intent(this, MainAct::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

    }

    private fun initViews() {
        paymentUrl = intent.getStringExtra("key_payment_url").toString()

        val webSettings: WebSettings = binding.webview.settings
        webSettings.javaScriptEnabled = true

        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl(paymentUrl)
    }

    override fun onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}