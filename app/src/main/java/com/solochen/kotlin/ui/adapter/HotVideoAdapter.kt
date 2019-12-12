package com.solochen.kotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solochen.kotlin.data.entity.VideoItem
import com.solochen.kotlin.databinding.ItemHotVideoBinding
import com.solochen.kotlin.ui.viewholder.HotPlayerViewHolder
import im.ene.toro.widget.PressablePlayerSelector

class HotVideoAdapter constructor(private val selector: PressablePlayerSelector, private var mList: MutableList<VideoItem>) :
    RecyclerView.Adapter<HotPlayerViewHolder>() {

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

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: HotPlayerViewHolder, position: Int) {
        holder.apply {
            bind(mList.get(position))
        }
    }

}