package com.solochen.kotlin.viewmodel.factory

import androidx.paging.DataSource
import com.solochen.kotlin.data.entity.VideoItem
import com.solochen.kotlin.data.repository.VideoRepository
import com.solochen.kotlin.paging.HotVideoDataSource

class HotVideoDataSourceFactory(val repository: VideoRepository) :
    DataSource.Factory<Int, VideoItem>() {
    override fun create(): DataSource<Int, VideoItem> {
        return HotVideoDataSource(repository)
    }

}