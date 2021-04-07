package com.example.demo_test.skip

import android.content.Intent
import com.example.demo_test.BaseVbActivity
import com.example.demo_test.TransferTransparencyActivity
import com.example.demo_test.databinding.ActivityABinding
import com.example.demo_test.utils.toActivity

/**
 * @Author: Nisikki
 * @Date: 2021/3/25
 * @Describe:
 */
class AActivity : BaseVbActivity<ActivityABinding>() {
    override fun initView() {
        toActivity(Intent(this, TransferTransparencyActivity::class.java))
    }


}