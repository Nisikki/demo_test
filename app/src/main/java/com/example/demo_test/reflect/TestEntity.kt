package com.example.demo_test.reflect

import com.example.demo_test.utils.MyLog
import com.example.demo_test.utils.mLog

/**
 * @Author: Nisikki
 * @Date: 2021/2/5
 * @Describe:
 */
data class TestEntity constructor(private val a: Int, private var b: String) {
    init {
        mLog.e("$b $a")
    }

    private fun test1(c :String){
        mLog.e("测试获取私有方法")
    }
}