package com.example.demo_test

import android.content.Intent
import android.net.Uri
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import androidx.core.net.toUri
import com.example.demo_test.utils.mLog

/**
 * @Author: Nisikki
 * @Date: 2021/3/12
 * @Describe:
 */
class RouteACtivity : BaseActivity() {
    override fun setLayout() = R.layout.activity_route
    override fun init() {
        var editText = findView<EditText>(R.id.edit)


        val json = "{\"uid\":\"180\",\"is_reservation_activity\":true,\"type\":0,\"viewModel\":{\"roomId\":\"55\"}}"
        val json2 = "{\"uid\":\"180\"}"
        val json3 = "{\"openURL\":\"https://help.aliyun.com/document_detail/144231.html\"}"
        val param = Base64.encodeToString(json.toByteArray(), Base64.NO_WRAP)
        val param3 = Base64.encodeToString(json3.toByteArray(), Base64.NO_WRAP)//有=
        editText.setText(param)
        mLog.e(param)
        mLog.e(param3)

        // 为了对url更友好, 一般建议把base64的结果进行以下字符替换。
        // "+" 转成"-" ,"/"转成"_" , 等号删除
        // 解码的时候, 这个转换, 是反过来的
        var test = param3
        val json4 = "的大师傅士大夫士大夫地草草草草方士大夫大单独发大师傅师地方傅"//有+
//        test = Base64.encodeToString(json4.toByteArray(), Base64.NO_WRAP)
        mLog.e("test0:$test")
        test = test.replace("+", "-").replace("=","")
        mLog.e("test1:$test")
        test = test.replace("-", "+").replace("_", "/")
        while (test.length % 4 > 0) {
            test += '='
        }
        mLog.e("test2:$test")
        test = String(Base64.decode(test, Base64.NO_WRAP))

        mLog.e("test3:$test")

        val te:Int? = null
        test = " 哈哈哈"+ te?.toString() +" 哈哈哈"
        mLog.e("测试：$test")

        findView<Button>(R.id.btn_route).setOnClickListener {
//            var uri = Uri.parse("http://prf.51songguo.com/index/share?action=1");
            var uri = Uri.parse("sgscheme://main");
            var intent = Intent("android.intent.action.VIEW");
            intent.data = uri;
            startActivity(intent);
        }
        findView<Button>(R.id.btn_route1).setOnClickListener {
//            var uri = Uri.parse("http://prf.51songguo.com/index/share?action=1");
            var uri = Uri.parse("sgscheme://page/web?params=$param3");
            var intent = Intent("android.intent.action.VIEW")
            intent.data = uri;
            startActivity(intent);
        }
        findView<Button>(R.id.btn_route2).setOnClickListener {
//            var uri = Uri.parse("http://prf.51songguo.com/index/share?action=1");
            var uri = Uri.parse("sgscheme://main");
            var intent = Intent("android.intent.action.VIEW")
            intent.data = uri;
            startActivity(intent);
        }
        findView<Button>(R.id.btn_route3).setOnClickListener {
//            var uri = Uri.parse("http://prf.51songguo.com/index/share?action=1");
            var uri = Uri.parse("sgscheme://recharge?params=$param");
            var intent = Intent("android.intent.action.VIEW")
            intent.data = uri;
            startActivity(intent);
        }
        findView<Button>(R.id.btn_route4).setOnClickListener {
            var uri = Uri.parse("sgscheme://recharge?params=$json");
            var intent = Intent("android.intent.action.VIEW")
            intent.data = uri;
            startActivity(intent);
        }


        val uri = Uri.parse("sgscheme://recharge?params=$json")
        val a = uri.authority
        val b = uri.scheme
        val c = uri.host
        val d = uri.port
        val e = uri.path
        val f = uri.pathSegments
        val g = uri.encodedAuthority

    }


}