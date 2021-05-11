package com.example.demo_test.download

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.demo_test.BuildConfig
import com.example.demo_test.MyApplication
import com.example.demo_test.utils.MyLog.TAG
import com.example.demo_test.utils.mLogd
import com.example.demo_test.utils.tryCatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 * @Author: Nisikki
 * @Date: 2021/5/10
 * @Describe:
 */
object DownloadUtil {

    val image1 =
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201304%2F25%2F195133e7a1l7b4f5117y4y.jpg&refer=http%3A%2F%2Fattach.bbs.miui.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623222763&t=331b7babec7f3599e5deaef6e4533054"

    fun down(url: String) {

    }

    @JvmStatic
    fun downTemp() {
        tryCatch {
            GlobalScope.launch {
                val file = withContext(Dispatchers.IO) {
                    downPatch(image1, "2021_05_10.png")
                }
                if (file == null) {
                    mLogd("File == null")
                } else {
                    mLogd("File：${file.absolutePath}")
                    getFileUri(MyApplication.context, file)
                }

            }

        }
    }

    fun downPatch(httpUrl: String?, filename: String?): File? {
        var url: URL? = null
        var connection: HttpURLConnection? = null
        var istream: InputStream? = null
        val dir: File?
        var file: File? = null
        var output: OutputStream? = null
        try {
            url = URL(httpUrl)
            //打开到url的连接
            connection = url.openConnection() as HttpURLConnection
            //以下为java IO部分，大体来说就是先检查文件夹是否存在，不存在则创建,然后的文件名重复问题，没有考虑
            istream = connection.inputStream
            val total = connection.contentLength.toLong()
            dir = Environment.getExternalStorageDirectory()
//               dir = MyApplication.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            if (dir?.exists()!!) {
               dir.mkdirs()
            }
            file = File(Environment.getExternalStorageDirectory(), filename)
//            file = File( MyApplication.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), filename)
            output = FileOutputStream(file)
            val buffer = ByteArray(1024 * 4)
            var count = 0
            var percent: Int
            var read: Int
            while (-1 != istream.read(buffer).also { read = it }) {
                output.write(buffer, 0, read)
                count += read
                percent = (count / total.toFloat() * 100).toInt()

            }
            output.flush()
            return file
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                output?.close()
                istream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return file
    }

    fun getFileUri(context: Context, file: File): Uri? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tryCatch(null) {
                FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file)
            }
        } else {
            Uri.fromFile(file)
        }
    }
}

