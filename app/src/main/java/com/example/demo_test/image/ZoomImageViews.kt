package com.example.demo_test.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import com.example.demo_test.R
import com.example.demo_test.utils.mLog


/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */
@SuppressLint("AppCompatCustomView")
class ZoomImageViews : ImageView, ScaleGestureDetector.OnScaleGestureListener {

    val TAG = javaClass.name
    var scaleGestureDetector: ScaleGestureDetector = ScaleGestureDetector(context, this)

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        return true

    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {
    }

    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        mLog.d(TAG, "focusX = " + detector?.focusX);       // 缩放中心，x坐标
        mLog.d(TAG, "focusY = " + detector?.focusY);       // 缩放中心y坐标
        mLog.d(TAG, "scale = " + detector?.scaleFactor);   // 缩放因子
        return true
    }
}