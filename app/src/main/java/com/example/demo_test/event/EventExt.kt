package com.example.demo_test.event

import android.app.Activity
import org.greenrobot.eventbus.EventBus

/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */
class EventHelper {

//    companion object {
//        @Volatile
//        private var sinstant: EventHelper? = null
//        fun getInstance(): EventHelper {
//            return sinstant ?: synchronized(this) {
//                sinstant ?: EventHelper().also {
//                    sinstant = it
//                }
//            }
//        }
//    }

}

val eventBus: EventBus = EventBus.getDefault()

