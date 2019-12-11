package com.solochen.kotlin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.solochen.kotlin.data.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE account=:account and pwd = :password")
    fun login(account: String, password: String): LiveData<User>

    @Query("SELECT * FROM user WHERE account=:account")
    fun findUserByAccount(account: String): User

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun delUser(user: User)

}