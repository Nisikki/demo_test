package com.example.demo_test

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.example.demo_test.dir.DirActivity
import com.example.demo_test.skip.AActivity
import com.example.demo_test.utils.toActivity
import com.example.demo_test.video.VideoPlayActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var timer: CountDownTimer

    private val mStringNames = mapOf(
        "存储相关" to DirActivity::class.java,
        "Test" to TestActivity::class.java,
        "Route" to RouteACtivity::class.java,
        "KotlinTest" to KotlinTestActivity::class.java,
        "VideoPlay" to VideoPlayActivity::class.java,
        "AActivity" to AActivity::class.java
    )


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        ReceiveMsgService.startService(this)

        mStringNames.forEach {
            val button = Button(this)
            button.text = it.key
            button.textSize = 15f
            button.setPadding(10, 10, 10, 10)
            val layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(20, 10, 0, 0)
            button.layoutParams = layoutParams
            findViewById<LinearLayout>(R.id.linearLayout).addView(button)
            button.setOnClickListener { it1 ->
//                startActivity(Intent(this@MainActivity, it.value))
                toActivity(Intent(this, TransferTransparencyActivity::class.java))
            }
        }

    }


}