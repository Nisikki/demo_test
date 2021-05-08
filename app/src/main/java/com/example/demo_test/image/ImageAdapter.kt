package com.example.demo_test.image

import android.media.Image
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.demo_test.R
import com.example.demo_test.`interface`.CommonClickListener
import com.example.demo_test.base.BaseRvAdapter
import com.example.demo_test.utils.UiUtils

/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */

class ImageAdapter(val imageWorker: ImageWorker,val listener:CommonClickListener) : BaseRvAdapter<ImageBean>(R.layout.item_image) {
    var width = 0

    init {
        width = UiUtils.getScreenWidth() / 4

    }

    override fun convert(holder: BaseViewHolder, item: ImageBean) {
        val iv = holder.getView(R.id.iv_image) as ImageView
        val lp = iv.layoutParams
        lp.height = width
        iv.layoutParams = lp
        if (ImageActivity.useMode == 0) {
            imageWorker.loadImage(item.id, iv)
        } else if (ImageActivity.useMode == 1) {
            Glide.with(context).load(item.url).centerCrop().into(iv)
        }else if ( ImageActivity.useMode == 2){
            Glide.with(context).load(item.uri).centerCrop().into(iv)
        }

        holder.itemView.setOnClickListener {
            listener.onClick(holder.adapterPosition)
        }

//
    }

}