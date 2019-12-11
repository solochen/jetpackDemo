package com.solochen.kotlin.data.cache

import com.alibaba.fastjson.JSON
import com.solochen.kotlin.base.App
import com.solochen.kotlin.data.entity.User
import com.solochen.kotlin.utils.JSONCheckUtil
import com.solochen.kotlin.utils.Prefs

class UserCache {

    init {
        getUser()
    }

    fun getUser(): User? {
        if (self != null) return self
        val userJson = Prefs.getInstance(App.context).getString(Prefs.Item.PREF_USER)
        if (JSONCheckUtil.isJson(userJson)) {
            val user = JSON.parseObject(userJson, User::class.java)
            if (user != null) {
                self = user
                return self
            }
            return null
        }
        return null
    }

    fun login(user: User) {
        self = user
        Prefs.getInstance(App.context).putString(Prefs.Item.PREF_USER, JSON.toJSONString(user))
    }

    fun isLogin(): Boolean = (self != null && self!!.account!!.isNotEmpty())

    fun logout() {
        self = null!!
        Prefs.getInstance(App.context).clearString(Prefs.Item.PREF_USER)
    }

    companion object {
        private var self: User? = null
        val instance: UserCache by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UserCache()
        }
    }


}