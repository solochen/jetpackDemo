package com.solochen.kotlin.data.entity

data class VideoItem(
    var dislike_count: Int,
    var like_count: Int,
    var title: String,
    var publish_time: Long,
    var avatar: String,
    var video_url: String
) {
    constructor() : this(0, 0, "", 0L, "", "")
}