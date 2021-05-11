package com.example.demo_test.utils

import android.content.Context
import android.util.Log
import com.example.demo_test.utils.MyLog.isShow

/**
 * @Author: Nisikki
 * @Date: 2021/2/5
 * @Describe:
 */
object MyLog {
    const val TAG = "TestDemo"

    const val isShow = true         //控制是否显示LOg

    @JvmStatic
    fun e(message: String) {
        Log.e(TAG, message)
    }

    @JvmStatic
    fun d(tag: String, message: String) {
        Log.e(tag, message)
    }

    @JvmStatic
    fun d(tag: String, message: String, isShow: Boolean) {
        if (isShow)
            Log.e(tag, message)
    }


}


fun Any.mLogd(msg: String) {
    mLog.d(javaClass.simpleName, msg)
}

fun Any.mLogd2(msg: String) {
    mLog.d(javaClass.simpleName, msg, isShow)
}

