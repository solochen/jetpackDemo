package com.solochen.kotlin.viewmodel

import android.content.Context
import com.solochen.kotlin.data.AppRepositoryProvider
import com.solochen.kotlin.data.repository.UserRepository
import com.solochen.kotlin.viewmodel.factory.HotVideoModelFactory
import com.solochen.kotlin.viewmodel.factory.LoginModelFactory
import com.solochen.kotlin.viewmodel.factory.RegisterModelFactory

/**
 * viewmodel 提供者
 */
object AppViewModelProvider {

    fun providerLoginModel(context: Context): LoginModelFactory {
        val repository: UserRepository = AppRepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(repository)
    }

    fun providerRegisterModel(context: Context): RegisterModelFactory {
        val repository: UserRepository = AppRepositoryProvider.providerUserRepository(context)
        return RegisterModelFactory(repository)
    }

    fun providerHotVideoModel(context: Context): HotVideoModelFactory {
        return HotVideoModelFactory(AppRepositoryProvider.providerVideoRepository(context))
    }
}