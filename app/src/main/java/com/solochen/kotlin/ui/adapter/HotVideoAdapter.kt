package com.solochen.kotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.solochen.kotlin.data.entity.VideoItem
import com.solochen.kotlin.databinding.ItemHotVideoBinding
import com.solochen.kotlin.ui.viewholder.HotPlayerViewHolder
import im.ene.toro.widget.PressablePlayerSelector

class HotVideoAdapter constructor(private val selector: PressablePlayerSelector) :
    PagedListAdapter<VideoItem, HotPlayerViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<VideoItem>() {
            override fun areItemsTheSame(oldConcert: VideoItem, newConcert: VideoItem): Boolean =
                oldConcert.avatar == newConcert.avatar

            override fun areContentsTheSame(oldConcert: VideoItem, newConcert: VideoItem): Boolean =
                oldConcert == newConcert
        }
    }

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotPlayerViewHolder {
        context = parent.context
        val viewHolder = HotPlayerViewHolder(
            ItemHotVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            selector
        )

        if (selector != null) viewHolder.itemView.setOnLongClickListener(selector)

        return viewHolder
    }


    override fun onBindViewHolder(holder: HotPlayerViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position)!!)
        }
    }

}