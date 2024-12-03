package com.mtg.speedtest.speedcheck.internet.booking

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mtg.speedtest.speedcheck.internet.booking.account_screen.LoginAct

class SplashAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LanguageHelper.updateLanguage(this)
        setContentView(R.layout.act_splash)
        initView()
    }

    private fun initView() {
        Handler().postDelayed({
           val intent = Intent(this, LoginAct::class.java)
           startActivity(intent)
           finish()
        }, 5000)
    }
}