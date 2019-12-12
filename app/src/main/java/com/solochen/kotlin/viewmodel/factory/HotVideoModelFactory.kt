package com.solochen.kotlin.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solochen.kotlin.data.repository.VideoRepository
import com.solochen.kotlin.viewmodel.model.HotVideoModel

class HotVideoModelFactory(private val repository: VideoRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HotVideoModel(repository) as T
    }
}