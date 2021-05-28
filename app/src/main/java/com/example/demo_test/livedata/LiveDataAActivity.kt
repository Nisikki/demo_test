package com.example.demo_test.livedata

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.demo_test.BaseVbActivity
import com.example.demo_test.databinding.ActivityTestBinding
import com.example.demo_test.event.EventConstant
import com.example.demo_test.event.EventMessageBean
import com.example.demo_test.utils.mLogd
import com.example.demo_test.utils.toActivity
import com.example.demo_test.utils.toast

/**
 * @Author: Nisikki
 * @Date: 2021/5/21
 * @Describe:
 */
class LiveDataAActivity : BaseVbActivity<ActivityTestBinding>() {


    val data = MutableLiveData<String>()
    override fun initView() {
        data.observe(this, Observer<String> {
            binding.tvContent.text = data.value
            mLogd("更新数据(observe)：" + data.value)
            data.value.toast
        })
        binding.tvContent.setOnClickListener {
            data.value = "A随机数"+Math.random()
        }
        binding.btn.setOnClickListener {
            toActivity(Intent(this,LiveDataBActivity::class.java))
        }
        data.observeForever {
            binding.tvContent.text = data.value
            mLogd("更新数据(observeForever)：" + data.value)
            data.value.toast

        }
    }

    override fun onEvent(event: EventMessageBean<Any>) {
        super.onEvent(event)
        if (event.key == EventConstant.EVENT_UPDATA_LIVEA){
            mLogd("接受到B穿过来的值：${event.data}")
            data.value = event.data as String
        }
    }
}