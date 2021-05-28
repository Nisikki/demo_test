@file:Suppress("unused")

package com.example.demo_test.network

import android.Manifest
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.demo_test.utils.errorLog
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Call
import java.security.Permissions

/**
 * @author Jowan
 * Created on 2019/6/24.
 */

fun Job?.tryCancel() {
    this ?: return
    try {
        cancel()
    } catch (e: Throwable) {
        e.errorLog()
    }
}

fun LifecycleOwner.launch(block: suspend () -> Unit): Job {
    return lifecycleScope.launch { block() }
}

fun LifecycleOwner.launchUI(block: suspend () -> Unit): Job {
    return lifecycleScope.launch(Dispatchers.Main) { block() }
}

fun LifecycleOwner.launchIO(block: suspend () -> Unit): Job {
    return lifecycleScope.launch(Dispatchers.IO) { block() }
}

fun ViewModel.launch(block: suspend () -> Unit): Job {
    return viewModelScope.launch { block() }
}

fun ViewModel.launchUI(block: suspend () -> Unit): Job {
    return viewModelScope.launch(Dispatchers.Main) { block() }
}

fun ViewModel.launchIO(block: suspend () -> Unit): Job {
    return viewModelScope.launch(Dispatchers.IO) { block() }
}

/**
 * io线程
 * 不使用全局的GlobalScope.launch，不然会取消不了job
 */
fun CoroutineScope.launchIO(block: suspend () -> Unit): Job {
    return this.launch(Dispatchers.IO) { block() }
}

/**
 * 主线程
 * 不使用全局的GlobalScope.launch，不然会取消不了job
 */
fun CoroutineScope.launchUI(block: suspend () -> Unit): Job {
    return this.launch(Dispatchers.Main) { block() }
}

/**
 * 获取视频的某一帧的作为封面图
 * <https://blog.csdn.net/qq_38356174/article/details/102545804>
 * @param url 视频链接或文件
 * @param frame 第几帧
 */
suspend fun getVideoCoverImg(url: String, frame: Int = 1): Bitmap? {
    return suspendCancellableCoroutine { continuation ->
        runCatching {
            val retriever = MediaMetadataRetriever()
            //文件与链接调用的方法不一致，注意区分
            if (url.startsWith("http")) {
                //setDataSource(String uri,  Map<String, String> headers) 是获取视频链接
                retriever.setDataSource(url, emptyMap())
            } else {
                //setDataSource(String path)是获取视频文件
                retriever.setDataSource(url)
            }
            retriever.setDataSource(url, HashMap())
            //获得第1帧图片 这里的第一个参数 以微秒为单位
            val bitmap = retriever.getFrameAtTime((frame * 1000000).toLong(), MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
            retriever.release()
            continuation.resumeWith(kotlin.Result.success(bitmap))
        }.onFailure {
            continuation.resumeWith(kotlin.Result.success(null))
        }
    }
}

