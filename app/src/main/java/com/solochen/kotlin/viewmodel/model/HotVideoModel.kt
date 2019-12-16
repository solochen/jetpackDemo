package com.solochen.kotlin.viewmodel.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.solochen.kotlin.data.entity.VideoItem
import com.solochen.kotlin.data.repository.VideoRepository
import com.solochen.kotlin.viewmodel.factory.HotVideoDataSourceFactory

class HotVideoModel constructor(private val repository: VideoRepository) : ViewModel() {


     fun getRefreshLiveData(): LiveData<PagedList<VideoItem>>{
        return LivePagedListBuilder(
            HotVideoDataSourceFactory(repository), PagedList.Config.Builder()
                .setPageSize(5)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(5)
                .build()).build()
    }

}