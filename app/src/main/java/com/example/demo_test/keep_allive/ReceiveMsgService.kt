package com.example.demo_test.keep_allive

import android.app.ActivityManager
import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log

/**
 * @Author: Nisikki
 * @Date: 2021/3/5
 * @Describe:
 */
class ReceiveMsgService : Service() {
    private lateinit var mContext :Context

    companion object {
        const val TAG = "ReceiveMsgService"
        const val GRAY_SERVICE_ID = 1001
        const val ALIVE_ID = "sg_alive"

        @JvmStatic
        fun startService(context: Context) {
            if (isServiceRunning(context, ReceiveMsgService::class.java)) {
                return
            }
            try {
                /**
                 * 这里原逻辑上，在API 大于等 26 后需要用startForegroundService启动service
                 * 但是在判断条件里还加了一个 && !DeviceUtils.isSupportOfflineMsg()
                 * 这样导致在国产系统内是不会使用 startForegroundService启动service
                 * 明显导致API26后的service启动异常，经追踪代码也没发现该逻辑的作用之处
                 * 所以先把这个逻辑判断给去掉。
                 * update by shaojunji on 2019.07.29
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.i(TAG, "startForegroundService: time={" + System.currentTimeMillis() + "}")
                    context.startForegroundService(getStartIntent(context))
                } else {
                    context.startService(getStartIntent(context))
                }
            } catch (se: SecurityException) {
                se.printStackTrace()
            }
        }

        fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (service in manager.getRunningServices(Int.MAX_VALUE)) {
                if (serviceClass.name == service.service.className) {
                    return true
                }
            }
            return false
        }


        private fun getStartIntent(context: Context): Intent? {
            return Intent(context, ReceiveMsgService::class.java)
        }



    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onCreate() {
        super.onCreate()
        mContext =  this
        startNotify(mContext)
    }


    fun startNotify(context: Context) {
        Log.i(TAG, "startNotify: time={" + System.currentTimeMillis() + "}")
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForeground(GRAY_SERVICE_ID, NotificationUtils.createNotificationByChannel(this))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                startForeground(GRAY_SERVICE_ID, NotificationUtils.buildNotification(this))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                val innerIntent = Intent(context, GrayInnerService::class.java)
                startService(innerIntent)
                startForeground(GRAY_SERVICE_ID, Notification())
            } else {
                startForeground(GRAY_SERVICE_ID, Notification())
            }
        } catch (e: Exception) {
            Log.d("error", "启动错误")
        }
    }


}


class GrayInnerService : Service() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startForeground(ReceiveMsgService.GRAY_SERVICE_ID, Notification())
        stopForeground(true)
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
