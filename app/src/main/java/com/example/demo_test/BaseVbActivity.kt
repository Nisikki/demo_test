package com.example.demo_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.demo_test.utils.inflater2Binding
import com.example.demo_test.utils.mLog
import com.example.demo_test.utils.mLogd

/**
 * @Author: Nisikki
 * @Date: 2021/3/22
 * @Describe:
 */

abstract class BaseVbActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val TAG = javaClass.simpleName
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG.mLogd("onCreate")
        binding = inflater2Binding(layoutInflater, null, 0)
        setContentView(binding.root)

        initView()
    }

    abstract fun initView()


    override fun onStart() {
        super.onStart()
        TAG.mLogd("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        TAG.mLogd("onRestart")

    }

    override fun onResume() {
        super.onResume()
        TAG.mLogd("onResume")

    }

    override fun onPause() {
        super.onPause()
        TAG.mLogd("onPause")

    }


    override fun onStop() {
        super.onStop()
        TAG.mLogd("onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        TAG.mLogd("onDestroy")

    }

}