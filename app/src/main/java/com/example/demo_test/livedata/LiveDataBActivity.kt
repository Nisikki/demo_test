package com.example.demo_test.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.demo_test.BaseVbActivity
import com.example.demo_test.databinding.ActivityTestBinding
import com.example.demo_test.event.EventConstant
import com.example.demo_test.event.EventMessageBean
import com.example.demo_test.event.eventBus
import com.example.demo_test.utils.mLogd

/**
 * @Author: Nisikki
 * @Date: 2021/5/21
 * @Describe:
 */
class LiveDataBActivity : BaseVbActivity<ActivityTestBinding>() {


    val data = MutableLiveData<String>()
    override fun initView() {
        data.observe(this, Observer<String> {
            binding.tvContent.text = data.value
            mLogd("更新数据：" + data.value)
        })
        binding.tvContent.setOnClickListener {
            data.value = "B随机数"+Math.random()
        }

        binding.btn.setOnClickListener {
            eventBus.post(EventMessageBean(EventConstant.EVENT_UPDATA_LIVEA,"B送过来的随机数${Math.random()}"))
        }
    }


}