package com.solochen.kotlin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "account") var account: String,
    @ColumnInfo(name = "pwd") var password: String,
    @ColumnInfo(name = "avatar") var avatar: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    constructor() : this("", "", "") {

    }

}