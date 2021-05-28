package com.example.demo_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.demo_test.base.BaseViewModel
import com.example.demo_test.event.EventMessageBean
import com.example.demo_test.event.eventBus
import com.example.demo_test.utils.inflater2Binding
import com.example.demo_test.utils.mLogd2
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @Author: Nisikki
 * @Date: 2021/3/22
 * @Describe:
 */



abstract class BaseVbVmActivity<VB : ViewBinding,VM:BaseViewModel> : BaseVbActivity<VB>() {


}