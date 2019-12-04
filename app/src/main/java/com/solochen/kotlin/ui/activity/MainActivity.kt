package com.solochen.kotlin.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solochen.kotlin.R


class MainActivity : AppCompatActivity() {


    companion object {
        @JvmStatic
        fun start(context: Context) =
            context.startActivity(Intent(context, MainActivity::class.java))

    }

    private lateinit var mBottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBottomNav = findViewById(R.id.nav_view)
        val hostNavFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        mBottomNav.setupWithNavController(hostNavFragment.navController)

    }


}
