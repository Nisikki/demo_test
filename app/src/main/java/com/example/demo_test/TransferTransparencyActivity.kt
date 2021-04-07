package com.example.demo_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: Nisikki
 * @Date: 2021/4/2
 * @Describe: 用于[com.app.pinealgland.ui.mine.account.view.FindAccountActivity.getStartIntent] 虚拟跳转，
 * 因为不想改动 [getStartIntnet]外层的调用方的代码，所以加了这个中转类，进来后直接退出
 */
class TransferTransparencyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("哈哈哈","高大大师傅")
        finish()
    }
}