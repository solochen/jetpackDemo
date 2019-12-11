package com.solochen.kotlin.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

object JSONCheckUtil {

    fun isJson(string: String): Boolean {
        val json = JSON.parse(string)
        if (json is JSONObject || json is JSONArray) {
            return true
        }
        return false
    }
}