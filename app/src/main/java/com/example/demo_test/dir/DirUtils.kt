package com.example.demo_test.dir

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.example.demo_test.MyApplication.Companion.context
import com.example.demo_test.utils.MyLog

/**
 * @Author: Nisikki
 * @Date: 2021/3/8
 * @Describe:
 */
@RequiresApi(Build.VERSION_CODES.N)
class DirUtils {

    companion object {
        val text1 = Environment.getDataDirectory().toString()
        val text2 = Environment.getDownloadCacheDirectory().toString()      // filepath:/data/cache
        val text3 = Environment.getRootDirectory().toString()               //filepath:/system
        val text4 = Environment.getExternalStorageDirectory().toString()    //filepath:/storage/emulated/0
        val text5 = context.cacheDir.toString()                             //   filepath:/data/user/0/com.example.androidfiletest/cache
        val text6 = context.dataDir.toString()                              //filepath:/data/user/0/com.example.androidfiletest
        val text7 = context.filesDir.toString()                             //filepath:/data/user/0/com.example.androidfiletest/files
        val text8 = context.obbDir.toString()                               //filepath:/storage/emulated/0/Android/obb/com.example.androidfiletest
        val text9 = context.noBackupFilesDir.toString()                     //filepath:/data/user/0/com.example.androidfiletest/no_backup
        val text10 = context.codeCacheDir.toString()                        //filepath:/data/user/0/com.example.androidfiletest/code_cache
        val text11 = context.externalCacheDir.toString()                    //filepath:/storage/emulated/0/Android/data/com.example.androidfiletest/cache
        val text12 = context.getExternalFilesDir(null).toString()      //filepath:/storage/emulated/0/Android/data/com.example.androidfiletest/files


        @JvmStatic
        @RequiresApi(Build.VERSION_CODES.N)
        fun logAllDir() {
            MyLog.e(text1)
            MyLog.e(text2)
            MyLog.e(text3)
            MyLog.e(text4)
            MyLog.e(text5)
            MyLog.e(text6)
            MyLog.e(text7)
            MyLog.e(text8)
            MyLog.e(text9)
            MyLog.e(text10)
            MyLog.e(text11)
            MyLog.e(text12)

        }

        @JvmStatic
        @RequiresApi(Build.VERSION_CODES.N)
        fun getDirText(): String {
            return "Environment.getDataDirectory():" + text1 + "\n" +
                    "Environment.getDownloadCacheDirectory():" + text2 + "\n" +
                    "Environment.getRootDirectory():" + text3 + "\n" +
                    "Environment.getExternalStorageDirectory():" + text4 + "\n" +
                    "context.cacheDir:" + text5 + "\n" +
                    "context.dataDir:" + text6 + "\n" +
                    "context.filesDir:" + text7 + "\n" +
                    "context.obbDir:" + text8 + "\n" +
                    "context.noBackupFilesDir:" + text9 + "\n" +
                    "context.codeCacheDir:" + text10 + "\n" +
                    "context.externalCacheDir:" + text11 + "\n" +
                    "context.getExternalFilesDir:" + text12
        }

    }
}