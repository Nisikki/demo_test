package com.example.demo_test.utils

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.demo_test.MyApplication

/**
 * @Author: Nisikki
 * @Date: 2021/3/3
 * @Describe:
 */

val String?.toast: Unit
    get() {
        if (this.isNullOrEmpty()) {
            return
        }
        Toast.makeText(MyApplication.context, this, Toast.LENGTH_LONG).show()
    }


fun Fragment.toActivity(intent: Intent) {
    startActivity(intent)
}
fun Activity.toActivity(intent: Intent) {
    startActivity(intent)
}