package com.example.demo_test.image

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.demo_test.R
import com.example.demo_test.`interface`.CommonClickListener
import com.example.demo_test.base.BaseRvAdapter

/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */
class ImageViewAdapter (val listener: CommonClickListener):BaseRvAdapter<ImageBean>(R.layout.item_image_view){

    override fun convert(holder: BaseViewHolder, item: ImageBean) {
        super.convert(holder, item)
        Glide.with(context).load(item.url).fitCenter().into(holder.getView(R.id.iv_image))
        holder.itemView.setOnClickListener { listener.onClick() }
    }
}