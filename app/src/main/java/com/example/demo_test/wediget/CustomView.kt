package com.example.testdemo.wediget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View


/**
 * @Author: Nisikki
 * @Date: 2021/2/3
 */
 class CustomView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private lateinit var squareBitmap: Bitmap     //方形背景
    private lateinit var roundBitmap: Bitmap             //圆角背景
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var canvas1: Canvas
    private lateinit var rectF: RectF

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(dip2px(5).toFloat(), 0f, (width - dip2px(5)).toFloat(), height.toFloat())
        squareBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        squareBitmap.eraseColor(Color.parseColor("#BF000000"))
        roundBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvas1 = Canvas(roundBitmap)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas1.drawRoundRect(rectF, 20f, 20f, paint)
        canvas1.drawBitmap(squareBitmap, 0f, 0f, paint)
        canvas?.drawBitmap(roundBitmap, 0f, 0f, null)
    }

     fun dip2px(dip: Int): Int {
        val scale: Float = resources.displayMetrics.density
        return (dip * scale + 0.5f * if (dip >= 0) 1 else -1).toInt()
    }
}


