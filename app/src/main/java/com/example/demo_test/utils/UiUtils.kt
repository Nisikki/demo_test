package com.example.demo_test.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.example.demo_test.MyApplication

/**
 * @Author: Nisikki
 * @Date: 2021/5/7
 * @Describe:
 */
object UiUtils {

    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    fun getScreenWidth(): Int {
        return getScreenWidth(MyApplication.context)
    }

}