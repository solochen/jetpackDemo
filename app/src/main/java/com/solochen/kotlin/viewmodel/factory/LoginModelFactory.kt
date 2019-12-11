package com.solochen.kotlin.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solochen.kotlin.data.repository.UserRepository
import com.solochen.kotlin.viewmodel.model.LoginModel

class LoginModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginModel(repository) as T
    }
}