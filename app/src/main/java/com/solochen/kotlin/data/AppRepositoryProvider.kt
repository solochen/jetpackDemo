package com.solochen.kotlin.data

import android.content.Context
import com.solochen.kotlin.data.repository.UserRepository


/**
 * 数据提供者，可以是网络数据、DB数据等
 */
object AppRepositoryProvider {

    fun providerUserRepository(context: Context): UserRepository {
        val userDao = AppDatabase.getInstance(context).userDao()
        return UserRepository.getInstance(userDao)
    }
}