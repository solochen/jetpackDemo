package com.solochen.kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class Prefs {

    enum class Item(val key: String, val defaultValue: String) {
        PREF_USER("pref_user", ""), //用户信息
    }

    fun putString(item: Item, value: String?) {
        val editor = sharedPreferences!!.edit()
        editor.putString(item.key, value)
        SharedPreferencesCompat.apply(editor)
    }

    fun getString(item: Item): String {
        return sharedPreferences!!.getString(item.key, item.defaultValue)
    }

    fun putInt(item: Item, value: Int) {
        val editor = sharedPreferences!!.edit()
        editor.putInt(item.key, value)
        SharedPreferencesCompat.apply(editor)
    }

    fun getInt(item: Item): Int {
        return sharedPreferences!!.getInt(
            item.key,
            Integer.valueOf(item.defaultValue)
        )
    }

    fun putLong(item: Item, value: Long) {
        val editor = sharedPreferences!!.edit()
        editor.putLong(item.key, value)
        SharedPreferencesCompat.apply(editor)
    }

    fun getLong(item: Item): Long{
        return sharedPreferences!!.getLong(item.key, item.defaultValue.toLong())
    }

    fun putBoolean(item: Item, value: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(item.key, value)
        SharedPreferencesCompat.apply(editor)
    }

    fun getBoolean(item: Item): Boolean {
        return if (TextUtils.isEmpty(item.defaultValue)) {
            sharedPreferences!!.getBoolean(item.key, false)
        } else sharedPreferences!!.getBoolean(
            item.key,
            java.lang.Boolean.valueOf(item.defaultValue)
        )
    }

    fun clearString(item: Item) {
        putString(item, "")
    }

    fun clearAll() {
        val editor = sharedPreferences!!.edit()
        editor.clear()
    }

    private object SharedPreferencesCompat {
        private val sApplyMethod =
            findApplyMethod()

        private fun findApplyMethod(): Method? {
            try {
                val clz: Class<*> = SharedPreferences.Editor::class.java
                return clz.getMethod("apply")
            } catch (e: NoSuchMethodException) {
            }
            return null
        }

        fun apply(editor: SharedPreferences.Editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor)
                    return
                }
            } catch (e: IllegalArgumentException) {
            } catch (e: IllegalAccessException) {
            } catch (e: InvocationTargetException) {
            }
            editor.commit()
        }
    }

    companion object {
        private var instance: Prefs? = null
        private var sharedPreferences: SharedPreferences? = null

        fun getInstance(context: Context): Prefs {
            return instance ?: synchronized(this) {
                instance ?: init(context).also { instance = it }
            }
        }

        private fun init(context: Context): Prefs {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return Prefs()
        }
    }
}