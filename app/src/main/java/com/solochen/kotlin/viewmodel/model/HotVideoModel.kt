package com.solochen.kotlin.viewmodel.model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.solochen.kotlin.data.repository.VideoRepository

class HotVideoModel constructor(private val repository: VideoRepository) : ViewModel() {

    fun getVideoData(context: Context) = repository.getHotVideoList(context)

}