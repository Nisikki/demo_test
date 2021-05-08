package com.example.demo_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.demo_test.event.EventMessageBean
import com.example.demo_test.event.eventBus
import com.example.demo_test.utils.inflater2Binding
import com.example.demo_test.utils.mLog
import com.example.demo_test.utils.mLogd
import com.example.demo_test.utils.mLogd2
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

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
        TAG.mLogd2("onCreate")
        binding = inflater2Binding(layoutInflater, null, 0)
        setContentView(binding.root)
        initView()
        eventBus.register(this)

    }


    abstract fun initView()


    override fun onStart() {
        super.onStart()
        TAG.mLogd2("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        TAG.mLogd2("onRestart")

    }

    override fun onResume() {
        super.onResume()
        TAG.mLogd2("onResume")
    }

    override fun onPause() {
        super.onPause()
        TAG.mLogd2("onPause")

    }


    override fun onStop() {
        super.onStop()
        TAG.mLogd2("onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        TAG.mLogd2("onDestroy")
        eventBus.unregister(this)

    }


    // TODO: 2021/5/8 这里可以再优化
    @Subscribe(threadMode = ThreadMode.MAIN)
   open fun onEvent(event:EventMessageBean<Any>){

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    open fun onEventSticky(event:EventMessageBean<Any>){

    }
}