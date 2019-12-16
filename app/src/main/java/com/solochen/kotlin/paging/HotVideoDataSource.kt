package com.solochen.kotlin.paging

import androidx.paging.PageKeyedDataSource
import com.solochen.kotlin.data.entity.VideoItem
import com.solochen.kotlin.data.repository.VideoRepository

class HotVideoDataSource(val repository: VideoRepository) : PageKeyedDataSource<Int, VideoItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, VideoItem>
    ) {
        //params.requestedLoadSize 为每页加载的数量
        val data = repository.loadFirstPageData(params.requestedLoadSize)

        //第二个参数为加载上一页的页数，这里为null不会去加载，所以loadBefore方法不会被调用
        //第二个参数为加载下一页的页数，这里为2，因为loadinitial方法时页数已经是1了。
        callback.onResult(data, null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, VideoItem>) {
        //params.key 为DataSource中的key，这里指第几页（页数）
        //params.requestedLoadSize 为每页加载的数量
        val data = repository.loadPageData(params.key, params.requestedLoadSize)
        data?.let {
            callback.onResult(data, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, VideoItem>) {
        //params.key 为DataSource中的key，这里指第几页（页数）
        //params.requestedLoadSize 为每页加载的数量
        val data = repository.loadPageData(params.key, params.requestedLoadSize)
        data?.let {
            callback.onResult(data, params.key - 1)
        }
    }

}