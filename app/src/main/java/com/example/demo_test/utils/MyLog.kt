package com.example.demo_test.utils

import android.util.Log
import com.example.demo_test.utils.MyLog.isShow

/**
 * @Author: Nisikki
 * @Date: 2021/2/5
 * @Describe:
 */
object MyLog {
    const val TAG = "TestDemo"

    const val isShow = false;
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


fun String.mLogd(msg: String) {
    mLog.d(this, msg)
}

fun String.mLogd2(msg: String) {
    mLog.d(this, msg,isShow)
}