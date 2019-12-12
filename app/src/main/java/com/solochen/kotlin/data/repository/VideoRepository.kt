package com.solochen.kotlin.data.repository

import android.content.Context
import com.alibaba.fastjson.JSON
import com.solochen.kotlin.data.entity.VideoItem
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class VideoRepository {

    companion object {
        val instance: VideoRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            VideoRepository()
        }
    }

    fun getHotVideoList(context: Context?): MutableList<VideoItem> {
        val jsonData = getJson(context!!, "video_data.json")
        return JSON.parseArray(jsonData, VideoItem::class.java)
    }


    private fun getJson(
        context: Context,
        fileName: String
    ): String? {
        val stringBuilder = StringBuilder()
        try {
            val assetManager = context.assets
            val bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileName)
                )
            )
            var line: String? = ""
            while (bf.readLine().also({ line = it }) != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }


}