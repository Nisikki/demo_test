package com.example.demo_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.content.FileProvider;

import com.example.demo_test.download.DownloadUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.functions.Func2;


/**
 * @Author: Nisikki
 * @Date: 2021/3/11
 * @Describe:
 */
public class TestActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void init() {
//        tvTitle = findView(R.id.tv_title);
        test();

        DownloadUtil.downTemp();
    }

    private TextView tvTitle;

    public void test() {



    }

}
