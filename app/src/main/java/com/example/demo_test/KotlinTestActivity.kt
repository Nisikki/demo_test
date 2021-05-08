package com.example.demo_test

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.example.demo_test.utils.mLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation

/**
 * @Author: Nisikki
 * @Date: 2021/3/18
 * @Describe:
 */
class KotlinTestActivity : BaseActivity() {


    private val stateFlow = MutableStateFlow("")
    private lateinit var job: Job


    private val test :String = ""


    override fun setLayout() = R.layout.activity_test

    override fun init() {
//        test()

    }

    fun test1(){

    }

    fun test() {
        job = GlobalScope.launch() {
            stateFlow.debounce(2000)
                .collect {
                    mLog.e("result:$it")
                }
        }

//        val etInput = findView<EditText>(R.id.et_name)
//        etInput.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                stateFlow.value = s.toString().trim()
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })
    }
}