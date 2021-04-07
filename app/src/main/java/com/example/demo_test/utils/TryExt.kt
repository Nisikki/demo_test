package com.example.demo_test.utils

import android.util.Log

/**
 * @author Jowan
 * Created on 2020/6/30.
 */

inline fun tryCatch(block: () -> Unit): Unit = try {
    block()
} catch (e: Exception) {
    e.errorLog()
}

/**
 * 当需要返回值try的时候使用
 */
inline fun <T> tryCatch(default: T, block: () -> T): T {
    return try {
        block()
    } catch (e: Exception) {
        e.errorLog()
        default
    }
}

/**
 * 通用输出异常的方法
 */
inline fun Throwable.errorLog() {
    Throwable().stackTrace[1]?.let {
        Log.e(it.fileName, "${it.methodName} errorLog: " + this.message)
    }
}