package com.example.demo_test.event

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */
object EventConstant {
    const val EVENT_IMAGE_COUNT  = "EVENT_IMAGE_COUNT"
    const val EVENT_IMAGE_DATA = "EVENT_IMAGE_DATA"
    const val EVENT_UPDATA_LIVEA = "EVENT_UPDATA_LIVEA"

}