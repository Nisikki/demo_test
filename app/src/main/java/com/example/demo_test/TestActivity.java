package com.example.demo_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;


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
    }

    private TextView tvTitle;

    public void test() {


    }

}
