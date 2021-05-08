package com.example.demo_test.base

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @Author: Nisikki
 * @Date: 2021/5/7
 * @Describe:
 */

// TODO: 2021/5/8 优化成ViewBinding
open class BaseRvAdapter <T>(@LayoutRes layoutResId:Int) :BaseQuickAdapter<T,BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: T) {

    }

}