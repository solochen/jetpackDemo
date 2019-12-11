package com.solochen.kotlin.base

import android.app.Application
import android.content.Context

open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
    }
}