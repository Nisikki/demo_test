package com.example.demo_test.keep_allive

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.demo_test.R
import com.example.demo_test.keep_allive.ReceiveMsgService

/**
 * @Author: Nisikki
 * @Date: 2021/3/5
 * @Describe:
 */
object NotificationUtils{

    @JvmStatic
     fun buildNotification(context: Context): Notification {
        val builder = Notification.Builder(context)
        // 直接打开app
        val launchIntentForPackage: Intent? = context.packageManager.getLaunchIntentForPackage(context.packageName)
        val pIntent = PendingIntent.getActivity(context, 0, launchIntentForPackage, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setTicker("TestDemo")
        builder.setContentTitle("TestDemo")
        builder.setContentText("TestDemo" + "正在运行, 确保您及时接收消息提醒")
        builder.setContentIntent(pIntent)
        builder.setOngoing(true)

        return builder.build()
    }

    @JvmStatic
    @TargetApi(Build.VERSION_CODES.O)
    fun createNotificationByChannel(context: Context): Notification? {
        val launchIntentForPackage = context.packageManager.getLaunchIntentForPackage(context.packageName)
        val pIntent = PendingIntent.getActivity(context, 0, launchIntentForPackage, PendingIntent.FLAG_UPDATE_CURRENT)
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 用户可以看到的通知渠道的名字.
        val name: CharSequence = context.getString(R.string.app_name)
        // 用户可以看到的通知渠道的描述
        val description = context.getString(R.string.app_name) + "正在运行, 确保您及时接收消息提醒"
        // 在notificationManager中创建该通知渠道
        if (mNotificationManager != null) {
            // 仅需要常驻通知栏，所以级别是low
            val channel: NotificationChannel =
                createLowNotificationChannel(ReceiveMsgService.ALIVE_ID, "运行服务", description)
            mNotificationManager.createNotificationChannel(channel)
        }
        return Notification.Builder(context)
            .setContentTitle(name)
            .setTicker(name)
            .setContentText(description)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setChannelId(ReceiveMsgService.ALIVE_ID)
            .setContentIntent(pIntent)
            .setOngoing(true)
            .build()
    }

    @JvmStatic
    @TargetApi(Build.VERSION_CODES.O)
    fun createLowNotificationChannel(id: String?, name: String?, description: String?): NotificationChannel {
        val channel: NotificationChannel =
            createNotificationChannel(id, name, description, NotificationManager.IMPORTANCE_LOW)
        // 不发出系统提示音，本身收到消息会有声音
        channel.setSound(null, null)
        // 不显示角标
        channel.setShowBadge(false)
        // 不提示灯
        channel.enableLights(false)
        // 不震动
        channel.enableVibration(false)
        return channel
    }

    /**
     * 创建渠道
     *
     * @param id          渠道id
     * @param name        渠道名
     * @param description 渠道属性
     * @param importance  通知级别
     * @return NotificationChannel
     */
    @JvmStatic
    @TargetApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(id: String?, name: String?, description: String?, importance: Int): NotificationChannel {
        val mChannel = NotificationChannel(id, name, importance)
        mChannel.description = description
        return mChannel
    }
}