package com.mtg.speedtest.speedcheck.internet.booking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.keyBackMainScreenByLanguageScreen
import com.mtg.speedtest.speedcheck.internet.booking.bookmark_screen.BookmarkFragment
import com.mtg.speedtest.speedcheck.internet.booking.favorite_screen.FavoriteFragment
import com.mtg.speedtest.speedcheck.internet.booking.home_screen.HomeFragment
import com.mtg.speedtest.speedcheck.internet.booking.menu.MenuAct
import com.mtg.speedtest.speedcheck.internet.booking.profile_screen.ProfileFragment

class MainAct : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomBarHome -> {
                    setCurrentFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottomBarFavorite -> {
                    setCurrentFragment(FavoriteFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottomBarBookmark -> {
                    setCurrentFragment(BookmarkFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottomBarProfile -> {
                    setCurrentFragment(ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        LanguageHelper.updateLanguage(this)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        findViewById<ImageView>(R.id.imvMenuMain).setOnClickListener {
            startActivity(Intent(this, MenuAct::class.java))
        }
    }

    private fun initViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigation)

        bottomNavigationView.itemIconTintList = null // Disable auto draw color
        setCurrentFragment(HomeFragment())

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutContainer, fragment)
            addToBackStack(null)
            commit()
        }

    override fun onRestart() {
        super.onRestart()
        val value = PreferManager.getInstance(this)?.readBoolean(
            keyBackMainScreenByLanguageScreen, false
        )
        if (value == true) {
            PreferManager.getInstance(this)
                ?.write(keyBackMainScreenByLanguageScreen, false)
            startActivity(Intent(this, MainAct::class.java))
            finish()
        }
    }
}