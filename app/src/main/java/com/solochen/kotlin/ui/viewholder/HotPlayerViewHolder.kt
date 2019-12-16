package com.solochen.kotlin.ui.viewholder

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import com.solochen.kotlin.data.entity.VideoItem
import com.solochen.kotlin.databinding.ItemHotVideoBinding
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.exoplayer.ExoPlayerDispatcher
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.helper.ToroPlayerHelper
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container
import im.ene.toro.widget.PressablePlayerSelector

class HotPlayerViewHolder(
    private val binding: ItemHotVideoBinding,
    selector: PressablePlayerSelector?
) :
    RecyclerView.ViewHolder(binding.root),
    ToroPlayer {

    private var helper: ToroPlayerHelper? = null
    private var playerView: PlayerView? = null

    init {
        binding.playCoverIsShow = true
        playerView = binding.player
        if (selector != null) binding.player.setControlDispatcher(
            ExoPlayerDispatcher(
                selector,
                this
            )
        )
    }

    override fun getPlayerView(): View {
        return playerView!!
    }

    override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
        if (helper == null) {
            val uri = Uri.parse(binding?.video?.video_url)
            helper = ExoPlayerViewHelper(this, uri)
        }

        helper!!.addPlayerEventListener(videoEventListener)
        helper!!.initialize(container, playbackInfo)
    }

    override fun getCurrentPlaybackInfo(): PlaybackInfo {
        return if (helper != null) helper!!.latestPlaybackInfo else PlaybackInfo()
    }

    override fun play() {
        if (helper != null) helper!!.play()
    }

    override fun pause() {
        if (helper != null) helper!!.pause()
    }

    override fun isPlaying(): Boolean {
        return helper != null && helper!!.isPlaying
    }

    override fun release() {
        if (helper != null) {
            helper!!.release()
            helper = null
        }
    }

    override fun wantsToPlay(): Boolean {
        return ToroUtil.visibleAreaOffset(this, itemView.parent) >= 0.85
    }

    override fun getPlayerOrder(): Int {
        return adapterPosition
    }

    fun bind(item: VideoItem) {
        binding.apply {
            this.video = item
            this.listener = View.OnClickListener {
                when (it.id) {
                }
            }
            executePendingBindings()
        }
    }

    private val videoEventListener = object : ToroPlayer.EventListener {
        override fun onFirstFrameRendered() {
            binding.playCoverIsShow = false
        }

        override fun onBuffering() {
            binding.playCoverIsShow = true
        }

        override fun onPlaying() {
            binding.playCoverIsShow = false
        }

        override fun onPaused() {
            binding.playCoverIsShow = true
        }

        override fun onCompleted() {
            binding.playCoverIsShow = true
        }

    }
}