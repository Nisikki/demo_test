package com.example.demo_test.skip

import android.app.Activity
import android.content.Intent
import com.example.demo_test.BaseVbActivity
import com.example.demo_test.databinding.ActivityABinding
import com.example.demo_test.databinding.ActivityBBinding
import com.example.demo_test.utils.toActivity
import com.google.android.exoplayer2.C

/**
 * @Author: Nisikki
 * @Date: 2021/3/25
 * @Describe:
 */
class BActivity : BaseVbActivity<ActivityBBinding>() {
    override fun initView() {
        binding.btn.setOnClickListener{
           toActivity(Intent(this, CActivity::class.java))
        }
    }
}