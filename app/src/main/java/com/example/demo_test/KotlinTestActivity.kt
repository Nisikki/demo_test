package com.example.demo_test

import androidx.lifecycle.lifecycleScope
import com.example.demo_test.network.BaseResponse
import com.example.demo_test.network.BasenNetWork
import com.example.demo_test.utils.mLog
import com.example.demo_test.utils.mLogd
import com.example.demo_test.utils.tryCatch
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce


/**
 * @Author: Nisikki
 * @Date: 2021/3/18
 * @Describe:
 */
class KotlinTestActivity : BaseActivity() {


    private val stateFlow = MutableStateFlow("")
    private lateinit var job: Job


    private val test: String = ""


    override fun setLayout() = R.layout.activity_test

    override fun init() {
        getList()

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


    fun getList() {
        lifecycleScope.launch {
            tryCatch {

                val data = withContext(Dispatchers.IO) {
                    BasenNetWork.getInstance().apiRequest.getBannerByKotlin()
                }
                mLogd(data.toString())
            }

        }



    }

    fun rxjava(){
        BasenNetWork.getInstance().apiRequest.getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseResponse<String>> {
                override fun onNext(t: BaseResponse<String>) {
                    mLogd("onNext$t")
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    mLogd("onError${e.message}")

                }
            })
    }

}