package com.example.demo_test.video

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import com.example.demo_test.BaseVbActivity
import com.example.demo_test.databinding.ActivitySpecializedCoursesVideoPlayBinding
import com.example.demo_test.widget.exoPlayer.MyPlayBackControlView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.util.Util

/**
 * @Author: Nisikki
 * @Date: 2021/3/22
 * @Describe:
 */
class VideoPlayActivity : BaseVbActivity<ActivitySpecializedCoursesVideoPlayBinding>(), MyPlayBackControlView.VisibilityListener {
    lateinit var mainHandler: Handler
    lateinit var bandwidthMeter: DefaultBandwidthMeter
    lateinit var videoTrackSelectionFactory: AdaptiveVideoTrackSelection.Factory
    lateinit var trackSelector: DefaultTrackSelector
    lateinit var player: SimpleExoPlayer
    lateinit var dataSourceFactory: DataSource.Factory
    lateinit var extractorsFactory: DefaultExtractorsFactory
    override fun initView() {
        // step1. 创建一个默认的TrackSelector
        mainHandler = Handler();
        // 创建带宽
        bandwidthMeter = DefaultBandwidthMeter()
        // 创建轨道选择工厂
        videoTrackSelectionFactory = AdaptiveVideoTrackSelection.Factory(bandwidthMeter)
        // 创建轨道选择器实例
        trackSelector = DefaultTrackSelector(mainHandler, videoTrackSelectionFactory)
        //step2. 创建播放器
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, DefaultLoadControl(), null, false)

        var mp4VideoUri: Uri = Uri.parse("https://v-cdn.zjol.com.cn/276985.mp4")
        // 创建加载数据的工厂
        dataSourceFactory = buildDataSourceFactory(true)
        // 创建解析数据的工厂
        extractorsFactory = DefaultExtractorsFactory()


        binding.playerView.player = player
        player.playWhenReady = true
        // 传入Uri、加载数据的工厂、解析数据的工厂，就能创建出MediaSource
        var videoSource = ExtractorMediaSource(mp4VideoUri, dataSourceFactory, extractorsFactory, mainHandler, null)
        val mediaSource = buildMediaSource(mp4VideoUri, null)
        player.seekTo(0)
        player.prepare(mediaSource)

        binding.playerView.setControllerVisibilityListener(this)

    }

    var BANDWIDTH_METER = DefaultBandwidthMeter()

    fun buildDataSourceFactory(useBandwidthMeter: Boolean): DataSource.Factory {
        val bandwidthMeter: DefaultBandwidthMeter? = if (useBandwidthMeter) BANDWIDTH_METER else null
        return DefaultDataSourceFactory(getApplication(), bandwidthMeter, buildHttpDataSourceFactory(bandwidthMeter))
    }


    private fun buildHttpDataSourceFactory(bandwidthMeter: DefaultBandwidthMeter?): HttpDataSource.Factory {
        var userAgent = Util.getUserAgent(this, "PinealGland")
        return DefaultHttpDataSourceFactory(userAgent, bandwidthMeter)
    }

    private fun buildMediaSource(
        uri: Uri,
        overrideExtension: String?
    ): MediaSource? {
        val type = Util.inferContentType(if (!TextUtils.isEmpty(overrideExtension)) ".$overrideExtension" else uri.lastPathSegment)
        return when (type) {
            C.TYPE_SS ->
                SsMediaSource(uri, buildDataSourceFactory(false), DefaultSsChunkSource.Factory(dataSourceFactory), mainHandler, null)
            C.TYPE_DASH ->
                DashMediaSource(uri, buildDataSourceFactory(false), DefaultDashChunkSource.Factory(dataSourceFactory), mainHandler, null)
            C.TYPE_HLS ->
                HlsMediaSource(uri, dataSourceFactory, mainHandler, null)
            C.TYPE_OTHER ->
                ExtractorMediaSource(uri, dataSourceFactory, DefaultExtractorsFactory(), mainHandler, null)
            else -> {
                throw IllegalStateException("Unsupported type: $type")
            }
        }
    }
    override fun onVisibilityChange(visibility: Int) {
        if (Configuration.ORIENTATION_LANDSCAPE == resources.configuration.orientation) {
//            ivReward.setVisibility(visibility)
        } else {
//            ivReward.setVisibility(View.GONE)
        }
    }

    override fun onScreenOrientationChange(full: Boolean) {
        requestedOrientation = if (full) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }




}