package com.example.demo_test.image

import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demo_test.BaseVbActivity
import com.example.demo_test.R
import com.example.demo_test.`interface`.CommonClickListener
import com.example.demo_test.event.EventConstant
import com.example.demo_test.databinding.ActImageBinding
import com.example.demo_test.event.EventMessageBean
import com.example.demo_test.event.eventBus
import com.example.demo_test.utils.mLogd
import com.example.demo_test.utils.toActivity
import kotlinx.coroutines.*


/**
 * @Author: Nisikki
 * @Date: 2021/5/7
 * @Describe: 注意！！！：Android Q 以上需要在androidMainfest里 添加：android:requestLegacyExternalStorage=true
 */


class ImageActivity : BaseVbActivity<ActImageBinding>(), CommonClickListener {
    lateinit var imageAdapter: ImageAdapter
    override fun initView() {

        binding.recycler.run {
            layoutManager = GridLayoutManager(this@ImageActivity, 4)
            val imageWorker = ImageWorker(this@ImageActivity)
            //这个bitmap是GridView中每一个item默认时的图片
            val b = Bitmap.createBitmap(intArrayOf(0x00000000), 1, 1, Bitmap.Config.ARGB_8888)
            imageWorker.setLoadBitmap(b);
            imageAdapter = ImageAdapter(imageWorker,this@ImageActivity)
            adapter = imageAdapter
        }

        lifecycleScope.launch {
            val data = withContext(Dispatchers.IO){
                searchImage()
            }
            imageAdapter.setNewData(data)
            imageAdapter.notifyDataSetChanged()
            eventBus.post(EventMessageBean(EventConstant.EVENT_IMAGE_COUNT, data.size))
        }

    }

    override fun onEvent(event: EventMessageBean<Any>) {
        super.onEvent(event)
        if (event.key == EventConstant.EVENT_IMAGE_COUNT) {
            binding.tvCount.text = "图片数量：${event.data}"

        }
    }

    companion object {
        const val useMode =1         //手动控制使用图片加载哪种方法 0:id（自定义） 1：url（Glide）  2:uri(Glide)
    }


   suspend fun searchImage() :ArrayList<ImageBean>{
        val ext_uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA
        )

       val selection = MediaStore.Images.Media.SIZE + ">=?"
       val selectionArgs =    arrayOf((1 * 1024).toString())
       val orderBy = MediaStore.Images.Media.DATE_ADDED + " desc"

       val cursor: Cursor = MediaStore.Images.Media.query(
            this.contentResolver,
            ext_uri,
            projection,
            null,          //筛选语句，传null不进行筛选
            null,       //筛选条件，配合第三个参数
            null            //配合第三个参数
        )
        val mediaID: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
        val mediaData: Int = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
        var i = 0

       val data = ArrayList<ImageBean>()

       while (cursor.moveToNext() && i < cursor.count) {   //移到指定的位置，遍历数据库
            val origId: Long = cursor.getLong(mediaID)
           val uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, origId.toString() + "")
           val url = cursor.getString(mediaData)
           data.add(ImageBean(origId,uri,url))
            cursor.moveToPosition(i)
            i++
        }

       return data
    }

    override fun onClick(vararg key: Any) {
        val index = key[0] as Int
        mLogd("当前索引：$index")
       val pair =  Pair<ArrayList<ImageBean>,Int>(imageAdapter.data as ArrayList<ImageBean>, index)
        toActivity(ImageViewActivity.getStartIntent(this))
        eventBus.postSticky(EventMessageBean(EventConstant.EVENT_IMAGE_DATA,pair))
    }

}




