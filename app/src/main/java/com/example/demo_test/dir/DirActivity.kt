package com.example.demo_test.dir

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.demo_test.R
import com.example.demo_test.BaseActivity
import com.example.demo_test.dir.DirUtils

/**
 * @Author: Nisikki
 * @Date: 2021/3/8
 * @Describe:
 */
class DirActivity : BaseActivity() {


    override fun setLayout() = R.layout.activity_dir

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init() {
        findView<TextView>(R.id.tvName).text = DirUtils.getDirText()
    }
}