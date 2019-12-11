package com.solochen.kotlin.viewmodel.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solochen.kotlin.data.DBCode
import com.solochen.kotlin.data.entity.User
import com.solochen.kotlin.data.repository.UserRepository
import kotlinx.coroutines.launch


class RegisterModel constructor(private val repository: UserRepository) : ViewModel() {


    var account = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")
    var passwordAgain = MutableLiveData<String>("")
    var registerId = MutableLiveData<Long>()
    val loginUser = MutableLiveData<User>()

    fun register() {

        viewModelScope.launch {
            val dbAccount = repository.findAccount(account.value!!)
            if (dbAccount != null) {
                registerId.value = DBCode.REGISTER_ERROR_EXIST
                return@launch
            }

            val user = User(
                account.value!!,
                password.value!!,
                "http://thirdwx.qlogo.cn/mmopen/vi_32/92lviccjPonkjiafPbP3R2fQezN7vxj04NgM8HzyOxuJpK5Kom2kFiapS84QPdwuw5mmJsVmBaH6uNBMDv4G44HcQ/132"
            )
            loginUser.value = user
            repository.register(user)
            registerId.value = DBCode.REGISTER_OK
        }
    }

    fun onTextAccountChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        account.value = s.toString()
    }

    fun onTextPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        password.value = s.toString()
    }

    fun onTextPasswordAgainChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        passwordAgain.value = s.toString()
    }

}