package com.solochen.kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.solochen.kotlin.R
import com.solochen.kotlin.data.cache.UserCache
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (UserCache.instance.isLogin()) {
            startActivity<MainActivity>()
            finish()
            return
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
