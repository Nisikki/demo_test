package com.example.demo_test

import android.app.Application
import android.content.Context

/**
 * @Author: Nisikki
 * @Date: 2021/3/8
 * @Describe:
 */
class MyApplication :Application(){
   companion object  {

        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }
}


