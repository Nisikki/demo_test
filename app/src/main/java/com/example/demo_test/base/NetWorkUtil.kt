package com.example.demo_test.base

import android.text.TextUtils
import java.util.*

/**
 * @Author: Nisikki
 * @Date: 2021/5/12
 * @Describe:

 */

object NetWorkUtil {
    val ENCRYPTION_KEY = "key"
    @JvmStatic
    fun getRequestParams(map: MutableMap<String, String> ): MutableMap<String, String> {
        if (!map.containsKey(K.Request.DEVICE)) map[K.Request.DEVICE] = K.Value.DEVICE
        // 增加一个时间戳参数
        if (!map.containsKey(K.Request.REQUEST_TIMESTAMP)) {
            map[K.Request.REQUEST_TIMESTAMP] = System.currentTimeMillis().toString() + ""
        }
        // 移除空的字段
        val stringSet: Set<String?> = HashSet(map.keys)
        for (s in stringSet) {
            if (TextUtils.isEmpty(map[s])) {
                map.remove(s)
            }
        }
        return getEncryptionParams(map)
    }

    /**
     * 获取Post方式加密参数
     */
    fun getEncryptionParams(params: MutableMap<String, String>): MutableMap<String, String> {
        params.remove(ENCRYPTION_KEY)
        val list = ArrayList<String>()
        for (s in params.keys) {
            list.add(s)
        }
        list.sort()
        var encryptionValue: String? = ""
        val size = list.size
        for (i in 0 until size) {
            val key = list[i]
            encryptionValue += params[key]
        }
//        val md5: String = SongguoNative.getEncryption(encryptionValue)
//        params[ENCRYPTION_KEY] = md5
        return params
    }
}