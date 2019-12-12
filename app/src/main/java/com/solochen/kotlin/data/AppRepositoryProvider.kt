package com.solochen.kotlin.data

import android.content.Context
import com.solochen.kotlin.data.repository.UserRepository
import com.solochen.kotlin.data.repository.VideoRepository


/**
 * 数据提供者，可以是网络数据、DB数据等
 */
object AppRepositoryProvider {

    fun providerUserRepository(context: Context): UserRepository {
        val userDao = AppDatabase.getInstance(context).userDao()
        return UserRepository.getInstance(userDao)
    }

    fun providerVideoRepository(context: Context): VideoRepository {
        return VideoRepository.instance
    }
}