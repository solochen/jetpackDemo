package com.solochen.kotlin.data.repository

import android.content.Context
import com.alibaba.fastjson.JSON
import com.solochen.kotlin.base.App
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

    private var videoData = mutableListOf<VideoItem>()

    init {
        val jsonData = getJson(App.context, "video_data.json")
        videoData = JSON.parseArray(jsonData, VideoItem::class.java)

        for (i in 1..5) {
            videoData.shuffle()
            videoData.addAll(videoData)
        }

    }

    fun loadFirstPageData(pageSize: Int): MutableList<VideoItem> {
        return videoData.subList(0, pageSize)
    }

    fun loadPageData(page: Int, pageSize: Int): MutableList<VideoItem> {
        val totalPage = if (videoData.size % pageSize == 0) {
            videoData.size / pageSize
        } else {
            videoData.size / pageSize + 1
        }

        if (page > totalPage || page < 1) {
            return null!!
        }

        if (page == totalPage) {
            return videoData.subList((page - 1) * pageSize, videoData.size)
        }
        return videoData.subList((page - 1) * pageSize, page * pageSize)
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