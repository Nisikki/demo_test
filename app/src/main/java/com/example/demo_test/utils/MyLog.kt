package com.example.demo_test.utils

import android.util.Log

/**
 * @Author: Nisikki
 * @Date: 2021/2/5
 * @Describe:
 */
object MyLog {
    const val TAG = "TestDemo"
    @JvmStatic
    fun e(message: String) {
        Log.e(TAG, message)
    }
}