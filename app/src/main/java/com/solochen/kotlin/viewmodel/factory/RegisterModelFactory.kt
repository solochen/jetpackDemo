package com.solochen.kotlin.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solochen.kotlin.data.repository.UserRepository
import com.solochen.kotlin.viewmodel.model.RegisterModel

class RegisterModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterModel(repository) as T
    }
}