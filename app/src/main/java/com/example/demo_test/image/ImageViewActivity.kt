package com.example.demo_test.image

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.demo_test.BaseVbActivity
import com.example.demo_test.`interface`.CommonClickListener
import com.example.demo_test.databinding.ActImageViewBinding
import com.example.demo_test.event.EventConstant
import com.example.demo_test.event.EventMessageBean
import com.example.demo_test.utils.mLogd
import com.example.demo_test.utils.thread.ThreadHelper

/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */
class ImageViewActivity : BaseVbActivity<ActImageViewBinding>(), CommonClickListener {

    lateinit var imageViewAdapter:ImageViewAdapter

    override fun initView() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        binding.viewPager.run {
            imageViewAdapter = ImageViewAdapter(this@ImageViewActivity)
            adapter = imageViewAdapter
        }


    }

    var index = 1
    lateinit var data: ArrayList<ImageBean>

    override fun onEventSticky(event: EventMessageBean<Any>) {
        super.onEventSticky(event)
        if (event.key == EventConstant.EVENT_IMAGE_DATA) {
            val pair = event.data as Pair<*, *>
            data = pair.first as ArrayList<ImageBean>
            index = pair.second as Int
            TAG.mLogd("当前索引：$index")

            imageViewAdapter.setNewData(data)
            binding.viewPager.setCurrentItem(index, false)
            imageViewAdapter.notifyDataSetChanged()

        }
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, ImageViewActivity::class.java)

    }

    override fun onClick(vararg key: Any) {
        finish()

    }
}

