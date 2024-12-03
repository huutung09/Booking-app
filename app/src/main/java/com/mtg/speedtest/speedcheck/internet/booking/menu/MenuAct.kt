package com.mtg.speedtest.speedcheck.internet.booking.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.keyBackMainScreenByLanguageScreen
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.linkPrivacy
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.linkShare
import com.mtg.speedtest.speedcheck.internet.booking.LanguageHelper
import com.mtg.speedtest.speedcheck.internet.booking.PreferManager
import com.mtg.speedtest.speedcheck.internet.booking.R
import java.util.*

class MenuAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_menu)

        initViews()
        initEvents()
    }

    private fun initEvents() {
        findViewById<ImageView>(R.id.imvBackMenu).setOnClickListener {
            finish()
        }

        findViewById<LinearLayout>(R.id.linearLayoutFeedback).setOnClickListener {
            val intentEmail = Intent(Intent.ACTION_SENDTO)
            intentEmail.data = Uri.parse("mailto:")
            intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(CommonUtils.nameEmail))
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, CommonUtils.titleEmail)
            startActivity(intentEmail)
        }

        findViewById<LinearLayout>(R.id.linearLayoutShare).setOnClickListener {
            val intentShare = Intent()
            intentShare.action = Intent.ACTION_SEND
            intentShare.putExtra(
                Intent.EXTRA_TEXT,
                linkShare + packageName
            )
            intentShare.type = "text/plain"
            startActivity(intentShare)
        }

        findViewById<LinearLayout>(R.id.linearLayoutPrivacy).setOnClickListener {
            val intentPrivacy = Intent(Intent.ACTION_VIEW, Uri.parse(linkPrivacy))
            startActivity(intentPrivacy)
        }

        findViewById<ImageView>(R.id.imvLanguageChinaMenu).setOnClickListener {
            LanguageHelper.setAppLanguage(this, Locale.CHINA.language)
            PreferManager.getInstance(this)
                ?.write(keyBackMainScreenByLanguageScreen, true)
            recreate()
        }

        findViewById<ImageView>(R.id.imvLanguageEnglishMenu).setOnClickListener {
            LanguageHelper.setAppLanguage(this, Locale.ENGLISH.language)
            PreferManager.getInstance(this)
                ?.write(keyBackMainScreenByLanguageScreen, true)
            recreate()
        }
    }

    private fun initViews() {

    }
}