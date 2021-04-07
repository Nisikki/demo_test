package com.example.demo_test.skip

import com.example.demo_test.BaseVbActivity
import com.example.demo_test.databinding.ActivityABinding
import com.example.demo_test.databinding.ActivityCBinding

/**
 * @Author: Nisikki
 * @Date: 2021/3/25
 * @Describe:
 */
class CActivity : BaseVbActivity<ActivityCBinding>() {
    override fun initView() {
        finish()
        return
    }
}