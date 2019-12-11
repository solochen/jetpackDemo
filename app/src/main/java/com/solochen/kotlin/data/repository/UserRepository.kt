package com.solochen.kotlin.data.repository

import androidx.lifecycle.LiveData
import com.solochen.kotlin.data.dao.UserDao
import com.solochen.kotlin.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository private constructor(private val userDao: UserDao) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also {
                    instance = it
                }
            }
    }

    fun login(account: String, pwd: String): LiveData<User> = userDao.login(account, pwd)

    suspend fun register(user: User): Long {
        return withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

   suspend fun findAccount(account: String): User {
        return withContext(Dispatchers.IO) {
            userDao.findUserByAccount(account)
        }
    }

    suspend fun updateUser(user: User) {
        //切换到IO线程执行
        withContext(Dispatchers.IO) {
            userDao.updateUser(user)
        }
    }

    suspend fun delUser(user: User) {
        //切换到IO线程执行
        withContext(Dispatchers.IO) {
            userDao.delUser(user)
        }
    }
}