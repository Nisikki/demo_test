package com.example.demo_test.dir

import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getExternalFilesDirs
import com.example.demo_test.MyApplication
import com.example.demo_test.MyApplication.Companion.context
import com.example.demo_test.utils.mLog
import com.example.demo_test.utils.mLogd
import java.io.File


/**
 * @Author: Nisikki
 * @Date: 2021/3/8
 * @Describe:
 */
@RequiresApi(Build.VERSION_CODES.N)
class DirUtils {

    fun getExternalDirs() {
        val files: Array<File>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            files = getExternalFilesDirs(MyApplication.context, Environment.MEDIA_MOUNTED)
            for (file in files) {
                mLog.e("目录：${file.absolutePath}")
            }
        }
    }


    /**
     *
     * 参考 https://blog.csdn.net/u010937230/article/details/73303034/?ops_request_misc=&request_id=&biz_id=102&utm_term=Android%20%E6%96%87%E4%BB%B6%E5%AD%98%E5%82%A8%E4%B8%AA%E7%89%88%E6%9C%AC&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-0-73303034.pc_v1_rank_blog_v1
     * 未证实
     * 4.4之前：而外部存储 相当于 SD卡存储
     * 4.4及其之后：外部存储有两部分：机身自带存储和SD卡存储
     * 在4.4以后的系统中，API提供了一个方法 getExternalDirs() 来遍历手机的外部存储路径
     * 如荣耀7(6.0)打印为
     * /storage/emulated/0/Android/data/packname/files/mounted  机身存储路径
     * /storage/B3E4-1711/Android/data/packname/files/mounted    SD卡存储路径
     *
     * 访问外部存储的API方法：
     * 1、Environment.getExternalStorageDirectory().getAbsolutePath()
     * 2、Environment.getExternalStoragePublicDirectory(“”).getAbsolutePath()
     * 3、getExternalFilesDir(“”).getAbsolutePath()
     * 4、getExternalCacheDir().getAbsolutePath()


     * 1、Environment.getDataDirectory() = /data
     * 这个方法是获取内部存储的根路径
     * 2、getFilesDir().getAbsolutePath() = /data/user/0/packname/files
     * 这个方法是获取某个应用在内部存储中的files路径
     * 3、getCacheDir().getAbsolutePath() = /data/user/0/packname/cache
     * 这个方法是获取某个应用在内部存储中的cache路径
     * 4、getDir(“myFile”, MODE_PRIVATE).getAbsolutePath() = /data/user/0/packname/app_myFile
     * 这个方法是获取某个应用在内部存储中的自定义路径
     * 方法2,3,4的路径中都带有包名，说明他们是属于某个应用
     * …………………………………………………………………………………………
     * 5、Environment.getExternalStorageDirectory().getAbsolutePath() = /storage/emulated/0
     * 这个方法是获取外部存储的根路径
     * 6、Environment.getExternalStoragePublicDirectory(“”).getAbsolutePath() = /storage/emulated/0
     * 这个方法是获取外部存储的根路径
     * 7、getExternalFilesDir(“”).getAbsolutePath() = /storage/emulated/0/Android/data/packname/files
     * 这个方法是获取某个应用在外部存储中的files路径
     * 8、getExternalCacheDir().getAbsolutePath() = /storage/emulated/0/Android/data/packname/cache
     * 这个方法是获取某个应用在外部存储中的cache路径
     *
     * 注意：方法7和方法8如果在4.4以前的系统中getExternalFilesDir(“”)和getExternalCacheDir()将返回null，4.4及以后才会返回结果
     *data目录下的文件物理上存放在我们通常所说的内部存储里面
     *storage目录下的文件物理上存放在我们通常所说的外部存储里面
     *system用于存放系统文件，
     * /cache用于存放一些缓存文件，物理上它们也是存放在内部存储里面的
     *
     */
    companion object {
        val text1 = Environment.getDataDirectory().toString()
        val text2 = Environment.getDownloadCacheDirectory().toString()      // filepath:/data/cache
        val text3 = Environment.getRootDirectory().toString()               //filepath:/system
        val text4 =
            Environment.getExternalStorageDirectory().toString()    //filepath:/storage/emulated/0
        val text5 =
            context.cacheDir.toString()                             //   filepath:/data/user/0/com.example.androidfiletest/cache
        val text6 =
            context.dataDir.toString()                              //filepath:/data/user/0/com.example.androidfiletest
        val text7 =
            context.filesDir.toString()                             //filepath:/data/user/0/com.example.androidfiletest/files
        val text8 =
            context.obbDir.toString()                               //filepath:/storage/emulated/0/Android/obb/com.example.androidfiletest
        val text9 =
            context.noBackupFilesDir.toString()                     //filepath:/data/user/0/com.example.androidfiletest/no_backup
        val text10 =
            context.codeCacheDir.toString()                        //filepath:/data/user/0/com.example.androidfiletest/code_cache
        val text11 =
            context.externalCacheDir.toString()                    //filepath:/storage/emulated/0/Android/data/com.example.androidfiletest/cache
        val text12 = context.getExternalFilesDir(null)
            .toString()      //filepath:/storage/emulated/0/Android/data/com.example.androidfiletest/files


        @JvmStatic
        @RequiresApi(Build.VERSION_CODES.N)
        fun logAllDir() {
            mLogd(text1)
            mLogd(text2)
            mLogd(text3)
            mLogd(text4)
            mLogd(text5)
            mLogd(text6)
            mLogd(text7)
            mLogd(text8)
            mLogd(text9)
            mLogd(text10)
            mLogd(text11)
            mLogd(text12)

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