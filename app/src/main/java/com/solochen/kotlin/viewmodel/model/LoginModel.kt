package com.solochen.kotlin.viewmodel.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solochen.kotlin.data.entity.User
import com.solochen.kotlin.data.repository.UserRepository

class LoginModel constructor(private val repository: UserRepository) : ViewModel() {

    var account = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")

    fun login(): LiveData<User> {
        return repository.login(account.value!!, password.value!!)
    }

    fun onTextAccountChanged(s: CharSequence, start: Int, before: Int, count:Int){
        account.value = s.toString()
    }

    fun onTextPasswordChanged(s: CharSequence, start: Int, before: Int, count:Int){
        password.value = s.toString()
    }


}