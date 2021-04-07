package com.example.demo_test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: Nisikki
 * @Date: 2021/3/8
 * @Describe:
 */
abstract class BaseActivity : AppCompatActivity() {
    public val TAG = javaClass.simpleName;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        init()
    }


    abstract fun setLayout(): Int

    abstract fun init()

    fun <T : View> findView(id: Int): T {
        return findViewById(id)
    }




}